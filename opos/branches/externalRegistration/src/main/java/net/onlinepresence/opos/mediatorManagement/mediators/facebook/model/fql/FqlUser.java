package net.onlinepresence.opos.mediatorManagement.mediators.facebook.model.fql;

import com.restfb.Facebook;

public class FqlUser {
	@Facebook(value = "uid")
	String uid;
	@Facebook(value = "name")
	String name;
	@Facebook(value = "location")
	String location;
	@Facebook(value = "status")
	String chatStatus;
	@Facebook(value = "online_presence")
	String onlinePresence;
	@Facebook(value = "pic_big")
	String avatarURL;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}
	
	public String getOnlinePresence() {
		return onlinePresence;
	}

	public void setOnlinePresence(String onlinePresence) {
		this.onlinePresence = onlinePresence;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

}
