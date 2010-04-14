package org.goodoldai.demo.twitt2opo.converter.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

public class Service {

	public String time;;
	
	public void setTime(String time) {
		this.time = time;
	}

	public Service(){
		time = getTime();
	}
	
	public String getTime(){
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		return formater.format(new GregorianCalendar().getTime());
	}
	
	public URL initializeXmlPath(String user) {
		try {
			return new URL("http", "www.twitter.com", -1, "/users/show/" + user
					+ ".xml");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String constructBaseUrl(String username, Properties prop) {
		return prop.getProperty("server_path") + constructRelativeUrl(username, "/");
	}
	
	public String constructSavingPath(String username, Properties prop) {
		return prop.getProperty("path_for_saving_files") + constructRelativeUrl(username, "/");
	}
	
	private String constructRelativeUrl(String username, String slash) {
		String yearStr = time.substring(0, 4);
		String monthStr = time.substring(5, 7);
		String dayStr = time.substring(8, 10);
		String hourStr = time.substring(11, 13);
		String minuteStr = time.substring(14, 16);
		String secondStr = time.substring(17, 19);
		
		return slash + "repository" + slash + yearStr + slash + monthStr + slash + dayStr + slash + username + "-" + hourStr + "-" + minuteStr + "-" + secondStr + ".rdf#";
	}
}
