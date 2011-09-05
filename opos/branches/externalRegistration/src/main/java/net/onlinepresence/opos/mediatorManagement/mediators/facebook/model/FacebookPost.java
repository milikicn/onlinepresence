package net.onlinepresence.opos.mediatorManagement.mediators.facebook.model;

import java.util.Date;

public class FacebookPost {

	private String textPost;
	private Date timePost;
	
	/**
	 * @param textPost
	 * @param timePost
	 */
	public FacebookPost(String textPost, Date timePost) {
		this.textPost = textPost;
		this.timePost = timePost;
	}

	public String getTextPost() {
		return textPost;
	}

	public void setTextPost(String textPost) {
		this.textPost = textPost;
	}

	public Date getTimePost() {
		return timePost;
	}

	public void setTimePost(Date timePost) {
		this.timePost = timePost;
	}

}
