package org.goodoldai.demo.twitt2opo.pages;

import java.io.IOException;
import java.net.URL;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.SessionState;
import org.goodoldai.demo.twitt2opo.converter.Converter;
import org.goodoldai.demo.twitt2opo.converter.exceptions.Twitt2opoException;

public class Redirection {

	@SessionState
	private Converter twitt;
	
	@InjectPage
	private Error errorPage;

	@OnEvent(component = "showOpoFile")
	Object onShowOpoFile() {

			try {
				twitt.convert();
			} catch (Twitt2opoException e1) {
				errorPage.setErrorMessage(e1.getMessage());
				return errorPage;
			}

			String linkString = twitt.getAbsoluteUrl();
			String realLink = linkString.substring(0, linkString.length()-1);
			URL link = null;
			try {
				link = new URL(realLink);
			} catch (IOException e) {
				return errorPage;
			}
			return link;

	}
}
