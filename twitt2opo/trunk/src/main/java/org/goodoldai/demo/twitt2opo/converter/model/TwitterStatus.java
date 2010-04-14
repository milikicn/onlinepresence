package org.goodoldai.demo.twitt2opo.converter.model;


import java.net.URL;
import java.util.Date;

public class TwitterStatus {

	private Date createdAt;
	private String id;
	private String text;
	private URL statusUrl;
	private URL replyOfStatusUrl;
	private String replyToUserId;
	
	public TwitterStatus() {
		super();
	}

	public TwitterStatus(Date createdAt, String id, String text,
			URL statusUrl, URL replyOfStatusUrl, String replyToUserId) {
		super();
		this.createdAt = createdAt;
		this.id = id;
		this.text = text;
		this.statusUrl = statusUrl;
		this.replyOfStatusUrl = replyOfStatusUrl;
		this.replyToUserId = replyToUserId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public URL getStatusUrl() {
		return statusUrl;
	}

	public void setStatusUrl(URL statusUrl) {
		this.statusUrl = statusUrl;
	}

	public URL getReplyOfStatusUrl() {
		return replyOfStatusUrl;
	}

	public void setReplyOfStatusUrl(URL replyOfStatusUrl) {
		this.replyOfStatusUrl = replyOfStatusUrl;
	}

	public String getReplyToUserId() {
		return replyToUserId;
	}

	public void setReplyToUserId(String replyToUserId) {
		this.replyToUserId = replyToUserId;
	}
}
