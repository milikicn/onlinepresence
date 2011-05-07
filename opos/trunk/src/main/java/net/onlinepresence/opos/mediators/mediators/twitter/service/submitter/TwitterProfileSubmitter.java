package net.onlinepresence.opos.mediators.mediators.twitter.service.submitter;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterProfileSubmitter {

	private OnlinePresence onlinePresence;
	private Twitter twitter;
	private String name;
	private String email;
	private String location;
	private String url;
	private String description;
	
	public TwitterProfileSubmitter(OnlinePresence onlinePresence,
			Twitter twitter) {
		this.onlinePresence = onlinePresence;
		this.twitter = twitter;
	}

	public boolean submit() {
		init();
		System.out.println("submit na TwitterProfileSubmitter");
		try {
			twitter.updateProfile(name, url, location, description);
			return true;
		} catch (TwitterException e) {
			return false;
//			throw new TwitterOPOSException("Error when submiting profile details");
		}
	}

	private void init() {
		initName();
		initLocation();
		initUrl();
	}
	
	private void initName() {
		if(onlinePresence.getAgent() != null)
			name = onlinePresence.getAgent().getName();
	}

	private void initLocation() {
//		if(onlinePresence.getLocation() != null){
//			String latitude = onlinePresence.getLocation().getLatitude();
//			String longitude = onlinePresence.getLocation().getLongitude();
//			
//			if(latitude != null && longitude != null){
//				GeoLocation geoLocation = new GeoLocation(Integer.parseInt(latitude), Integer.parseInt(longitude));
//	
//				try {
//					ResponseList<Place> places = twitter.getNearbyPlaces(new GeoQuery(geoLocation));
//					
//					Place place = places.iterator().next();
//					
//					location = place.getFullName();
//				} catch (TwitterException e) {
//					e.printStackTrace();
//				}
//			}
//		}
	}
	
	private void initUrl() {
		if(onlinePresence.getAgent() != null)
			if(onlinePresence.getAgent().getHomepage() != null)
				url = onlinePresence.getAgent().getHomepage().toString();
	}
}
