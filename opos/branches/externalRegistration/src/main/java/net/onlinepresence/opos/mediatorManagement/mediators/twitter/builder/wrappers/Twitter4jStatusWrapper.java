package net.onlinepresence.opos.mediatorManagement.mediators.twitter.builder.wrappers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import twitter4j.Status;

public class Twitter4jStatusWrapper {

	private Status status;

	public Twitter4jStatusWrapper(Status status) {
		this.status = status;
	}

	public String getId() {
		String id = null;
		
		try {
			id = String.valueOf(status.getId());
		} catch (Exception e) {

		}
		return id;
	}

	public String getText(){
		String text = null;
		
		try {
			text = status.getText();
		} catch (Exception e) {

		}
		return text;
	}
	
	public Date getCreatedAt(){
		Date createdAt = null;
		
		try {
			createdAt = status.getCreatedAt();
		} catch (Exception e) {

		}
		return createdAt;
	}
	
	public URL getStatusURL(String screenName) {
		URL url = null;
		
		try {
			url = new URL("http://twitter.com/" + screenName + "/statuses/" + status.getId());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	public URL getStatusReplyOfStatusUrl() {
		URL url = null;

		if (status.getInReplyToStatusId() > 0 ) {
			try {
				url = new URL("http://twitter.com/" + status.getInReplyToScreenName()
						+ "/statuses/" + status.getInReplyToStatusId());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
	
	public URL getStatusReplyOfStatusContent() {
		URL url = null;

		if (status.getInReplyToStatusId() > 0 ) {
			try {
				url = new URL("http://twitter.com/" + status.getInReplyToScreenName()
						+ "/statuses/" + status.getInReplyToStatusId());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
}
