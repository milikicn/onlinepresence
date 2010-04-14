package org.goodoldai.demo.twitt2opo.converter.util;

import java.net.MalformedURLException;
import java.net.URL;


import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.goodoldai.demo.twitt2opo.converter.model.TwitterUser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterUserBuilder {

	public static TwitterUser buildUser(Twitter twitter) throws TwitterException {
		User user = twitter.verifyCredentials();
		TwitterUser twitterUser = new TwitterUser();

		twitterUser.setId(String.valueOf(user.getId()));
		
//		twitterUser.setLocationUrl(extractUserLocation(user.getLocation()));
		
		twitterUser.setName(user.getName());
		
		twitterUser.setNickname(user.getScreenName());
		
		twitterUser.setImgUrl(user.getProfileImageURL());
		
//		twitterUser.setHomepage(user.getextractUserHomepage(sourceDoc));
		
		twitterUser.setCurrentStatus(TwitterStatusBuilder.buildCurrentStatus(twitter));

		return twitterUser;
	}

//	public static URL extractUserHomepage(Document sourceDoc) {
//		URL homepage = null;
//		try {
//			String url = sourceDoc.getRootElement().elementText("url");
//			if(url != null)
//				homepage = new URL(url);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		return homepage;
//	}

	public static URL extractUserLocation(String location) {
		int geoID = getGeoNameID(location);

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

	private static int getGeoNameID(String location) {
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
