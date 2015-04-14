import java.lang.Integer;

public class BellmanFord
{
        private int distance[];
	
        public BellmanFord(Graph g)	
        {
	distance = new int[g.getSize()];
        }
        
        /**
         * Returns the distance of a given vertex.
         */
        public int getDistanceOfVertex(int vertex)
        {
	return distance[vertex-1];
        }
        
        /**
         * Sets a new distance to a given vertex.
         */
        public void setDistanceOfVertex(int vertex, int val)
        {
	distance[vertex-1] = val;
        }
        
        /**
         * Initializes de distance array.
         * All vertices get value INFINITE except the source vertex.
         */
        public void initializeSingleSource(Graph g, int source)
        {
	int i;
        
	for(i=0; i<distance.length ; i++)
	        distance[i] = Integer.MAX_VALUE ;
	     
	distance[source-1] = 0;        
        }
        
        
        /**
         * Relaxes the edge. 
         * Returns true if it did something, false otherwise.
         */
        public boolean relax(int orig, int dest, int weight)
        {
	if(getDistanceOfVertex(orig)<getDistanceOfVertex(dest))
	{
	        setDistanceOfVertex(orig, getDistanceOfVertex(dest) + weight);
	        return true;
	}
        
	return false;
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