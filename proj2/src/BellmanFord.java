import java.util.Vector;

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

	public static int[][] run(Vertex[] vertices, Vector<Edge> edges, Vertex origin) /*throws NegativeCycleException*/{

		int distance[] = new int[vertices.length];

		/*
		 * Initialization
		 */
		for(Vertex v : vertices){
			if(v.equals(origin)){
				distance[v.getValue() - 1] = 0;
			}else{
				distance[v.getValue() - 1] = Integer.MAX_VALUE;
			}
		}

		int currentValue = origin.getValue();
		boolean[] visited = new boolean[vertices.length];
		boolean[] inNegative = new boolean[vertices.length];
		Vector<Integer> nextToCheck = new Vector<Integer>();

		nextToCheck.add(currentValue);

		for(;;){ // ate passar por todo o grafo
			try{
				currentValue = nextToCheck.get(0);
				nextToCheck.remove(0);
				Vertex currentVertex = vertices[currentValue - 1];
				Edge[] myEdges = currentVertex.getEdges();
				
				visited[currentValue - 1] = true;
				
				for(Edge e : myEdges){ // para cada aresta que parte de um dado no
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

					boolean noChange = true;
					if(distance[currentValue - 1] + weight < distance[pointeeValue - 1]){
						distance[pointeeValue - 1] = distance[currentValue - 1] + weight;
						noChange = false;
					}
					//if(!nextToCheck.contains(pointeeValue) /*&& !(distance[currentValue - 1] + weight < 0)*/)
					if(!nextToCheck.contains(pointeeValue) && !noChange)
						nextToCheck.add(pointeeValue);
				}
			}catch(ArrayIndexOutOfBoundsException e){
				break;
			}
		}

		int[][] results = new int[2][vertices.length];
		
		for(int i = 0; i < inNegative.length; i++){
			if(inNegative[i])
				results[1][i] = 1;
			else
				results[1][i] = 0;
		}

		results[0] = distance;

		return results;
	}
}
