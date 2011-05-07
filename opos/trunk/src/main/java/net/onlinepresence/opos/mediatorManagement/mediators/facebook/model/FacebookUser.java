package net.onlinepresence.opos.mediatorManagement.mediators.facebook.model;

public class FacebookUser {

	private String name;
	private String location;
	private FacebookPost lastPost;
	private boolean isOnline = false;
	private String avatarURL;

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

	public FacebookPost getLastPost() {
		return lastPost;
	}

	public void setLastPost(FacebookPost lastPost) {
		this.lastPost = lastPost;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public String toString() {
		String toReturn = "Name: " + this.name + "\nLocation: " + this.location + "\nIs Online: " + this.isOnline + "\nAvatar URL: " + this.avatarURL + "\n";
		if (lastPost != null)
			toReturn+="Last post text: " + this.lastPost.getTextPost() + ",date updated: (" + this.lastPost.getTimePost() + ")";
		
		return toReturn;
	}
}
