#ifndef _QUEUE_H_
#define _QUEUE_H_

class QueueNode{
 
  private:
    const int value;
    
    
  public:
    QueueNode* next_elem;
  
    
    //constructors
    explicit QueueNode(int val)
    :value(val)
    {
      next_elem = NULL;
    }
    
    //creates empty node (head of queue)
    explicit QueueNode()
    :value(0)
    {
      next_elem = NULL;
    }
    
    //creates copy of another QueueNode
    explicit QueueNode(QueueNode*& node)
    :next_elem(node->next_elem),value(node->getNodeValue())
    {}
    
    //destructor
    ~QueueNode(){} 
  
  
    //instance methods
    const int getNodeValue() const;
    
    void push(int val);
    int pop() ;
    
    void printAdjecentNodes() const;
  
  
};

#endif