package net.onlinepresence.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	
	private Properties configProps;
	
	private static PropertiesManager instance;
	
	public static PropertiesManager instance() {
		if ( instance == null )
			instance = new PropertiesManager();
		return instance;
	}
	
	protected PropertiesManager() {
		loadPropValues();
	}
	
	private void loadPropValues() {
		       
	    try {
	     	
	    //	InputStream is = getClass().getResourceAsStream("configuration.properties");
	    	
	    	FileInputStream in = 
	    		new FileInputStream("src/main/resources/config/configuration.properties");
	    	configProps = new Properties();
	        configProps.load( in );  
	        in.close();
	            
	     } catch (IOException e) {
	           System.err.println(e.getStackTrace().toString());
	     }	
	     
	}
	
	public String getProperty(String name) {
		
		if ( configProps.containsKey( name ) )
			return configProps.getProperty( name );
		
		System.err.println("Error! The requested config property " + name + "was not found");
		return null;
	}
	
	
	public void addProperty(String name, String value) {
		if ( name != null && value != null )
			configProps.setProperty( name, value );
	}
	
	public void updatePropertyValue(String propName, String propValue) {	
		configProps.remove(propName);
		configProps.put(propName, propValue);
	
	}



}
