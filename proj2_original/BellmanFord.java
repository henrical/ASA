import java.lang.Integer;

public class BellmanFord
{
        private int distance[];
        private int predecessor[];
        private final int size;
	
        public BellmanFord(Graph g)	
        {
	size = g.getSize();
	distance = new int[size];
	predecessor = new int[size];
        }
        
        /**
         * Returns the distance of a given vertex.
         */
        private int getDistanceOfVertex(int vertex)
        {
	return distance[vertex-1];
        }
        
        /**
         * Sets a new distance to a given vertex.
         */
        private void setDistanceOfVertex(int vertex, int val)
        {
	distance[vertex-1] = val;
        }
        
            /**
         * Returns the predecessor of a given vertex.
         */
        private int getPredecessorOfVertex(int vertex)
        {
	return predecessor[vertex-1];
        }
        
        /**
         * Sets a new predecessor to a given vertex.
         */
        private void setPredecessorOfVertex(int vertex, int val)
        {
	predecessor[vertex-1] = val;
        }
        
        /**
         * Initializes de distance array.
         * All vertices get value INFINITE except the source vertex.
         */
        private void initializeSingleSource(int source)
        {
	int i;
        
	for(i=0; i<size ; i++)
	        distance[i] = Integer.MAX_VALUE ;
	     
	setDistanceOfVertex(source,0);
	setPredecessorOfVertex(source,0);
        }
        
        
        /**
         * Relaxes the edge. 
         * Returns true if it did something, false otherwise.
         */
        public boolean relax(int orig, int dest, int weight)
        {
	if(getDistanceOfVertex(orig) + weight <getDistanceOfVertex(dest))
	{
	        setDistanceOfVertex(dest, getDistanceOfVertex(orig) + weight);
	        setPredecessorOfVertex(dest, orig);
	        return true;
	}
        
	return false;
        }
        
        private void printOutput()
        {
	int i;
	
	for(i=1; i<= size; i++)
	{
	        switch (getDistanceOfVertex(i)) {
		case Integer.MAX_VALUE: 
		        System.out.println(i + " U");
		        break;
		case Integer.MIN_VALUE: 
		        System.out.println(i + " I");
		        break;
		default: 
		        System.out.println(i + " " + getDistanceOfVertex(i));
		        break;
	        }
	}
        }
        
        /**
         * Runs the algorithm.
         *
         */
        public void run(Graph g, int source)
        {
	int iterations;
	int vert_num;
	int dist;
	Edge edges[];
	
	initializeSingleSource(source);
	
	for(iterations=0; iterations < size-1; iterations++)
	{
	        innerFor: for(vert_num=1; vert_num <= size; vert_num++)
	        {
		if(getDistanceOfVertex(vert_num) == Integer.MAX_VALUE)
			continue innerFor;
	        
		edges = g.getVertexByValue(vert_num).getEdges();
		
		for(Edge e : edges)
		{       
		        relax(vert_num, e.getPointeeValue(), e.getWeight());     
			//System.out.println("---> Relax of edge (" + vert_num + "," + e.getPointeeValue() + ") SUCCEDED");
		        //else
			//System.out.println("---> Relax of edge (" + vert_num + "," + e.getPointeeValue() + ") FAILED");
		}
		
	        }
	
	}
	
	Queue q = new Queue(size);
	
	
	//last iteration: checks for negative loops
	for(vert_num=1; vert_num <= size; vert_num++)
	        {
		edges = g.getVertexByValue(vert_num).getEdges();
		
		for(Edge e : edges)
		{
		        if(relax(vert_num, e.getPointeeValue(), e.getWeight()))
		        {
			//setDistanceOfVertex(e.getPointeeValue(),Integer.MIN_VALUE);
			if(e.getWeight()<=0) {
			        q.push(e.getPointee());
			}
			//iterateNegativeCycle(e.getPointeeValue());
			//seguir caminhos negativos a partir do vertice ate o encontrar de novo
		        }
		}
		
	        }
	
	while(!(q.isEmpty()))
	{
	        //System.out.println("Going into queue...");
	        q.get();
	}
	
	printOutput();
	
	
	
        }
        
        
         /**
         * Prints the distance array.
         */
        public void print()
        {
	int i;
        
	System.out.println("==========PRINTING DISTANCE ARRAY============");
	for(i=0; i<distance.length; i++)
	{
	        System.out.println("d[" + i + "] or vertex " + (i+1) + ": Distance: " + distance[i]);
	}
	        
	System.out.println("============================================");
        }
}