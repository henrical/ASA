#include <iostream>
#include "graph.h"

list_t list_init(int size)
{
    int i;
  
    list_t adj_list = new adjecency_list; 
    
    adj_list->list=new Node*[size];
    
    for(i=0; i<size; i++)
    {
      adj_list->list[i] = new Node(i+1);
    }
    
    return adj_list;
}


void list_insert_edge(list_t graph, int node, int edge)
{ 
  graph->list[node-1]->addAdjecent(edge);
}



 

