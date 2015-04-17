#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

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

int *runBellmanFord(int n, int m, Edge* edges, int originValue){

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
			for(j = 0; j < m; j++){
				Edge e = edges[j];
				if(distance[e.source - 1] == INT_MAX)
					continue;
				if(distance[e.source - 1] + e.weight < distance[e.destination - 1]){
					distance[e.destination - 1] = distance[e.source - 1] + e.weight;
					predecessor[e.destination - 1] = e.source;
				}
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

int main(){

	int n;
	int c;
	int originNodeId;
	int **edgesId;
	int *results;

	int i;
	char br[20], *j;
	
	j = fgets(br, 20, stdin);
	sscanf(br, "%d %d", &n, &c);
	
	j = fgets(br, 20, stdin);
	sscanf(br, "%d", &originNodeId);
	
	edgesId = (int **) malloc(c * sizeof(int *));
	
	for(i = 0; i < c; i++){
		
		edgesId[i] = (int *) malloc(3 * sizeof(int));
		
		j = fgets(br, 20, stdin);
		sscanf(br, "%d %d %d", &edgesId[i][0], &edgesId[i][1], &edgesId[i][2]);
	}
	
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
	
	free(edges);	
	free(results);
	
	sprintf(j, "POTATO");
	
	exit(EXIT_SUCCESS);
}
