/**
 * 
 */
package net.onlinepresence.opos.config;

import org.simpleframework.xml.Element;

public class RdfRepositoryConfig {

	@Element(name = "namespace", required = true)
	public String namespace;
	
	@Element(name = "sdb-config", required = true)
	public SDBConfig sdbConfig;

}
