#ifndef _NODE_H_
#define _NODE_H_

#include <vector>

class Node {
 
  private:
    const int value;
    std::vector<Node*> neighbours;
   
  public:
  
    explicit Node(int val)
    :value(val)
    {}
    
    ~Node(){}
  
    const int getNodeValue() const;
    
    void addNeighbour(int val);
  
  
};

#endif