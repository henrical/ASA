#include <iostream>
#include "graph.h"

list_t list_init(int size)
{
    //allocate memory for struct
    list_t adj_list = new adjecency_list; 
    
    //allocate memory for array within struct
    adj_list->list=new Node*[size];
    
    return adj_list;
}