import java.lang.Integer;
import java.util.Stack;

public class BellmanFord
{
        

        private int distance[];
        private int predecessor[];
        private final int size;
        private int source;
	
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
	
	this.source = source;
        
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
		if(getDistanceOfVertex(vert_num) == Integer.MAX_VALUE)
			continue;
	        
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
	        System.out.println("========== Iterating Negative Cicle: =============");
	        Vertex v = q.get();
	        
	        //System.out.println("Going into queue...");
	        if(!(v.inNegativeCycle()))
		iterateNegativeCycle(g,v,size);
	        else
	        {
		System.out.println("Skipping vertex " + v.getValue());
		continue;
	        }
	        
	}
	
	setDistanceOfVertex(source,0);
	
	printOutput();
        }
        
         /**
          * Iterates through a negative cycle and marks all vertices that are part of it.
          */
        private void iterateNegativeCycle(Graph g,Vertex v, int size)
        {
	int temp;
	int val = v.getValue();
	int pred;
	int i = 0;
	Stack<Integer> vertices_to_mark = new Stack<Integer>();
	int visited[] = new int[size];
	
	visited[val-1] = 1;
	vertices_to_mark.push(val);
	i++;
	
	pred = getPredecessorOfVertex(val);
	System.out.println("Val is " + val + ".");
	do
	{
	        //if it can't assign vertex as part of negative cycle, its because its already part of another cycle
	        //if(g.getVertexByValue(pred).inNegativeCycle())
		//return;
		
	        if(visited[pred-1]==1)
		return;
	        
	        
	        visited[pred-1] = 1;
	        vertices_to_mark.push(pred);
	        i++;
	        //System.out.println("Backtracking from " + pred + ".");
	        pred = getPredecessorOfVertex(pred);
	        
	        if(getDistanceOfVertex(pred)==Integer.MAX_VALUE || getDistanceOfVertex(pred)==Integer.MIN_VALUE)
		return;
	        //System.out.println("Backtracking to " + pred + ".");
	} while(pred != val);
	
	while(!vertices_to_mark.empty())
	{
	       temp = vertices_to_mark.pop();
	       setDistanceOfVertex(temp,Integer.MIN_VALUE);
	       g.getVertexByValue(temp).assignNegativeCycle();
	       
	}
	
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