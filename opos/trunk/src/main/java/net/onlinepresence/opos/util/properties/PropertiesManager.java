package net.onlinepresence.opos.util.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

	private Properties configProps;

	private static PropertiesManager instance;

	public static PropertiesManager instance() {
		if (instance == null)
			instance = new PropertiesManager();
		return instance;
	}

	protected PropertiesManager() {
		loadPropValues();
	}

	private void loadPropValues() {

		FileInputStream in = null;
		try {
			// URL configURL = getClass().getClassLoader().getResource(
			// "src/main/resources/config/configuration.properties");
			// File configFile = new File(configURL.getPath());
			// is = new FileInputStream(configFile);
			in = new FileInputStream(
					"src/main/resources/config/configuration.properties");
			configProps = new Properties();
			configProps.load(in);

		} catch (IOException e) {
			System.err.println(e.getStackTrace().toString());
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public String getProperty(String name) {

		if (configProps.containsKey(name))
			return configProps.getProperty(name);

		System.err.println("Error! The requested config property " + name
				+ "was not found");
		return null;
	}
}
