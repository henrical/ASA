public class Queue
{
        private int front;
        private int rear;
        
        private Vertex queue[];
        
        public Queue(int size)
        {
	queue = new Vertex[size+1];
	front = 0;
	rear = 0;
        }
        
        public void print()
        {
	int i;
	
	System.out.println("<<<<<<<<<<<<<<<<Printing queue...>>>>>>>>>>>>>>>>>>");
	for(i=0; i<queue.length ; i++)
	{
	        queue[i].print();
	}
	System.out.println("<<<<<<<<<<<<<<<<=================>>>>>>>>>>>>>>>>>>");
        }
        
        public void push(Vertex v)
        {
	if(front==queue.length-1)
	        System.out.println("==========QUEUE SIZE EXCEEDED!!===========");
	        
	queue[front]=v;
	
	System.out.println("Inserting <<" + v.getValue() + ">>.");
	
	front++;
        }
        
        public Vertex get()
        {
	Vertex result = queue[rear];
	
	System.out.println("Getting <<" + result.getValue() + ">>.");
	rear = rear + 1;
	return result;
        }
        
        public boolean isEmpty()
        {
	return (front==rear);
        }

        
}