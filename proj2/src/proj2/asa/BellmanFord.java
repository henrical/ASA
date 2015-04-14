package proj2.asa;

import java.lang.Integer;

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
	
	public static void run(Vertex[] vertices, Edge[] edges, Vertex origin){
		
		int distance[] = new int[edges.length];
		int predecessor[] = new int[edges.length];
		
		/*
		 * Initialization
		 */
		for(Vertex v : vertices){
			if(v.equals(origin)){
				distance[v.getValue()] = 0;
			}else{
				distance[v.getValue()] = Integer.MIN_VALUE;
			}
			predecessor[v.getValue()] = Integer.MIN_VALUE;
		}
		
		/*
		 * Relaxation
		 */
		for(int i = 0; i < vertices.length; i++){
			Edge[] thisEdges = vertices[i].getEdges();
			// para cada aresta que liga (u,v) e tem peso w
			for(Edge e : thisEdges){
				if(e.getPointee().equals(obj))
			}
		}
	}
}