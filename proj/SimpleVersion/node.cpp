#include <iostream>
#include "node.h"

#include <vector>

const int Node::getNodeValue() const
{
  return value;
}


void Node::addAdjecent(int val)
{ 
  Node* new_node = new Node(val);
  
  if(adjecent_node==NULL)
  {
     adjecent_node = new_node;
  }
  else
  {
    new_node->adjecent_node = adjecent_node;
    
    //creates copy of new_node
    adjecent_node = new Node(new_node);
    
    delete new_node;
  }
}

