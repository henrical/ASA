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
		int predecessor[] = new int[n]; // para o caso de haver ciclos negativos podermos identifica-los

		/*
		 * Initialization
		 */
		for(int i = 0; i < distance.length; i++){
			distance[i] = Integer.MAX_VALUE;
			predecessor[i] = Integer.MAX_VALUE;
		}
		distance[originValue - 1] = 0;
		
		for(int i = 1; i < n -1; i++){
			for(Edge2 e : edges){
				if(distance[e.getSource() - 1] == Integer.MAX_VALUE)
					continue;
				if(distance[e.getSource() - 1] + e.getWeight() < distance[e.getDestination() - 1]){
					distance[e.getDestination() - 1] = distance[e.getSource() - 1] + e.getWeight();
					predecessor[e.getDestination() - 1] = e.getSource();
				}
			}
		}
		
		List<Integer> negativeCycleVertices = new ArrayList<Integer>();
		for(Edge2 e : edges){
			if(distance[e.getSource() - 1] + e.getWeight() < distance[e.getDestination() - 1]){
				// ciclo negativo
				negativeCycleVertices.add(e.getSource());
			}
		}
		
		while(!negativeCycleVertices.isEmpty()){
			int infected = negativeCycleVertices.get(0);
			negativeCycleVertices.remove(0);
			int pred = predecessor[infected - 1];
			List<Integer> forward = new ArrayList<Integer>(); // fica com o caminho negativo
			forward.add(infected);
			do{
				forward.add(pred);
				pred = predecessor[pred - 1]; 
			}while(pred != infected);
			
			for(int p : forward){
				distance[p - 1] = Integer.MIN_VALUE;
			}
		}
		
		// verificar que nenhum dos nos com distancia != -inf esta ligado a predecessores com distancia == -inf
		
		for(int i = 0; i < n; i++){
			int pred = predecessor[i];
			do{
				if(pred == Integer.MAX_VALUE)
					break;
				pred = predecessor[pred - 1];
				if(pred == Integer.MAX_VALUE)
					break;
				if(distance[pred - 1] == Integer.MIN_VALUE){
					distance[i] = Integer.MIN_VALUE;
					break;
				}
			}while(pred != i);
		}
		
		return distance;
	}
}
