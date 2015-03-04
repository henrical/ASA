#include <iostream>
#include "node.h"

#include <vector>

const int Node::getNodeValue() const
{
  return value;
}

void Node::addNeighbour(int val)
{
  Node *temp_node = new Node(val);
  neighbours.push_back(temp_node);
  
  delete temp_node;
}

void Node::addNeighbour(Node* n)
{
    
   neighbours.push_back(n);
}

void Node::printNeighbours() const
{
  std::vector<Node*>::size_type i; //kind of an int
  
  for(i=0; i<neighbours.size(); i++)
	{
		std::cout << neighbours[0]->getNodeValue() << std::endl;
	}
	
  std::cout << i << " adjecent nodes." << std::endl;
}
