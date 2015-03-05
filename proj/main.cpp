#include <iostream>
#include "graph.h"
#include "node.h"

int main()
{
  
  Node* n1 = new Node(1);
  std::cout << n1->getNodeValue() << std::endl;
  
  n1->addAdjecent(2);
  
  n1->addAdjecent(3);
  n1->addAdjecent(4);
  n1->addAdjecent(5);
  //n1->printAdjecentNodes();
  /*if(n1->adjecent_node==NULL)
    std::cout << "STUFF" << std::endl;*/
    
  std::cout << n1->adjecent_node->getNodeValue() << std::endl;
  std::cout << n1->adjecent_node->adjecent_node->getNodeValue() << std::endl;
  
 
  std::cout << n1->adjecent_node->adjecent_node->adjecent_node->getNodeValue() << std::endl;
  
  std::cout << n1->adjecent_node->adjecent_node->adjecent_node->adjecent_node->getNodeValue() << std::endl;
}