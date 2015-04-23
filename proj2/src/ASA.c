#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

typedef struct inactive_vertex_list
{
        int size;
        int num_elem;
        int* list;
} *t_vertex_list;

t_vertex_list vertex_list_init(int size)
{
        t_vertex_list result =(t_vertex_list)malloc(sizeof(t_vertex_list));
        result->size = size;
        result->num_elem=0;
        result->list = (int*)malloc(sizeof(int)*size);
        
        return result;
}

int vertex_list_insert(t_vertex_list l, int vertex)
{
        if(l->list[vertex-1]==0) {
	l->list[vertex-1]=1;
	l->num_elem++;
	return 1;
        }
        else
	return 0;
}

int vertex_list_is_full(t_vertex_list l)
{
        if(l->num_elem == l->size)
	return 1;
        else
	return 0;
}

typedef struct _edge{

	int source;
	int destination;
	int weight;

}Edge;

typedef struct _intFifo{

	int value;
	struct _intFifo *next;
	
}IntFifo;

IntFifo *createFifo(){
	IntFifo *base = (IntFifo *) malloc(sizeof(IntFifo));
	if(base == NULL)
		exit(EXIT_FAILURE);
	base->next = NULL;
	
	return base;
}

void addToFifo(IntFifo *base, int value){
	
	IntFifo *new = (IntFifo *) malloc(sizeof(IntFifo));
	IntFifo *aux = base;
	
	if(new == NULL)
		exit(EXIT_FAILURE);
		
	while(aux->next != NULL)
		aux = aux->next;
	
	new->value = value;
	new->next = NULL;
	aux->next = new;
	
}

int peekFifoNext(IntFifo *base){
	if(base->next == NULL)
		return INT_MIN;
	else
		return base->next->value;
}

int getFromFifo(IntFifo *base){
	int value = peekFifoNext(base);
	if(value != INT_MIN){
		IntFifo *old = base->next;
		base->next = old->next;
		free(old);
	}
	return value;
}

void emptyFifo(IntFifo *base){
	int value;
	do{
		value = getFromFifo(base);
	}while(value != INT_MIN);
}

int isFifoEmpty(IntFifo *base){
	if(base->next != NULL)
		return 0;
	else
		return 1;
}

int getFifoSize(IntFifo *base){
	int size = 0;
	IntFifo *aux = base->next;
	while(aux != NULL){
		size++;
		aux = aux->next;
	}
	return size;
}

int fifoHasValue(IntFifo *base, int value){
	IntFifo *aux = base->next;
	
	while(aux != NULL){
		if(aux->value == value)
			return 1;
		aux = aux->next;
	}
	
	return 0;
}

