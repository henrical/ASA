import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;




public class Main {

	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = new String();
		String string_buffer[] = new String[3];
		int int_buffer[] = new int[3];
		int i,j;

		//our graph
		Graph g;

		//the total number of edges
		int edge_num;

		//the first vertex to be traversed
		int source_vertex;

		//read first line
		try{
		        input = in.readLine();
		}
		catch(IOException excep)
		{
		        System.exit(1);
		}

		string_buffer = input.split(" ");

		for(i=0; i<2; i++)
		        int_buffer[i] = Integer.parseInt(string_buffer[i]);

		//second position of buffer contains the number of edges given by input
		edge_num = int_buffer[1]; 

		//first position contains the number of vertices in input
		g = new Graph(int_buffer[0]);





		//read the second line
		try{
		        input = in.readLine();
		}
		catch(IOException excep)
		{
		        System.exit(1);
		}

		source_vertex = Integer.parseInt(input);


		// read another 'edge_num' amount of lines and add the edges
		i = 0;
		while(i<edge_num)
		{
			System.out.println("Index at " + i + ", max is " + edge_num);
			try{
			        input = in.readLine();
			}
			catch(IOException excep)
			{
			        System.exit(1);
			}

			string_buffer = input.split(" ");

			for(j=0; j<3; j++)
			        int_buffer[j] = Integer.parseInt(string_buffer[j]);

			g.addEdge(int_buffer[0],int_buffer[1],int_buffer[2]);
			
			i++;
			
		}
		
		
		BellmanFord bf = new BellmanFord(g);
		
		bf.initializeSingleSource(g,source_vertex);
		bf.print();
		//g.print();
		

	}

	
}