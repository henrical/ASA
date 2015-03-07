#ifndef _GRAPH_H_
#define _GRAPH_H_

#include "node.h"

typedef struct adjecency_list
{  
  // Structure that stores an array of pointers to Node objects;
  Node **list;
  
} *list_t;

//initialize adjecency list(graph) with a given number of nodes
list_t list_init(int size);


//'node' is the origin node, edge is the destination
void list_insert_edge(list_t graph, int node, int edge);


//output graph
void list_print(list_t graph);


#endif