#include <iostream>
#include <stdio.h>
#include <limits>
#include "graph.h"
#include "node.h"

void breadth_first_search(list_t graph, int orig)
{
  int color[graph->graph_size];
  int predecessor[graph->graph_size];
  int distance[graph->graph_size];
  
  memset(color, 
  
}


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
}

