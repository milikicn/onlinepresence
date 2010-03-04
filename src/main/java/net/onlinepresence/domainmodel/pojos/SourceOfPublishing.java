package net.onlinepresence.domainmodel.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.interfaces.SourceOfPublishingBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("SourceOfPublishing")
public class SourceOfPublishing extends Resource implements SourceOfPublishingBean{

	private String name;

	@RdfProperty("http://online-presence.net/opo/ns#sourceName")
	public String getName() {
		return name;
	}

	public void setName(String sourceName) {
		this.name = sourceName;
	}
	
	
}
