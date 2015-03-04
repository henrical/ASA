#ifndef _GRAPH_H_
#define _GRAPH_H_

#include "node.h"

typedef struct adjecency_list
{
  /*
   * Array of pointers to Node objects
   */
  Node **list;

} *list_t;

list_t list_init(int size);

void list_insert(int val);


#endif