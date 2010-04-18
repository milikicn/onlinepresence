package org.goodoldai.demo.twitt2opo.converter.exceptions;

public class Twitt2opoException extends Exception {

	private static final long serialVersionUID = -8569573880946653340L;
	
	public Twitt2opoException(String msg){
		super(msg);
	}
	
	public Twitt2opoException(Exception cause){
		super(cause);
	}
}
