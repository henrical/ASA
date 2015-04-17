import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BellmanFord {
	public static Vector<Integer> run(int n, EdgeList edges, int originValue){

		Vector<Integer> distance = new Vector<Integer>();
		Vector<Integer> predecessor = new Vector<Integer>(); // para o caso de haver ciclos negativos podermos identifica-los

		/*
		 * Initialization
		 */
		for(int i = 0; i < n; i++){
			distance.add(Integer.MAX_VALUE);
			predecessor.add(Integer.MAX_VALUE);
		}
		distance.set(originValue - 1, 0);
		
		for(int i = 1; i < n -1; i++){
			for(Edge2 e : edges){
				if(distance.get(e.getSource() - 1) == Integer.MAX_VALUE)
					continue;
				if(distance.get(e.getSource() - 1) + e.getWeight() < distance.get(e.getDestination() - 1)){
					distance.set(e.getDestination() - 1, distance.get(e.getSource() - 1) + e.getWeight());
					predecessor.set(e.getDestination() - 1, e.getSource());
				}
			}
		}
		
		List<Integer> negativeCycleVertices = new ArrayList<Integer>();
		for(Edge2 e : edges){
			//if(distance[e.getSource() - 1] + e.getWeight() < distance[e.getDestination() - 1]){
			if(distance.get(e.getSource() - 1) + e.getWeight() < distance.get(e.getDestination() - 1)){
				// ciclo negativo
				negativeCycleVertices.add(e.getSource());
			}
		}
		
		while(!negativeCycleVertices.isEmpty()){
			int infected = negativeCycleVertices.get(0);
			negativeCycleVertices.remove(0);
			if(predecessor.get(infected - 1) == Integer.MAX_VALUE)
				continue;
			int pred = predecessor.get(infected - 1);
			List<Integer> forward = new ArrayList<Integer>(); // fica com o caminho negativo
			forward.add(infected);
			do{
				forward.add(pred);
				pred = predecessor.get(pred - 1);
			}while(pred != infected);
			
			for(int p : forward){
				distance.set(p - 1, Integer.MIN_VALUE);
			}
		}
		
		// verificar que nenhum dos nos com distancia != -inf esta ligado a predecessores com distancia == -inf
		
		for(int i = 0; i < n; i++){
			int pred = predecessor.get(i);
			do{
				if(pred == Integer.MAX_VALUE)
					break;
				pred = predecessor.get(pred - 1);
				if(pred == Integer.MAX_VALUE)
					break;
				if(distance.get(pred - 1) == Integer.MIN_VALUE){
					distance.set(i, Integer.MIN_VALUE);
					break;
				}
			}while(pred != i);
		}
		
		return distance;
	}
}
