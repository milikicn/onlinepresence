package net.onlinepresence.opos.tapestry.appconfig;

public class UserAppSettings {

	private String appName;
	private boolean userUssesApp;
	private String username;
	private String password;
	private boolean sendDataToApp;
	private boolean receiveDataFromApp;
	
	public UserAppSettings(String appName) {
		this.appName = appName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
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
