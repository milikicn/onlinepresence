package net.onlinepresence.opos.mediatorManagement.mediators.twitter.util;

import java.net.URL;

public class Geo{
	private int id;
	private double latitude;
	private double longitude;
	private URL geonamesUrl;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the geonamesUrl
	 */
	public URL getGeonamesUrl() {
		return geonamesUrl;
	}
	/**
	 * @param geonamesUrl the geonamesUrl to set
	 */
	public void setGeonamesUrl(URL geonamesUrl) {
		this.geonamesUrl = geonamesUrl;
	}
}
