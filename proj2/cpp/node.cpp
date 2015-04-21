#include <iostream>
#include "node.h"

#include <vector>


Edge::Edge(int w, Node*& p)
        :weight(w),pointee(p)
        {}
        
void Edge::print()
        {
	std::cout << "Edge with weight << " << getWeight() << " >> to vertex << " << pointee->getNodeValue() << " >>" << std::endl;
        }

Node* Edge::getPointee()
        {
	return pointee;
        }
   
int Edge::getWeight()
        {
	return weight;
        }
        
/*********************************************
 * NODE
 *********************************************/ 
        
Node::Node(int val)
    :value(val)
    {
      //edges = new std::vector<Edge*>();
    }
    
Node::Node(Node*& node)
    :edges(node->edges),value(node->getNodeValue())
    {}
       

const int Node::getNodeValue() const
{
  return value;
}


void Node::addEdge(int weight, Node* destination)
{ 
        edges.push_back(new Edge(weight,destination));
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
