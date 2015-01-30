package exceptions;

public class LocalException extends Exception {

	String s = null;
	
	public LocalException(String s) {
		this.s = s;
	}
	
	public String getMessage() {
		return this.s;
	}
	
}
