import java.lang.Integer;

public class BellmanFord
{
        private int distance[];
	
        public BellmanFord(Graph g)	
        {
	distance = new int[g.getSize()];
        }
        
        
        public void initializeSingleSource(Graph g, int source)
        {
	int i;
        
	for(i=0; i<distance.length ; i++)
	        i = Integer.MIN_VALUE - 1 ;
	     
	distance[source-1] = 0;        
        }
        
        public void print()
        {
	int i;
        
	System.out.println("==========PRINTING DISTANCE ARRAY============");
	for(i=0; i<distance.length; i++)
	{
	        System.out.println("Position " + i + ": Distance: " + distance[i]);
	}
	System.out.println("============================================");
        }
}