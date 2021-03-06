#include <iostream>
#include <stdio.h>
#include <limits>
#include <cstring>
#include <vector>
#include "graph.h"
#include "node.h"
#include "queue.h"

#define NIL -1
#define WHITE 0
#define GREY 1
#define BLACK 2
//#define INFINITY std::numeric_limits<int>::max()

void breadth_first_search(list_t graph, int orig);

int main()
{ 
  
  list_t graph; // <--pointer
  
  int i;
  
  int temp1;
  int temp2;
  
  int edge_num;
  int orig_node;
  
  scanf("%d %d", &temp1,&edge_num);
  
  graph = list_init(temp1);
  
  scanf("%d", &orig_node);
  
  for(i=0; i<edge_num; i++)
  {
      scanf("%d %d", &temp1, &temp2);
      list_insert_edge(graph, temp1, temp2);
      list_insert_edge(graph, temp2, temp1);
  }
  
  //list_print(graph);*/
  
  std::cout << "BFS Start:" << std::endl;
  
  breadth_first_search(graph,orig_node);
}


/*
 *   breadth_first_search
 *   orig is the initial node 
 *   computes and prints results(!)
 */
void breadth_first_search(list_t graph, int orig)
{
  
  /**********************************
   * Initialization
   **********************************/
  
  std::vector<int> adj;
  
  int size = graph->graph_size;
  QueueNode *queue = new QueueNode(size+1);
  
  int i,c,u,v, vertex_index;
  int M = 0;
  
  int color[size];
  int predecessor[size];
  int distance[size];
  
  //intialize color[] to 0
  memset(color, WHITE, sizeof(color));
  //initialize predecessor[] to NIL
  memset(predecessor, NIL, sizeof(predecessor));
  
  //initialize distance[] to infinity
  for(i=0; i<size; i++)
  { 
    distance[i] = 0;
  }
  
  /********************************
   * Algorithm
   ********************************/
  
  //push first vertex
  queue->push(orig);
  color[orig-1]=GREY;
  i=1;

  
  while(!queue->isEmpty())
  {
    std::cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << std::endl;
    std::cout << "Iteration: " << i << std::endl;
    i++;
    
    u = queue->pop();
    
    adj = graph->list[u-1]->getAdjecentNodes();
    
    std::cout << "Printing adjecent nodes:" << std::endl;
    for(c=0; c<adj.size(); c++)
    {
      std::cout << adj[c] << std::endl;
    }
    
    for(c=0; c<adj.size(); c++)
    {
      vertex_index = adj[c]-1;
    
      if(color[vertex_index]==WHITE)
      {
	std::cout << "==================" << std::endl;
	std::cout << "Going into node " << adj[c] << "." << std::endl;
	color[vertex_index]=GREY;
	distance[vertex_index] = distance[u-1] + 1;
	if(distance[vertex_index]>M)
	  M = distance[vertex_index];
        std::cout << "Distance of " << adj[c] << " set to " << distance[u-1] + 1 << std::endl;
	predecessor[vertex_index] = u;
	std::cout << "Predecessor of " << adj[c] << " set to " << u << std::endl;
	
	queue->push(adj[c]);
	std::cout << "Pushing " << adj[c] << " to queue."<< std::endl;
      }
      else
      {
	std::cout << adj[c] << " already visited." << std::endl;
      }
      
    }  
    std::cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << std::endl;
  }
  
    std::cout << "Expected Output:" << std::endl;
    std::cout << "M="<< M << std::endl;
    
    int dist_count[M];
    memset(dist_count, 0, sizeof(dist_count));
    
    for(i=0; i<size; i++)
    {
      if(distance[i]==0)
	continue;
      else
	dist_count[distance[i]-1]++; 
    }
    
    for(i=0; i<M; i++)
    {
      std::cout << dist_count[i] << std::endl;
    }
    
    /*for(i=0; i<size; i++)
    {
      std::cout << "Distance of " << i+1 << ": " << distance[i] << std::endl;
    }*/
    
    std::cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << std::endl;
    
}

