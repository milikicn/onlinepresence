/**
 *  Java OPO library
 *  Copyright (C) 2010  Filip Radulovic, Nikola Milikic
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/.
 *  
 *  You any further questions regarding usage of this software you can 
 *  find appropriate contacts on the OPO Prject website 
 *  http://online-presence.net.
 */
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
