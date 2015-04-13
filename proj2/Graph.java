public class Graph {

	private Vertex list[];
	private int size;

	public Graph(int size)
	{
		int i;

		list = new Vertex[size];
		this.size = size;

		for(i=0; i<size; i++)
			list[i] = new Vertex(i+1);
	}

	public int getSize()
	{
		return size;
	}

	/*
	 * Returns the Vertex with the given value.
	 * Values are [1,...,size]	
	 */
	private Vertex getVertexByValue(int value)
	{
		return list[value-1];
	}

	/*
	 * Adds an edge of weight 'weight' between 'orig' and 'dest'
	 */
	void addEdge(int orig, int dest, int weight)
	{
		Vertex temp_orig = getVertexByValue(orig);
		Vertex temp_dest = getVertexByValue(dest);
		
		temp_orig.addEdge(weight,temp_dest);
	}

	void print()
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
	/*public void print()
	{
		int i;

		for(i = 0; i<size; i++)
		{
			System.out.println("Value of Vertex at " + i + " is: " + list[i].getValue() );
			System.out.println("#################");
		}
	}*/




	
}