package net.onlinepresence.domainmodel.opo.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.StatusMessageBean;
import net.onlinepresence.domainmodel.sioc.pojos.Item;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("StatusMessage")
public class StatusMessage extends Item implements StatusMessageBean {

	public StatusMessage() {
		super();
	}
	
	public StatusMessage(String uri) {
		super(uri);
	}
	
}
