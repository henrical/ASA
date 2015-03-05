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

list_t list_init(int size);

void list_insert(int val);


#endif