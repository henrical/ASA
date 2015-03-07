#include <iostream>
#include "graph.h"
#include "node.h"

int main()
{
  
  Node* n1 = new Node(1);
  /*
  n1->addAdjecent(2);
  
  n1->addAdjecent(3);
  n1->addAdjecent(4);
  n1->addAdjecent(5);
  
  n1->printAdjecentNodes();
  */
  
  list_t graph = list_init(6);
  list_insert_edge(graph, 2, 4);
  list_insert_edge(graph, 2, 5);
  list_insert_edge(graph, 2, 6);
  list_insert_edge(graph, 1, 5);
  list_insert_edge(graph, 5, 6);
  list_insert_edge(graph, 6,6);
  n1 = graph->list[1];
  
  //n1->printAdjecentNodes();
  
  list_print(graph);
}