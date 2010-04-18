package org.goodoldai.demo.twitt2opo.pages;

import org.apache.tapestry5.annotations.Persist;

public class Error {
	
	@Persist
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
