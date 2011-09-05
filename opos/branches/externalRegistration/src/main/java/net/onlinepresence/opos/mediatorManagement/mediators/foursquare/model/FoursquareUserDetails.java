package net.onlinepresence.opos.mediatorManagement.mediators.foursquare.model;

public class FoursquareUserDetails {

	private String firstName;
	private String lastName;
	private String avatarURL;
	private FoursquareLastLocation lastLocation;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public FoursquareLastLocation getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(FoursquareLastLocation lastLocation) {
		this.lastLocation = lastLocation;
	}

	public String toString() {
		return "Name: " + this.firstName + " " + this.lastName
				+ "\nAvatar URL: " + this.avatarURL + "\nLast location: \n"
				+ this.lastLocation.getText() + " - "
				+ this.lastLocation.getTime() + "\n" +"Lat:"
				+ this.lastLocation.getLatitude() + "\n" +"Long:"
				+ this.lastLocation.getLongitude() + "\n" + ")";

	}
}
