/**
 * 
 */
package net.onlinepresence.opos.config;

import org.simpleframework.xml.Element;

public class SDBConfig {

	@Element(name = "db-type", required = true)
	public String dbType;
	
	@Element(name = "db-jdbcURL", required = true)
	public String dbURL;
	
	@Element(name = "db-username", required = true)
	public String dbUser;
	
	@Element(name = "db-password", required = false)
	public String dbPassword = "";
	
	@Element(name = "format", required = false)
	public boolean format;

}
