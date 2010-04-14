package org.goodoldai.demo.twitt2opo.pages;

import java.io.IOException;
import java.net.URL;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.goodoldai.demo.twitt2opo.converter.Converter;

public class Redirection{

	@SessionState
	private Converter twitt;
	
	@OnEvent(component = "showOpoFile")
	Object onShowOpoFile() {
		twitt.convert();
		String linkString = twitt.getAbsoluteUrl();
		URL link = null;
		try {
			link = new URL(linkString);
		} catch (IOException e) {

		}
		return link;
	}
}
