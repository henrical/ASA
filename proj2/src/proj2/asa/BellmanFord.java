package proj2.asa;

import java.util.Vector;

public class BellmanFord {
	
	private static int distance[];

	/*public BellmanFord(Graph g) {
		distance = new int[g.getSize()];
	}*/

	public static void initializeSingleSource(Graph g, int source) {
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
	}
	
	public static int[][] run(Vertex[] vertices, Vector<Edge> edges, Vertex origin) throws NegativeCycleException{
		
		int distance[] = new int[vertices.length];
		int predecessor[] = new int[vertices.length];
		
		/*
		 * Initialization
		 */
		for(Vertex v : vertices){
			if(v.equals(origin)){
				distance[v.getValue() - 1] = 0;
			}else{
				distance[v.getValue() - 1] = Integer.MAX_VALUE - 1;
			}
			predecessor[v.getValue() - 1] = Integer.MAX_VALUE - 1;
		}
		
		/*
		 * Relaxation
		 */
		//for(int i = 0; i < vertices.length; i++){
		for(Vertex v : vertices){
			int thisValue = v.getValue();
			Edge[] thisEdges = v.getEdges();
			// para cada aresta que liga (u,v) e tem peso w
			for(Edge e : thisEdges){
				int w = e.getWeight();
				int pointValue = e.getPointee().getValue();
				int theDistance = distance[thisValue - 1] + w;
				if(theDistance < distance[pointValue - 1]){
					distance[pointValue - 1] = distance[thisValue - 1] + w;
					predecessor[pointValue - 1] = thisValue;
				}
			}
		}
		
		/*
		 * Negative cycle check
		 */
		for(Vertex v : vertices){
			Edge[] thisEdges = v.getEdges();
			int thisValue = v.getValue();
			for(Edge e : thisEdges){
				int w = e.getWeight();
				int pointValue = e.getPointee().getValue();
				if(distance[thisValue] + w < distance[pointValue]){
					throw new NegativeCycleException("The graph has a negative weight cycle");
				}
			}
		}
		
		int[][] results = new int[2][];
		
		results[0] = distance;
		results[1] = predecessor;
		
		return results;
	}
}