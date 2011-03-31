package net.onlinepresence.opos.config;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Config {
	
	@org.simpleframework.xml.Transient
	public static String MySQL = "MySQL";

	@Element(name = "sdb-config", required = true)
	public SDBConfig sdbConfig;
	
	@Element(name = "twitter-mediator", required = false)
	public TwitterMediatorConfig twitterMediatorConfig;

}
