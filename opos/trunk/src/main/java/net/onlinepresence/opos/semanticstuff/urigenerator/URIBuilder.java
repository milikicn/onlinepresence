package net.onlinepresence.opos.semanticstuff.urigenerator;

import org.apache.log4j.Logger;

public class URIBuilder {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public UriGenerator uriGenerator;

	private static class URIBuilderHolder {
		private static final URIBuilder INSTANCE = new URIBuilder();
	}

	public static URIBuilder getInstance() {
		return URIBuilderHolder.INSTANCE;
	}

	private URIBuilder() {
		uriGenerator = new UUIDBasedUriGenerator();
	}

}
