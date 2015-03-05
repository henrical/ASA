#include <iostream>
#include "node.h"

#include <vector>

const int Node::getNodeValue() const
{
  return value;
}

  /*
   *  addAdjecent is used to insert nodes in the second 
   *  position of the list i.e. the beggining of the 
   *  adjecency queue. 
   *   ___     ________      ___      ___
   *  | 1-|-->|new node|--->| 3-|--->| 2 |
   *  |___|   |________|    |___|    |___|
   *  | 2 |
   *  |___|
   *  | 3 |
   *  |___|
   */
void Node::addAdjecent(int val)
{
  
  if(adjecent_node==NULL)
  {
     adjecent_node = new Node(val);
     std::cout << "Node with value " << adjecent_node->getNodeValue() <<" added to empty adjecency queue." <<std::endl;
  }
  else
  {
    
    Node* new_node = new Node(val);
    new_node->adjecent_node = adjecent_node;
    
    adjecent_node = new Node(new_node);
    
    delete new_node;
  }
  
  
}


void Node::printAdjecentNodes() const
{
  Node *node_iterator = adjecent_node;
  int i=0;
  
  while(node_iterator!=NULL)
    {
	  std::cout << "###########################" << std::endl;
	  std::cout << node_iterator->getNodeValue() << std::endl;
	  
	  node_iterator = node_iterator->adjecent_node;
	  
	  i++;
    }
	
  std::cout << i << " adjecent nodes." << std::endl;
}
