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

	public static int[][] run(Vertex[] vertices, Vector<Edge> edges, Vertex origin) /*throws NegativeCycleException*/{

		int distance[] = new int[vertices.length];
		int predecessor[] = new int[vertices.length];

		/*
		 * Initialization
		 */
		for(Vertex v : vertices){
			if(v.equals(origin)){
				distance[v.getValue() - 1] = 0;
			}else{
				distance[v.getValue() - 1] = Integer.MAX_VALUE;
			}
			predecessor[v.getValue() - 1] = Integer.MAX_VALUE;
		}

		/*
		 * Relaxation
		 */
		/*for(Vertex v : vertices){
			int thisValue = v.getValue();
			Edge[] thisEdges = v.getEdges();
			// para cada aresta que liga (u,v) e tem peso w
			for(Edge e : thisEdges){
				int w = e.getWeight();
				int pointValue = e.getPointee().getValue();
				if(distance[thisValue - 1] == Integer.MAX_VALUE)
					continue;
				int theDistance = distance[thisValue - 1] + w;
				if(theDistance < distance[pointValue - 1]){
					distance[pointValue - 1] = distance[thisValue - 1] + w;
					predecessor[pointValue - 1] = thisValue;
				}
			}
		}*/

		int currentValue = origin.getValue();
		boolean[] visited = new boolean[vertices.length];
		boolean[] inNegative = new boolean[vertices.length];
		Vector<Integer> nextToCheck = new Vector<Integer>();

		nextToCheck.add(currentValue);

		for(;;){ // até passar por todo o grafo
			try{
				currentValue = nextToCheck.get(0);
				nextToCheck.remove(0);
				Vertex currentVertex = vertices[currentValue - 1];
				Edge[] myEdges = currentVertex.getEdges();
				
				visited[currentValue - 1] = true;
				
				for(Edge e : myEdges){ // para cada aresta que parte de um dado nó
					Vertex pointee = e.getPointee();
					int pointeeValue = pointee.getValue();
					int weight = e.getWeight();

					if(visited[pointeeValue - 1] && inNegative[pointeeValue - 1]){
						continue;
					}
					
					if(visited[pointeeValue - 1] && distance[currentValue - 1] + weight < 0){
						// ciclo negativo
						inNegative[pointeeValue - 1] = true;
					}

					if(distance[currentValue - 1] + weight < distance[pointeeValue - 1]){
						distance[pointeeValue - 1] = distance[currentValue - 1] + weight;
					}
					//if(!nextToCheck.contains(pointeeValue) /*&& !(distance[currentValue - 1] + weight < 0)*/)
						nextToCheck.add(pointeeValue);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}

		/*
		 * Negative cycle check
		 */

		/*for(Vertex v : vertices){
			Edge[] thisEdges = v.getEdges();
			int thisValue = v.getValue();
			for(Edge e : thisEdges){
				int w = e.getWeight();
				int pointValue = e.getPointee().getValue();
				if(distance[thisValue - 1] == Integer.MAX_VALUE)
					continue;
				if(distance[thisValue - 1] + w < distance[pointValue - 1]){
					//throw new NegativeCycleException("The graph has a negative weight cycle");
					// ver se o ciclo negativo é atingível a partir da raiz
				}
			}
		}*/

		int[][] results = new int[2][vertices.length];
		
		for(int i = 0; i < inNegative.length; i++){
			if(inNegative[i])
				results[1][i] = 1;
			else
				results[1][i] = 0;
		}

		results[0] = distance;
		//results[1] = predecessor;

		return results;
	}
}