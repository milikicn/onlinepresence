/**
 * 
 */
package net.onlinepresence.opos.config;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class FacebookMediatorConfig {

	@Element(name = "api-secret", required = true)
	public String apiSecret;
	
	@Element(name = "application-id", required = true)
	public String applicationId;
	
	@Element(name = "redirect-url", required = true)
	public String redirectUrl;
	
	@ElementList(name = "permissions", required = true)
	public List<Permission> permissions;
	
	@Element(name = "check-timeout", required = true)
	public long checkTimeout;

}
