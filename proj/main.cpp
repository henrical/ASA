#include <iostream>
#include "graph.h"
#include "node.h"

int main()
{
  
  Node* n1 = new Node(1);
  std::cout << n1->getNodeValue() << std::endl;
  
  Node* n2 = new Node(2);
  n1->addNeighbour(n2);
  
  n1->printNeighbours();
}