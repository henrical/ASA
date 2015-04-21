#pragma once

#ifndef _EDGE_H_
#define _EDGE_H_

#include <iostream>

#include "node.h"

class Node; //forward declaration for  two mutually inclusive files

class Edge {
        private:
        const int weight;
        Node* pointee;
        
        explicit Edge(int w, Node*& p)
        :weight(w),pointee(p)
        {}
        
        int getWeight()
        {
	return weight;
        }
        
        Node* getPointee()
        {
	return pointee;
        }
        
        void print()
        {
	//std::cout << "Edge with weight << " << getWeight() << " >> to vertex << " << pointee->getNodeValue() << " >>" << std::endl;
        }
};

#endif