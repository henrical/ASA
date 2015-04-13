import java.util.Vector;

public class Vertex {

	private final int value;
	private Vector<Edge> edges;	

	public Vertex()
	{
		value = -1;
		edges = new Vector<Edge>();
	}

	public Vertex(int val)
	{
		this.value = val;
		edges = new Vector<Edge>();
	}

	public int getValue()
	{
		return value;
	}

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

	public void addEdge(int weight, Vertex destination)
	{
		Edge edge = new Edge(weight,destination);
		edges.add(edge);
	}


}