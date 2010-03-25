package net.onlinepresence.util.urigenerator;

import net.onlinepresence.util.MiscConstants;
import net.onlinepresence.util.PropertiesManager;

public class URIBuilder implements MiscConstants {

	private UriGenerator uriGenerator;

	private static URIBuilder instance;

	public static URIBuilder instance() {
		if (instance == null)
			instance = new URIBuilder();
		return instance;
	}

	private URIBuilder() {
		uriGenerator = new UUIDBasedUriGenerator();
	}
	public String generateURI(Object instance, String namespace) {
		return uriGenerator.generateInstanceUri(instance, namespace);
	}

	public String generateURI(Object instance) {
		return generateURI(instance, PropertiesManager.instance().getProperty(
				TRIPLE_STORE_NAMESPACE));
	}

}
