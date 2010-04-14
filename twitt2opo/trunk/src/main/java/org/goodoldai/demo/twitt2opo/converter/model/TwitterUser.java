package org.goodoldai.demo.twitt2opo.converter.model;

import java.net.URL;



public class TwitterUser {

	private String id;
	private String name;
	private String nickname;
	private URL locationUrl;
	private URL imgUrl;
	private URL homepage;
	
	private TwitterStatus currentStatus;
	
	public TwitterUser() {
		super();
	}

	public TwitterUser(String id, String name, String nickname,
			URL locationUrl, URL imgUrl, URL homepage) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.locationUrl = locationUrl;
		this.imgUrl = imgUrl;
		this.homepage = homepage;
	}

	public TwitterUser(String id, String name, String nickname,
			URL locationUrl, URL imgUrl, URL homepage, TwitterStatus currentStatus) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.locationUrl = locationUrl;
		this.imgUrl = imgUrl;
		this.homepage = homepage;
		this.currentStatus = currentStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public URL getLocationUrl() {
		return locationUrl;
	}

	public void setLocationUrl(URL locationUrl) {
		this.locationUrl = locationUrl;
	}

	public URL getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(URL imgUrl) {
		this.imgUrl = imgUrl;
	}

	public URL getHomepage() {
		return homepage;
	}

	public void setHomepage(URL homepage) {
		this.homepage = homepage;
	}

	public TwitterStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(TwitterStatus currentStatus) {
		this.currentStatus = currentStatus;
	}
}
