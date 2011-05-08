package net.onlinepresence.opos.config;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Config {
	
	@org.simpleframework.xml.Transient
	public static String MySQL = "MySQL";
	
	@Element(name = "opos-website", required = true)
	public String oposWebsite;

	@Element(name = "rdf-repository", required = true)
	public RdfRepositoryConfig rdfRepositoryConfig;
	
	@Element(name = "twitter-mediator", required = false)
	public TwitterMediatorConfig twitterMediatorConfig;
	
	@Element(name = "facebook-mediator", required = false)
	public FacebookMediatorConfig facebookMediatorConfig;
	
	@Element(name = "foursquare-mediator", required = false)
	public FoursquareMediatorConfig foursquarekMediatorConfig;

}
