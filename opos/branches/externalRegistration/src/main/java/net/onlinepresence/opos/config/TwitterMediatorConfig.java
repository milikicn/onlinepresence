/**
 * 
 */
package net.onlinepresence.opos.config;

import org.simpleframework.xml.Element;

public class TwitterMediatorConfig {

	@Element(name = "api-key", required = true)
	public String apiKey;
	
	@Element(name = "api-secret", required = true)
	public String apiSecret;
	
	@Element(name = "check-timeout", required = true)
	public long checkTimeout;

}
