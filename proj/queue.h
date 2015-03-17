#ifndef _QUEUE_H_
#define _QUEUE_H_

class QueueNode{

  public:
    int *queue;
    int front; //the first element
    int rear; //the last element
  
    
    //constructors
    explicit QueueNode(int size)
    {
        queue = new int[size];
        front = 0;
        rear = 0;
    }
    
    //destructor
    ~QueueNode(){} 
    
    void push(int val);
    int pop() ;
    
    void print() const;
    
    bool isEmpty() const
    {
	   return front==rear;
    }
  
  
};

#endif