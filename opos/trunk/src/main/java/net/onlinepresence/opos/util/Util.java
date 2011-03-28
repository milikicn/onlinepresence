package net.onlinepresence.opos.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

	public static Properties loadPropertyFile(String propertyName) {
		Properties prop = new Properties();

		InputStream in= new Util().getClass().getClassLoader().getResourceAsStream(propertyName);
		if (in != null) {
			try {
				prop.load(in);
				return prop;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
