#include <iostream>
#include <stdio.h>
#include "graph.h"
#include "node.h"


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
  
  //std::cout << edge_num<< std::endl;
  
  
  
  
  
  
  //std::cout << *i[0] << std::endl << *i[1] << std::endl;
  
  /* user interface TODO
  std::string input_line;
  std::cin >> input_line;
  
  std::istringstream(input_line) >> i
  std::cout << i << std::endl;
  */
  
  /*
  Node* n1 = new Node(1);
  
  list_t graph = list_init(6);
  list_insert_edge(graph, 2, 4);
  list_insert_edge(graph, 2, 5);
  list_insert_edge(graph, 2, 6);
  list_insert_edge(graph, 1, 5);
  list_insert_edge(graph, 5, 6);
  list_insert_edge(graph, 6,6);
  n1 = graph->list[1];
  
  list_print(graph);*/
}

