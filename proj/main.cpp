#include <iostream>
#include <stdio.h>
#include <limits>
#include <cstring>
#include "graph.h"
#include "node.h"
#include "queue.h"

#define NIL -1
#define WHITE 0
#define GREY 1
#define BLACK 2
#define INFINITY std::numeric_limits<int>::max()

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
  }
  
  list_print(graph);
  
  breadth_first_search(graph,orig_node);
}

void breadth_first_search(list_t graph, int orig)
{
  int size = graph->graph_size;
  int i;
  
  int color[size];
  int predecessor[size];
  int distance[size];
  
  //intialize color[] to 0
  memset(color, WHITE, sizeof(color));
  //initialize predecessor[] to NIL
  memset(predecessor, NIL, sizeof(predecessor));
  
  //initialize size[] to infinity
  for(i=0; i<size; i++)
  {
    distance[i] = INFINITY;
  }
    
}

