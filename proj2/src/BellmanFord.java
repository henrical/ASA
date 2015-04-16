import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

	//private static int distance[];

	/*public static void initializeSingleSource(Graph g, int source) {
		int i;

		for (i = 0; i < distance.length; i++)
			i = Integer.MIN_VALUE - 1;

		distance[source - 1] = 0;
	}

	public static void print() {
		int i;

		System.out.println("==========PRINTING DISTANCE ARRAY============");
		for (i = 0; i < distance.length; i++) {
			System.out.println("Position " + i + ": Distance: " + distance[i]);
		}
		System.out.println("============================================");
	}*/

	//public static int[][] run(Vertex[] vertices, Vector<Edge> edges, Vertex origin) /*throws NegativeCycleException*/{
	
	public static int[] run(int n, EdgeList edges, int originValue){

		int distance[] = new int[n];

		/*
		 * Initialization
		 */
		for(int i = 0; i < distance.length; i++)
			distance[i] = Integer.MAX_VALUE;
		distance[originValue - 1] = 0;
		
		for(int i = 1; i < n -1; i++){
			for(Edge2 e : edges){
				if(distance[e.getSource() - 1] + e.getWeight() < distance[e.getDestination() - 1])
					distance[e.getDestination() - 1] = distance[e.getSource() - 1] + e.getWeight();
			}
		}
		
		List<Integer> negativeCycleVertices = new ArrayList<Integer>();
		for(Edge2 e : edges){
			if(distance[e.getSource() - 1] + e.getWeight() < distance[e.getDestination() - 1]){
				// ciclo negativo
				System.out.println("ciclo infinito");
				negativeCycleVertices.add(e.getSource());
			}
		}
		
		List<Integer> ultimateCycleVertices = new ArrayList<Integer>();
		while(!negativeCycleVertices.isEmpty()){
			int source = negativeCycleVertices.get(0);
			negativeCycleVertices.remove(0);
			ultimateCycleVertices.add(source);
			int destination;
		}
		
		return distance;
	}
}
