package net.onlinepresence.opos.dataManager.mediators.twitter.service.builder.wrappers;

import java.net.MalformedURLException;
import java.net.URL;

import net.onlinepresence.opos.dataManager.mediators.twitter.util.Geo;

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
	
	public Object getMbox() {
		URL url = null;
		
		try {
//			url = user.get
		} catch (Exception e) {

		}
		return url;
	}
	
	public Geo getUserLocation() {
		Geo geo = getGeo(user.getLocation());

		if (geo != null && geo.getId() != 0) {
			try {
				URL url = new URL("http://sws.geonames.org/" + geo.getId() + "/");
				geo.setGeonamesUrl(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return geo;
	}
	
	private Geo getGeo(String location) {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(location);
		ToponymSearchResult searchResult;
		
		try {
			searchResult = WebService.search(searchCriteria);
			for (Toponym toponym : searchResult.getToponyms()) {
				Geo geo = new Geo();
				geo.setId(toponym.getGeoNameId());
				geo.setLatitude(toponym.getLatitude());
				geo.setLongitude(toponym.getLongitude());
				return geo;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
