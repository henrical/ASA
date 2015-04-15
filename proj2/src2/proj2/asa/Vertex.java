package proj2.asa;
import java.util.Vector;

/**
 * Represents a vertex of the graph
 * @author Henrique Caldeira
 *
 */
public class Vertex {

	/**
	 * The value of this vertex
	 */
	private final int value;
	
	/**
	 * The vector of edges that connect to this vertex
	 */
	private Vector<Edge> edges;	

	/**
	 * Vertex constructor with no value
	 */
	public Vertex()
	{
		value = -1;
		edges = new Vector<Edge>();
	}

	/**
	 * Vertex constructor with value val
	 */
	public Vertex(int val)
	{
		this.value = val;
		edges = new Vector<Edge>();
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