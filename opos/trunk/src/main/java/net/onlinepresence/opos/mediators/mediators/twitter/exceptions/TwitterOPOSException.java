package net.onlinepresence.opos.mediators.mediators.twitter.exceptions;

public class TwitterOPOSException extends Exception {

	private static final long serialVersionUID = -8569573880946653340L;
	
	public TwitterOPOSException(String msg){
		super(msg);
	}
	
	public TwitterOPOSException(Exception cause){
		super(cause);
	}
}
