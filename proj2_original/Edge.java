/**
 * Represents an edge of the graph
 * @author Henrique Caldeira
 *
 */
public class Edge {

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
	 * @return Vertex object where this edge points to
	 */
	public int getPointeeValue()
	{
		return pointee.getValue();
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

}