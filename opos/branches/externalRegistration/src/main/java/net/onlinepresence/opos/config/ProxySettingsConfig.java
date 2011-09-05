package net.onlinepresence.opos.config;

import org.simpleframework.xml.Element;

public class ProxySettingsConfig {

	@Element(name = "host-url", required = false)
	public String hostUrl;
	
	@Element(name = "port", required = false)
	public int port;
	
	@Element(name = "username", required = false)
	public String username;
	
	@Element(name = "password", required = false)
	public String password = "";
	
}
