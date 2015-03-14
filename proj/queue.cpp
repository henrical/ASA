#include <iostream>
#include "node.h"

#include "queue.h"

const int QueueNode::getNodeValue() const
{
  return value;
}


void QueueNode::push(int val)
{  
  QueueNode* new_node = new QueueNode(val);
  
  if(next_elem==NULL)
  {
     next_elem = new_node;
     //std::cerr << "::PUSH:: " << val << " ON EMPTY QUEUE" << std::endl;
  }
  else
  {
    new_node->next_elem = next_elem;
    
    //std::cerr << "::PUSH:: QUEUE" << std::endl;
    //creates copy of new_node
    next_elem = new QueueNode(new_node);
    
    delete new_node;
  }
}

int QueueNode::pop()
{
  int result;
  QueueNode *temp_elem;
  
  if(next_elem==NULL)
  {
     std::cerr << "::POP:: Queue is empty!" << std::endl;
     return -1;
  }
  else if(next_elem->next_elem==NULL)
  { 
     result = next_elem->getNodeValue();
     
     std::cerr << "::POP:: QUEUE WITH ONE ELEMENT. POPPED " << result << std::endl;
     
     next_elem = NULL;
  }
  else
  {
     temp_elem = new QueueNode(next_elem->next_elem);
    
     result = next_elem->getNodeValue();
    
     delete next_elem;
    
     next_elem = temp_elem;
     
     std::cerr << "::POP:: QUEUE WITH MORE THAN ONE ELEMENT.POPPED " << result << std::endl;
  }
  
  
  return result;
}

void QueueNode::printAdjecentNodes() const
{
  QueueNode *node_iterator = next_elem;
  int i=0;
  
  while(node_iterator!=NULL)
    {
	  std::cout << "->" <<node_iterator->getNodeValue() << std::endl;
	  
	  node_iterator = node_iterator->next_elem;
	  
	  i++;
    }
	
  std::cout << i << " elements in queue" << std::endl;
}
