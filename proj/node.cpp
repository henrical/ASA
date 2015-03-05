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
  Node* new_node = new Node(val);
  
  if(adjecent_node==NULL)
  {
     adjecent_node = new_node;
     std::cout << "Node added to empty adjecency queue." <<std::endl;
  }
  else
  {
    new_node->adjecent_node = adjecent_node;
    
    adjecent_node = new_node;
    std::cout << "Node added to queue." <<std::endl;
    
  }
  
  delete new_node;
}


void Node::printAdjecentNodes() const
{
  Node *node_iterator = adjecent_node;
  int i=0;
  
  while(node_iterator!=NULL)
    {
	  std::cout << "###########################" << std::endl;
	  std::cout << node_iterator->getNodeValue() << std::endl;
	  std::cout << "###########################" << std::endl;
	  
	  node_iterator = node_iterator->adjecent_node;
	  
	  i++;
    }
	
  std::cout << i << " adjecent nodes." << std::endl;
}
