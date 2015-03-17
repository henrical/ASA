#include <iostream>
#include "queue.h"


void QueueNode::push(int val)
{ 
    std::cout << "======================================" << std::endl;
    queue[rear] = val;
    std::cout << "Pushed " << val << " into position " << rear << "." << std::endl;
    rear++;
}

int QueueNode::pop()
{ 
    std::cout << "======================================" << std::endl;
    int result;

    result = queue[front];
    std::cout << "Popped " << result << " from position " << front << "." << std::endl;
    front++;
    return result;
}

void QueueNode::print() const
{
  std::cout << "===============PRINTING==QUEUE=============" << std::endl;
  int i = front;

  if(isEmpty()) {
    std::cout << "Queue is empty." << std::endl;
    return;
  }

  for(i; i<rear;i++)
  {
    std::cout << "Element at postion " << i << " is " << queue[i] << std::endl;
  }
}