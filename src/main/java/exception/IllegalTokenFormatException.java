package exception;

public class IllegalTokenFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IllegalTokenFormatException() {
		super();
	}
	
	public IllegalTokenFormatException(String message) {
		super(message);
	}

}
