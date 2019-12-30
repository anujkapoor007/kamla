package exception;

public class UnsupportedVersionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsupportedVersionException() {
		super();
	}
	
	public UnsupportedVersionException(String message) {
		super(message);
	}

}
