package net.onlinepresence.opos.mediatorManagement.mediators.foursquare.model;

public class FoursquareLastLocation {

	private String text;
	private String time;

	public FoursquareLastLocation() {
		super();
	}

	public FoursquareLastLocation(String text, String time) {
		super();
		this.text = text;
		this.time = time;
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

}
