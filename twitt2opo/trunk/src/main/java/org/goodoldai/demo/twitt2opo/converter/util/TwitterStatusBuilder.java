package org.goodoldai.demo.twitt2opo.converter.util;

import org.goodoldai.demo.twitt2opo.converter.model.TwitterStatus;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterStatusBuilder {
	
	private Twitter twitter;
	private User user;
	
	public TwitterStatusBuilder(Twitter twitter, User user){
		this.twitter = twitter;
		this.user = user;
	}

	public TwitterStatus buildStatus() throws TwitterException {
		
		TwitterStatus status = new TwitterStatus();
		
		Twitter4jStatusWrapper statusWrapper = new Twitter4jStatusWrapper(twitter.showStatus(user.getStatus().getId()));

		status.setId(statusWrapper.getId());
		status.setText(statusWrapper.getText());
		status.setCreatedAt(statusWrapper.getCreatedAt());		
		status.setStatusUrl(statusWrapper.getStatusURL(user.getScreenName()));
		status.setReplyOfStatusUrl(statusWrapper.getStatusReplyOfStatusUrl());

		return status;
	}

}
