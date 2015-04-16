
public class Edge2 {

	final int source;
	final int destination;
	final int weight;
	
	public Edge2(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public int getSource() {
		return source;
	}
	
	public int getDestination() {
		return destination;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString(){
		return "(" + source + " -> " + destination + ", w: " + weight + ")";
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Edge2))
			return false;
		Edge2 obj = (Edge2) o;
		return source == obj.source && destination == obj.destination && weight == obj.weight;
	}
	
}
