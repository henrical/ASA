import java.lang.Integer;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Vector;


class BellmanFord
{
        

        private int distance[];
        private int predecessor[];
        private final int size;
        private int source;
	
        public BellmanFord(Graph g)	
        {
	size = g.getSize();
	distance = new int[size];
	predecessor = new int[size];
        }
        
        /**
         * Returns the distance of a given vertex.
         */
        private int getDistanceOfVertex(int vertex)
        {
	return distance[vertex-1];
        }
        
        /**
         * Sets a new distance to a given vertex.
         */
        private void setDistanceOfVertex(int vertex, int val)
        {
	distance[vertex-1] = val;
        }
        
            /**
         * Returns the predecessor of a given vertex.
         */
        private int getPredecessorOfVertex(int vertex)
        {
	return predecessor[vertex-1];
        }
        
        /**
         * Sets a new predecessor to a given vertex.
         */
        private void setPredecessorOfVertex(int vertex, int val)
        {
	predecessor[vertex-1] = val;
        }
        
        /**
         * Initializes de distance array.
         * All vertices get value INFINITE except the source vertex.
         */
        private void initializeSingleSource(int source)
        {
	int i;
	
	this.source = source;
        
	for(i=0; i<size ; i++)
	        distance[i] = Integer.MAX_VALUE ;
	     
	setDistanceOfVertex(source,0);
	setPredecessorOfVertex(source,0);
        }
        
        
        /**
         * Relaxes the edge. 
         * Returns true if it did something, false otherwise.
         */
        public boolean relax(int orig, int dest, int weight)
        {
	if(getDistanceOfVertex(orig) + weight <getDistanceOfVertex(dest))
	{
	        setDistanceOfVertex(dest, getDistanceOfVertex(orig) + weight);
	        setPredecessorOfVertex(dest, orig);
	        return true;
	}
        
	return false;
        }
        
        private void printOutput()
        {
	int i;
	
	for(i=1; i<= size; i++)
	{
	        switch (getDistanceOfVertex(i)) {
		case Integer.MAX_VALUE: 
		        System.out.println("U");
		        break;
		case Integer.MIN_VALUE: 
		        System.out.println("I");
		        break;
		default: 
		        System.out.println(getDistanceOfVertex(i));
		        break;
	        }
	}
        }
        
        /**
         * Runs the algorithm.
         *
         */
        public void run(Graph g, int source)
        {
	int iterations;
	int vert_num;
	int dist;
	Edge edges[];
	
	initializeSingleSource(source);
	
	for(iterations=0; iterations < size-1; iterations++)
	{
	        innerFor: for(vert_num=1; vert_num <= size; vert_num++)
	        {
		if(getDistanceOfVertex(vert_num) == Integer.MAX_VALUE)
			continue innerFor;
	        
		edges = g.getVertexByValue(vert_num).getEdges();
		
		for(Edge e : edges)
		{       
		        relax(vert_num, e.getPointeeValue(), e.getWeight());     
			//System.out.println("---> Relax of edge (" + vert_num + "," + e.getPointeeValue() + ") SUCCEDED");
		        //else
			//System.out.println("---> Relax of edge (" + vert_num + "," + e.getPointeeValue() + ") FAILED");
		}
		
	        }
	
	}
	
	Queue q = new Queue(size);
	
	
	//last iteration: checks for negative loops
	for(vert_num=1; vert_num <= size; vert_num++)
	        {
		edges = g.getVertexByValue(vert_num).getEdges();
		
		for(Edge e : edges)
		{
		        if(relax(vert_num, e.getPointeeValue(), e.getWeight()))
		        {
			//setDistanceOfVertex(e.getPointeeValue(),Integer.MIN_VALUE);
			if(e.getWeight()<=0) {
			        q.push(e.getPointee());
			}
			//iterateNegativeCycle(e.getPointeeValue());
			//seguir caminhos negativos a partir do vertice ate o encontrar de novo
		        }
		}
		
	        }
	
	while(!(q.isEmpty()))
	{
	        //System.out.println("========== Iterating Negative Cicle: =============");
	        Vertex v = q.get();
	        
	        //System.out.println("Going into queue...");
	        if(!(v.inNegativeCycle()))
		iterateNegativeCycle(g,v,size);
	        else
	        {
		//system.out.println("Skipping vertex " + v.getValue());
		continue;
	        }
	        
	}
	
	setDistanceOfVertex(source,0);
	
	printOutput();
        }
        
