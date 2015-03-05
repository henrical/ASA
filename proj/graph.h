#ifndef _GRAPH_H_
#define _GRAPH_H_

#include "node.h"

  /*
   * Structure that stores an array of pointers to Node objects;
   */
typedef struct adjecency_list
{
  Node **list;

} *list_t;

//initialize adjecency list with a given number of nodes
list_t list_init(int size);

//TODO
void list_insert(int val);


#endif