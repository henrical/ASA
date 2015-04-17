import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
	
	static int n; // vertexNum (including origin)
	static int c; // edgeNum 
	static int originNodeId;
	static int[][] edgesId; // [0] = u vertex, [1] = v vertex, [2] = w loss
	
	public static void main(String[] args){
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			String[] inputArr = input.split(" ");
			
			n = Integer.parseInt(inputArr[0]);
			c = Integer.parseInt(inputArr[1]);
			
			input = br.readLine();
			originNodeId = Integer.parseInt(input);
			
			edgesId = new int[c][3];
			
			for(int i = 0; i < c; i++){
				input = br.readLine();
				inputArr = input.split(" ");
				
				edgesId[i][0] = Integer.parseInt(inputArr[0]);
				edgesId[i][1] = Integer.parseInt(inputArr[1]);
				edgesId[i][2] = Integer.parseInt(inputArr[2]);
			}
			
		} catch (IOException e) {
			System.out.println("Incorrect input given");
			System.exit(500);
		} catch(NullPointerException e){
			System.out.println("Input for list of edges incorrect");
			System.exit(404);
		}
		
		EdgeList edges = new EdgeList();
		
		for(int[] e : edgesId){
			int originVertex = e[0];
			int destVertex = e[1];
			int weight = e[2];
			
			Edge2 nwEdge = new Edge2(originVertex, destVertex, weight);
			edges.add(nwEdge);
		}

		List<Integer> results = BellmanFord.run(n, edges, originNodeId);
		
		for(int val : results){
			if(val == Integer.MIN_VALUE)
				System.out.println("I");
			else if(val == Integer.MAX_VALUE)
				System.out.println("U");
			else
				System.out.println(val);
		}
	}
}