         /**
          * Iterates through a negative cycle and marks all vertices that are part of it.
          */
        private void iterateNegativeCycle(Graph g,Vertex v, int size)
        {
	int temp;
	int val = v.getValue();
	int pred;
	int i = 0;
	Stack<Integer> vertices_to_mark = new Stack<Integer>();
	int visited[] = new int[size];
	
	visited[val-1] = 1;
	vertices_to_mark.push(val);
	i++;
	
	pred = getPredecessorOfVertex(val);
	//System.out.println("Val is " + val + ".");
	do
	{
	        if(pred == 0)
		return;
	
	        //if it can't assign vertex as part of negative cycle, its because its already part of another cycle
	        if(g.getVertexByValue(pred).inNegativeCycle())
		return;
	
	        if(visited[pred-1]==1)
		break;
	        
	        
	        visited[pred-1] = 1;

	        vertices_to_mark.push(pred);
	        i++;
	        //System.out.println("Backtracking from " + pred + ".");
	        pred = getPredecessorOfVertex(pred);
	        //System.out.println("Backtracking to " + pred + ".");
	} while(pred != val);
	
	while(!vertices_to_mark.empty())
	{
	       temp = vertices_to_mark.pop();
	       setDistanceOfVertex(temp,Integer.MIN_VALUE);
	       g.getVertexByValue(temp).assignNegativeCycle();
	       
	}
	
        }
        
        
         /**
         * Prints the distance array.
         */
        public void print()
        {
	int i;
        
	System.out.println("==========PRINTING DISTANCE ARRAY============");
	for(i=0; i<distance.length; i++)
	{
	        System.out.println("d[" + i + "] or vertex " + (i+1) + ": Distance: " + distance[i]);
	}
	        
	System.out.println("============================================");
        }
}/**
 * Represents an edge of the graph
 * @author Henrique Caldeira
 *
 */
class Edge {

	/**
	 * The weight of this edge
	 */
	private final int weight;
	
	/**
	 * The vertex this edge points to
	 */
	private Vertex pointee; //the vertex it points/leads to

	/**
	 * Edge constructor with no vertex to point to
	 * @param weight The weight of this edge
	 */
	public Edge(int weight)
	{
		this.weight = weight;
		this.pointee = null;
	}

	/**
	 * Edge constructor
	 * @param weight The weight of this edge
	 * @param pointee The vertex this edge points to
	 */
	public Edge(int weight, Vertex pointee)
	{
		this.weight = weight;
		this.pointee = pointee;
	}

	/**
	 * Returns the weight of this edge
	 * @return Integer representing the weight of this edge
	 */
	public int getWeight()
	{
		return weight;
	}

	/**
	 * Returns the vertex this edge points to
	 * @return value of vertex this edge points to
	 */
	public int getPointeeValue()
	{
		return pointee.getValue();
	}
	
	/**
	 * Returns the vertex this edge points to
	 * @return Vertex object where this edge points to
	 */
	public Vertex getPointee()
	{
		return pointee;
	}

	/**
	 * Sets the edge this vertex points to
	 * @param pointee Vertex object this vertex points to
	 */
	public void setPointee(Vertex pointee)
	{
		this.pointee = pointee;
	}

	/**
	 * Prints to the console a representation of this edge
	 */
	public void print()
	{
	 	System.out.println("Edge with weight ||" + getWeight() + "|| ------------> to vertex << " + pointee.getValue() + " >>" );
	}
	
	/**
	 * Returns a representation of this edge
	 */
	@Override
	public String toString(){
		return "(w: " + weight + ", to: " + pointee.getValue() + ")";
	}

}/**
 * Represents a graph
 * @author Henrique Caldeira
 *
 */
class Graph {

	/**
	 * A list of vertexes contained in this graph
	 */
	private Vertex list[];
	/**
	 * The number of vertexes this graph has
	 */
	private int size;

	/**
	 * Graph constructor
	 * @param size The number of vertexes this graph has
	 */
	public Graph(int size)
	{
		int i;

		list = new Vertex[size];
		this.size = size;

		for(i=0; i<size; i++)
			list[i] = new Vertex(i+1);
	}

	/**
	 * Returns the number of vertexes this graph has
	 * @return
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Returns the Vertex with the given value.
	 * Values are [1,...,size]
	 * @param value The value of the vertex to search for
	 * @return The vertex that has value
	 */
	public Vertex getVertexByValue(int value)
	{
		return list[value-1];
	}
	
	/**
	 * Returns the list of vertices of this graph
	 * @return The list of vertices of this graph
	 */
	public Vertex[] getVertexList(){
		return list;
	}

	/**
	 * Adds an edge of weight 'weight' between 'orig' and 'dest'
	 * @param orig
	 * @param dest
	 * @param weight
	 */
	public void addEdge(int orig, int dest, int weight)
	{
		Vertex temp_orig = getVertexByValue(orig);
		Vertex temp_dest = getVertexByValue(dest);
		
		temp_orig.addEdge(weight,temp_dest);
	}
	
