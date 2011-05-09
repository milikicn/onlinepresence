package net.onlinepresence.opos.mediatorManagement.mediators.foursquare.model;

public class FoursquareLastLocation {

	private String text;
	private String time;
	private String latitude;
	private String longitude;

	public FoursquareLastLocation() {
		super();
	}

	public FoursquareLastLocation(String text, String time, String latitude, String longitude) {
		super();
		this.text = text;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
