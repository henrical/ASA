package proj2.asa;

public class NegativeCycleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegativeCycleException(){
		super();
	}
	
	public NegativeCycleException(String message){
		super(message);
	}

}