	public boolean checkIfNegativeEdge(int orig, int dest){
		Vertex v_orig = getVertexByValue(orig);
		
		Edge edges[] = v_orig.getEdges();
		
		for(Edge e : edges)
		{
		        if(e.getWeight()<0 && e.getPointeeValue()==dest)
			return true;
		}
		   
		return false;
		
	}

	/**
	 * Prints a representation of this graph to the console
	 */
	public void print()
	{
		int i;

		System.out.println("_____________________________");
		System.out.println("------PRINTING GRAPH---------");
		System.out.println("_____________________________");

		for(i=0; i<size; i++)
		{
			list[i].print();
		}

		System.out.println("_____________________________");
		System.out.println("_____________________________");
		System.out.println("_____________________________");
	}
	
	/**
	 * Returns a representation of this graph
	 */
	@Override
	public String toString(){
		String out = "";
		for(Vertex v : list){
			out += v.toString();
			out += System.lineSeparator();
		}
		return out;
	}
	
}






class Main {

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
		
		bf.run(g,source_vertex);
		//bf.print();
		
		
		//g.print();
		System.exit(0);
		

	}

	
}

class Queue
{
        private int front;
        private int rear;
        
        private Vertex queue[];
        
        public Queue(int size)
        {
	queue = new Vertex[size+1];
	front = 0;
	rear = 0;
        }
        
        public void print()
        {
	int i;
	
	System.out.println("<<<<<<<<<<<<<<<<Printing queue...>>>>>>>>>>>>>>>>>>");
	for(i=0; i<queue.length ; i++)
	{
	        queue[i].print();
	}
	System.out.println("<<<<<<<<<<<<<<<<=================>>>>>>>>>>>>>>>>>>");
        }
        
        public void push(Vertex v)
        {
	if(front==queue.length-1)
	        System.out.println("==========QUEUE SIZE EXCEEDED!!===========");
	        
	queue[front]=v;
	
	//System.out.println("Inserting <<" + v.getValue() + ">>.");
	
	front++;
        }
        
        public Vertex get()
        {
	Vertex result = queue[rear];
	
	//System.out.println("Getting <<" + result.getValue() + ">>.");
	rear = rear + 1;
	return result;
        }
        
        public boolean isEmpty()
        {
	return (front==rear);
        }

        
}

/**
 * Represents a vertex of the graph
 * @author Henrique Caldeira
 *
 */
class Vertex {

	/**
	 * The value of this vertex
	 */
	private final int value;
	
	/**
	 * The vector of edges that connect to this vertex
	 */
	private Vector<Edge> edges;	

	 /**
	  * Shows whether or not this vertex is part of a negative cycle.
	  */
	private int negCycle;
	
	/**
	 * Vertex constructor with no value
	 */
	public Vertex()
	{
		value = -1;
		edges = new Vector<Edge>();
		negCycle = 0;
	}

	/**
	 * Vertex constructor with value val
	 */
	public Vertex(int val)
	{
		this.value = val;
		edges = new Vector<Edge>();
		negCycle = 0;
	}
	
	/**
	 * Returns the value of this vertex
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Returns a vector of edges connecting to this vertex
	 */
	public Edge[] getEdges()
	{
		Edge[] result = new Edge[edges.size()];
		int i;

		for(i=0; i<edges.size(); i++)
		{
			result[i] = edges.get(i);
		}

		return result;
	}
	

	/**
	 * Adds an edge to this vertex
	 */
	public void addEdge(int weight, Vertex destination)
	{
		edges.add(new Edge(weight,destination));
	}
	
	public void addEdge(Edge edge){
		edges.add(edge);
	}
	
	 /**
	  * Returns true if operations succeeds.
	  * Returns false if this vertex is already part of a negative cycle.
	  */
	public void assignNegativeCycle()
	{
	        negCycle = 1;
	}
	
	public boolean inNegativeCycle()
	{
	        if(negCycle==1)
		return true;
	        else
		return false;
	}

	/**
	 * Prints the weight and destination of all connected edges.
	 */
	void print()
	{
		int i;

		System.out.println("++++ Vertex " + getValue() + "++++");

		for(i=0; i<edges.size(); i++)
		{
			edges.get(i).print();
		}
	}
	
	/**
	 * Returns a representation of this vertex
	 */
	@Override
	public String toString(){
		String out = "Vertex " + getValue();
		for(Edge e : edges){
			//e.print();
			out += e.toString();
			out += System.lineSeparator();
		}
		return out;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Vertex))
			return false;
		Vertex obj = (Vertex) o;
		return obj.value == this.value;
	}
}