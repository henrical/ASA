#include <iostream>
#include "node.h"

#include <vector>

const int Node::getNodeValue() const
{
  return value;
}


void Node::addAdjecent(int val)
{ 
  /*  addAdjecent is used to insert nodes in the second 
   *  position of the list, so that the complexity is O(1)  
   *   ___     ________      ___      ___
   *  | 1-|-->|new node|--->| 3-|--->| 2 |
   *  |___|   |________|    |___|    |___|
   *  | 2 |
   *  |___|
   *  | 3 |
   *  |___|
   *  
   *  Warning: If node is already adjecent, it will be 
   *  inserted again. Fix? TODO
   */
   
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

std::vector<int> Node::getAdjecentNodes() const
{
  Node *node_iterator = adjecent_node;
  std::vector<int> result;
  int i=0;
  
  while(node_iterator!=NULL)
    {
      result.push_back(node_iterator->getNodeValue());
      
      node_iterator = node_iterator->adjecent_node;
    }
    
  return result;
}

void Node::printAdjecentNodes() const
{
  /* Prints the value of all nodes attached to object.
   * Mainly for debugging purposes.
   * 
   * Example output:
   * "Node 1:
   *  1->2
   *  2->3
   *  Node 1 has 1 adjecent nodes."
   */
  Node *node_iterator = adjecent_node;
  int i=0;
  
  while(node_iterator!=NULL)
    {
	  std::cout << getNodeValue() << "->" <<node_iterator->getNodeValue() << std::endl;
	  
	  node_iterator = node_iterator->adjecent_node;
	  
	  i++;
    }
	
  std::cout << "Node " << getNodeValue() << " has " << i << " adjecent nodes." << std::endl;
}
