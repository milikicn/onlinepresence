package net.onlinepresence.opos.tapestry.appconfig;

import net.onlinepresence.opos.domain.ApplicationNames;

public class UserAppSettings {

	private ApplicationNames appName;
	private boolean userUssesApp;
	private String username;
	private String password;
	private boolean sendDataToApp;
	private boolean receiveDataFromApp;
	
	public UserAppSettings(ApplicationNames appName) {
		this.appName = appName;
	}

	public ApplicationNames getAppName() {
		return appName;
	}

	public void setAppName(ApplicationNames appName) {
		this.appName = appName;
	}

	public boolean isUserUssesApp() {
		return userUssesApp;
	}

	public void setUserUssesApp(boolean userUssesApp) {
		this.userUssesApp = userUssesApp;
	}
	
	public boolean isSendDataToApp() {
		return sendDataToApp;
	}

	public void setSendDataToApp(boolean sendDataToApp) {
		this.sendDataToApp = sendDataToApp;
	}

	public boolean isReceiveDataFromApp() {
		return receiveDataFromApp;
	}

	public void setReceiveDataFromApp(boolean receiveDataFromApp) {
		this.receiveDataFromApp = receiveDataFromApp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
