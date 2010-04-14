package org.goodoldai.demo.twitt2opo.pages;

import java.io.IOException;
import java.net.URL;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.goodoldai.demo.twitt2opo.converter.Converter;

public class Show {

	@SessionState
	private Converter twitt;

	@SetupRender
	void initialize(){
		twitt = new Converter();
	}
	
	@OnEvent(component = "oAuthLogin")
	Object onAuthLogin() {

		String linkString = twitt.getOAuthAuthorizationURL();

		URL link = null;
		try {
			link = new URL(linkString);
		} catch (IOException e) {

		}
		return link;
	}
}