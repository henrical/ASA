public class Graph {

	private Vertex list[];
	private int size;

	public Graph(int size)
	{
		list = new Vertex[size];
		this.size = size;
	}

	public void print()
	{
		int i;

		for(i = 0; i<size; i++)
		{
			System.out.println("#################");
			System.out.println("Value of Vertex at " + i + " is: " + list[i].getValue() );
			System.out.println("#################");
		}
	}


	
}