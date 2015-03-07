#ifndef _NODE_H_
#define _NODE_H_

#include <vector>

class Node {
 
  private:
    const int value;
     
   
  public:
    Node* adjecent_node;
    
    //constructors
    explicit Node(int val)
    :value(val)
    {
      adjecent_node = NULL;
    }
    
    explicit Node(Node*& node)
    :adjecent_node(node->adjecent_node),value(node->getNodeValue())
    {}
    
    //destructor
    ~Node(){} 
  
    //instance methods
    const int getNodeValue() const;
    
    void addAdjecent(int val);
    
    void printAdjecentNodes() const;
  
  
};

#endif