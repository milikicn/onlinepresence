package org.goodoldai.demo.twitt2opo.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.goodoldai.demo.twitt2opo.converter.Converter;
import org.goodoldai.demo.twitt2opo.converter.exceptions.Twitt2opoException;

public class Show {

	@SessionState
	private Converter twitt;

	@InjectPage
	private Error errorPage;

	@Property
	private String username;

	@SetupRender
	Object initialize() {
		try {
			twitt = new Converter();
			return null;
		} catch (Twitt2opoException e) {
			errorPage.setErrorMessage(e.getMessage());
			return errorPage;
		}
	}

	Object onSubmitFromConvertForm() {
		twitt.setScreenName(username.trim());

		try {
			if (twitt.userExists()) {
				return twitt.checkIfProtectedAndReturnUrl();
			} else {
				errorPage
						.setErrorMessage("The user you have requested the conversion for doesn't exist. Please check the profile name.");
				return errorPage;
			}
		} catch (Twitt2opoException e) {
			errorPage.setErrorMessage(e.getMessage());
			return errorPage;
		}

	}

}