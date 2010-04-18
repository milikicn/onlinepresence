package org.goodoldai.demo.twitt2opo.converter.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import twitter4j.User;

public class Twitter4jUserWrapper {

	private User user;

	public Twitter4jUserWrapper(User user) {
		this.user = user;
	}
	
	public String getId(){
		String id = null;
		
		try {
			id = String.valueOf(user.getId());
		} catch (Exception e) {

		}
		return id;
	}

	public String getName(){
		String name = null;
		
		try {
			name = user.getName();
		} catch (Exception e) {

		}
		return name;
	}
	
	public String getScreenName(){
		String screenName = null;
		
		try {
			screenName = user.getScreenName();
		} catch (Exception e) {

		}
		return screenName;
	}
	
	public URL getProfileImageURL(){
		URL profileImageURL = null;
		
		try {
			profileImageURL = user.getProfileImageURL();
		} catch (Exception e) {

		}
		return profileImageURL;
	}
	
	public URL getURL(){
		URL url = null;
		
		try {
			url = user.getURL();
		} catch (Exception e) {

		}
		return url;
	}
	
	public URL getUserLocation() {
		int geoID = getGeoNameID(user.getLocation());

		URL url = null;
		if (geoID != 0) {
			try {
				url = new URL("http://sws.geonames.org/" + geoID + "/");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		return url;
	}
	
	private int getGeoNameID(String location) {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(location);
		ToponymSearchResult searchResult;
		
		try {
			searchResult = WebService.search(searchCriteria);
			for (Toponym toponym : searchResult.getToponyms()) {
				return toponym.getGeoNameId();
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
