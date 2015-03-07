#include <iostream>
#include "node.h"

#include <vector>

const int Node::getNodeValue() const
{
  return value;
}


  /*
   *  addAdjecent is used to insert nodes in the second 
   *  position of the list, so that the complexity is O(1)  
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
  }
  else
  {
    new_node->adjecent_node = adjecent_node;
    
    //creates copy of new_node
    adjecent_node = new Node(new_node);
    
    delete new_node;
  }
}

  /*
   * Prints the value of all nodes attached to object.
   * Mainly for debugging purposes.
   * 
   */
void Node::printAdjecentNodes() const
{
  Node *node_iterator = adjecent_node;
  int i=0;
  
  while(node_iterator!=NULL)
    {
	  std::cout << node_iterator->getNodeValue() << std::endl;
	  
	  node_iterator = node_iterator->adjecent_node;
	  
	  i++;
    }
	
  std::cout << "Node " << getNodeValue() << " has " << i << " adjecent nodes." << std::endl;
}
