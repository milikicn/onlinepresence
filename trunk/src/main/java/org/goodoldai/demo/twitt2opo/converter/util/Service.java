package org.goodoldai.demo.twitt2opo.converter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;

public class Service {

	public String time;
	private String screenName;
	private Properties prop;
	private String serverUrl;
	private String savingPath;

	public Service(String screenName, Properties prop){
		this.screenName = screenName;
		this.time = getTime();
		this.prop = prop;
		initialize();
	}
	
	private void initialize(){
		serverUrl = constructRelativeUrl(prop.getProperty("server_url")) + "#";
		savingPath = constructRelativeUrl(prop.getProperty("path_on_server_for_saving_files"));
	}
	
	public String getTime(){
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		return formater.format(new GregorianCalendar().getTime());
	}
	
	public String getServerUrl() {
		return serverUrl;
	}
	
	public String getSavingPath() {
		return savingPath;
	}
	
	private String constructRelativeUrl(String base) {
		String beginingUrl = base;
		
		if(!beginingUrl.endsWith("/"))
			beginingUrl = beginingUrl + "/";
			
		String yearStr = time.substring(0, 4);
		String monthStr = time.substring(5, 7);
		String dayStr = time.substring(8, 10);
		String hourStr = time.substring(11, 13);
		String minuteStr = time.substring(14, 16);
		String secondStr = time.substring(17, 19);
		
		return beginingUrl + "repository" + "/" + yearStr + "/" + monthStr + "/" + dayStr + "/" + screenName + "-" + hourStr + "-" + minuteStr + "-" + secondStr + ".rdf";
	}
}
