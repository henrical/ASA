#include <iostream>
#include "graph.h"

list_t list_init(int size)
{
    int i;
  
    //allocate memory for struct
    list_t adj_list = new adjecency_list; 
    
    //allocate memory for array within struct
    adj_list->list=new Node*[size];
    
    /*initializes node values in numerical order 
     *(1,2,3,...size)  */
    for(i=0; i<size; i++)
    {
      adj_list->list[i] = new Node(i+1);
      //std::cout << adj_list->list[i]->getNodeValue() << std::endl;
    }
    
    return adj_list;
}


void list_insert_edge(list_t graph, int node, int edge)
{ 
  /*  Inserts an edge between node at position 'node' and
   *  the node at position 'edge'
   *  
   *  For example
   *  list_insert_edge(graph, 1 , 2) inserts node between
   *  1 and 2
   */
  graph->list[node-1]->addAdjecent(edge);
}


void list_print(list_t graph)
{
  int i = 0;
  bool lastElem = false;
  
  std::cout << "Printing graph:" << std::endl;
  
  while(!(lastElem))
  {
    std::cout << "Node "<< i+1 << ":" << std::endl;
    graph->list[i]->printAdjecentNodes();
    std::cout << "------------------------" <<std::endl;
    
    if(graph->list[i+1]!=NULL)
	i++;
    else
	lastElem = true;
  }
  
  
}

