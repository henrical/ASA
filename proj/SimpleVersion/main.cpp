#include <iostream>
#include "graph.h"
#include "node.h"

int main()
{
  
  Node* n1 = new Node(1);

  list_t graph = list_init(6);
  list_insert_edge(graph, 2, 4);
  list_insert_edge(graph, 2, 5);
  list_insert_edge(graph, 2, 6);
  list_insert_edge(graph, 1, 5);
  list_insert_edge(graph, 5, 6);
  list_insert_edge(graph, 6,6);
  n1 = graph->list[1];
  
}