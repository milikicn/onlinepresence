package org.goodoldai.demo.twitt2opo.converter.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.goodoldai.demo.twitt2opo.converter.model.TwitterStatus;


import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterStatusBuilder {

	public static TwitterStatus buildCurrentStatus(Twitter twitter) throws TwitterException{
		User user = twitter.verifyCredentials();
		TwitterStatus status = new TwitterStatus();
		
		twitter4j.Status twitterStatus = twitter.showStatus(user.getStatusId());

		status.setId(String.valueOf(twitterStatus.getId()));

		status.setText(twitterStatus.getText());

		status.setCreatedAt(twitterStatus.getCreatedAt());
		
		status.setStatusUrl(extractStatusURL(user));

		status.setReplyOfStatusUrl(extractStatusReplyOfStatusUrl(twitterStatus));

		return status;
	}

	private static URL extractStatusURL(User user) {
		URL url = null;
		
		try {
			url = new URL("http://twitter.com/" + user.getScreenName() + "/statuses/" + user.getStatusId());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}

	private static URL extractStatusReplyOfStatusUrl(twitter4j.Status twitterStatus) {
		URL url = null;

		if (twitterStatus.getInReplyToStatusId() > 0 ) {
			try {
				url = new URL("http://twitter.com/" + twitterStatus.getInReplyToScreenName()
						+ "/statuses/" + twitterStatus.getInReplyToStatusId());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
}
