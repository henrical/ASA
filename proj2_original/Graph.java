/**
 * Represents a graph
 * @author Henrique Caldeira
 *
 */
public class Graph {

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