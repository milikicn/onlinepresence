/*
 * Settings.java
 * by s.ziplies
 */
package net.onlinepresence.opos.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class Settings {

	/**
	 * The logger.
	 */
	private static Logger logger = Logger.getLogger(Settings.class);

	/**
	 * Config file name.
	 */
	private static final String configFile = "config.xml";

	/**
	 * Relative path to folder in which the default config file is distributed.
	 */
	private static final String defaultConfigDir = "config/";

	/**
	 * Config is a singleton.
	 */
	public Config config;

	private static class SettingsHolder {
		private static final Settings INSTANCE = new Settings();
	}

	/**
	 * 
	 * @return
	 */
	public static Settings getInstance() {
		return SettingsHolder.INSTANCE;
	}

	/**
	 * @throws Exception
	 * 
	 */
	private Settings() {
		try {
			loadDefaultConfig();
		} catch (Exception e) {
			logger.error("Could not load settings: ", e);
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	private void loadDefaultConfig() throws Exception {

		InputStream is = null;
		Serializer serializer = new Persister();

		try {
			// get path to config file
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(defaultConfigDir + configFile);

			if (url != null) {
				String path = url.getFile();
				// remove white spaces encoded with %20
				path = path.replaceAll("%20", " ");
				is = new FileInputStream(new File(path));
				// read config file into Java
				config = serializer.read(Config.class, is);
				//
			} else {

			}

		} catch (FileNotFoundException fnfe) {
			// TODO
		} catch (Exception e) {
			throw new Exception("Could not serialize the configuration file: "
					+ configFile, e);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("Could not close InputStream!", e);
				}
			}
		}
	}

}
