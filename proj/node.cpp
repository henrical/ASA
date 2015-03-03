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