int *runBellmanFord(int n, int m, Edge* edges, int originValue){

		t_vertex_list l = vertex_list_init(n);
		int no_active_vertices = 0;
		int *distance;
		int predecessor[n];
		int i, j;
		IntFifo *negativeCycleVertices;
		IntFifo *forward;/* = (IntFifo *) malloc(sizeof(IntFifo));*/
		
		distance = (int *) malloc(n * sizeof(int));
		
		/*
		 * Initialization
		 */
		for(i = 0; i < n; i++){
			/*distance.add(Integer.MAX_VALUE);
			predecessor.add(Integer.MAX_VALUE);*/
			distance[i] = INT_MAX;
			predecessor[i] = INT_MAX;
		}
		distance[originValue - 1] = 0;
		
		for(i = 1; i < n -1; i++){
		        if(no_active_vertices)
			break;
		        
			for(j = 0; j < m; j++){
				if(vertex_list_is_full(l))
				{
				        no_active_vertices = 1;
				        break;
				}
				        
				Edge e = edges[j];
				if(distance[e.source - 1] == INT_MAX)
					continue;
				if(distance[e.source - 1] + e.weight < distance[e.destination - 1]){
					distance[e.destination - 1] = distance[e.source - 1] + e.weight;
					predecessor[e.destination - 1] = e.source;
				}
				else
					vertex_list_insert(l,e.destination);
			}
		}
		
		/*IntFifo *negativeCycleVertices = (IntFifo *) malloc(sizeof(negativeCycleVertices));*/
		negativeCycleVertices = createFifo();
		for(i = 0; i < m; i++){
			Edge e = edges[i];
			if(distance[e.source - 1] + e.weight < distance[e.destination - 1]){
				addToFifo(negativeCycleVertices, e.source);
			}
		}
		
		while(isFifoEmpty(negativeCycleVertices) == 0){
			int infected = getFromFifo(negativeCycleVertices);
			if(predecessor[infected - 1] == INT_MAX)
				continue;
			int pred = predecessor[infected - 1];
			
			forward = createFifo();
			addToFifo(forward, infected);
			do{
				if(fifoHasValue(forward, pred) == 1)
					break;
				addToFifo(forward, pred);
				pred = predecessor[pred - 1];
			}while(pred != infected);
			
			int fifoSize = getFifoSize(forward);
			for(i = 0; i < fifoSize; i++){
				distance[getFromFifo(forward) - 1] = INT_MIN;
			}
			if(isFifoEmpty(forward) == 0)
				emptyFifo(forward);
			free(forward);
		}
		free(negativeCycleVertices);
		
		for(i = 0; i < n; i++){
			int pred = predecessor[i];
			do{
				if(pred == INT_MAX)
					break;
				pred = predecessor[pred - 1];
				if(pred == INT_MAX)
					break;
				if(distance[pred - 1] == INT_MIN){
					distance[i] = INT_MIN;
					break;
				}
			}while(pred != i);
		}
		return distance;
	}

int main(int argc, char *argv[]){

	int n;
	int c;
	int originNodeId;
	int **edgesId;
	int *results;

	int i;
	char br[20], *j;
	
	FILE *input_file = NULL;

	if(argc > 1){	
		input_file = fopen(argv[1], "r");
		if(input_file == NULL){
			printf("No input file %s\n", argv[1]);
			exit(EXIT_FAILURE);
		}
	}
	
	if(argc > 1)
		j = fgets(br, 20, input_file);
	else
		j = fgets(br, 20, stdin);
	sscanf(br, "%d %d", &n, &c);
	
	if(argc > 1)
		j = fgets(br, 20, input_file);
	else
		j = fgets(br, 20, stdin);
	sscanf(br, "%d", &originNodeId);
	
	edgesId = (int **) malloc(c * sizeof(int *));
	
	for(i = 0; i < c; i++){
		
		edgesId[i] = (int *) malloc(3 * sizeof(int));
		
		if(argc > 1)
			j = fgets(br, 20, input_file);
		else
			j = fgets(br, 20, stdin);
		sscanf(br, "%d %d %d", &edgesId[i][0], &edgesId[i][1], &edgesId[i][2]);
	}
	
	if(argc > 1)
		fclose(input_file);
	
	Edge *edges = (Edge *) malloc(c * sizeof(Edge));
	
	for(i = 0; i < c; i++){
		edges[i].source = edgesId[i][0];
		edges[i].destination = edgesId[i][1];
		edges[i].weight = edgesId[i][2];
	}
	
	for(i = 0; i < c; i++){
		free(edgesId[i]);
	}
	free(edgesId);

	results = runBellmanFord(n, c, edges, originNodeId);
	
	/*FILE *output_file = fopen("output.out","w");*/
	/*if(output_file == NULL){
		printf("No output file\n");
		exit(EXIT_FAILURE);
	}*/
	
	for(i = 0; i < n; i++){
		int val = results[i];
		if(val == INT_MIN)
			fputs("I\n", stdout);
		else if(val == INT_MAX)
			fputs("U\n", stdout);
		else{
			char output[10];
			sprintf(output, "%d\n", val);
			fputs(output, stdout);
		}
	}
	
	/*fclose(output_file);*/
	
	free(edges);	
	free(results);
	
	sprintf(j, "POTATO");
	
	exit(EXIT_SUCCESS);
}
