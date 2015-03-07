#ifndef _GRAPH_H_
#define _GRAPH_H_

#include "node.h"

typedef struct adjecency_list
{  
  Node **list;
  
} *list_t;

list_t list_init(int size);

void list_insert_edge(list_t graph, int node, int edge);

void list_print(list_t graph);


#endif