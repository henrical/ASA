public class Edge {

	private final int weight;
	private Vertex pointee; //the vertex it points/leads to

	public Edge(int weight)
	{
		this.weight = weight;
		this.pointee = null;
	}

	public Edge(int weight, Vertex pointee)
	{
		this.weight = weight;
		this.pointee = pointee;
	}

	public int getWeight()
	{
		return weight;
	}

	public Vertex getPointee()
	{
		return pointee;
	}

	public void setPointee(Vertex pointee)
	{
		this.pointee = pointee;
	}

	public void print()
	{
	 	System.out.println("Edge with weight << " + getWeight() + " >> to vertex << " + pointee.getValue() + " >>" );
	}

}