package net.onlinepresence.util.urigenerator;

import net.onlinepresence.util.MiscConstants;
import net.onlinepresence.util.PropertiesManager;

public class URIBuilder
implements MiscConstants {
	
	private UriGenerator uriGenerator;
	
	private static URIBuilder instance;
	
	public static URIBuilder instance() {
		if ( instance == null )
			instance = new URIBuilder();
		return instance;
	}
	
	private URIBuilder() {
		
		String className = 
			PropertiesManager.instance().getProperty(URI_GENERATOR);
		
		try {
			Class c = Class.forName(className);
			uriGenerator = 
				(UriGenerator) c.getConstructor(null).newInstance(null);
		} catch (Exception e) {
			System.err.println( e.getMessage() );
			e.printStackTrace();
		}
	}
	
	public String generateURI(Object instance, String namespace) {
		return uriGenerator.generateInstanceUri(instance, namespace);
	}
	
	public String generateURI(Object instance) {
		return generateURI(instance, PropertiesManager.instance().getProperty(TRIPLE_STORE_NAMESPACE));
	}

}
