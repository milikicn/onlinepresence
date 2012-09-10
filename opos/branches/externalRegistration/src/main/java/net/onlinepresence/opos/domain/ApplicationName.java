package net.onlinepresence.opos.domain;

import java.util.HashMap;
import java.util.Map;

public class ApplicationName {
	public static String SPARK = "SPARK";
	public static String TWITTER = "TWITTER";
	public static String FACEBOOK = "FACEBOOK";
	public static String FOURSQUARE = "FOURSQUARE";
	public static String MOODLE = "MOODLE";
	
	public static Map<String, String> apps;
	
	public static String getApplicationHomepage(String appName) {
		if (apps == null) {
			apps = new HashMap<String, String>();
			apps.put(ApplicationName.TWITTER, "http://twitter.com");
			apps.put(ApplicationName.FACEBOOK, "http://www.facebook.com");
			apps.put(ApplicationName.SPARK, "http://www.igniterealtime.org/projects/spark/");
			apps.put(ApplicationName.TWITTER, "https://foursquare.com");
			apps.put(ApplicationName.MOODLE, "http://www.moodle.com");
		}
		return apps.get(appName);
	}
}
