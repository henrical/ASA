#pragma once

#ifndef _NODE_H_
#define _NODE_H_

#include <iostream>
#include <vector>

class Node;

class Edge {
        private:
        const int weight;
        Node* pointee;
        
        explicit Edge(int w, Node*& p);
        
        int getWeight();
        
        Node* getPointee();
        void print();
       
};

class Node {
 
  private:
    const int value;
    
    
  public:
    std::vector<Edge*> edges;
  
    
    //constructors
    explicit Node(int val);
    
    //creates copy of another node
    explicit Node(Node*& node);
    
    //destructor
    ~Node(){} 
  
  
    //instance methods
    const int getNodeValue() const;
    
    void addEdge(int weight, Node* destination);
    
    void printAdjecentNodes() const;
    
    //return vector with all adjecent vertexes
    std::vector<int> getAdjecentNodes() const;
  
  
};

#endif