import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

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
		} catch(NullPointerException e){
			System.out.println("Input for list of edges incorrect");
		}
		
		Vertex[] vertices= new Vertex[n];
		
		for(int i = 0; i < vertices.length; i++){
			vertices[i] = new Vertex(i + 1);
		}
		
		//Graph g = new Graph(n);
		Vector<Edge> edges = new Vector<Edge>();
		
		for(int[] e : edgesId){
			int originVertex = e[0];
			int destVertex = e[1];
			int weight = e[2];
			
			Edge nwEdge = new Edge(weight, vertices[destVertex - 1]);
			vertices[originVertex - 1].addEdge(nwEdge);
			edges.add(nwEdge);
			//g.addEdge(originVertex, destVertex, weight);
		}
		
		int[][] results = BellmanFord.run(vertices, edges, vertices[originNodeId - 1]);
		
		if(results[0] != null){
			//for(int line : results[0]){
			for(int i = 0; i < results[0].length; i++){
				if(results[1][i] == 1)
					System.out.println("I");
				else if(results[0][i] == Integer.MAX_VALUE)
					System.out.println("U");
				else
					System.out.println(results[0][i]);
			}
		}
	}
}