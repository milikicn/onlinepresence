package net.onlinepresence.opos.exceptions;

public class OPOSException extends Exception {

	private static final long serialVersionUID = -8569573880946653340L;
	
	public OPOSException(String msg){
		super(msg);
	}
	
	public OPOSException(Exception cause){
		super(cause);
	}
}
