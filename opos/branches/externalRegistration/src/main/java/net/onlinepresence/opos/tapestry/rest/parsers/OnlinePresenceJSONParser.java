package net.onlinepresence.opos.tapestry.rest.parsers;

import java.util.Collection;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;

public interface OnlinePresenceJSONParser {

	Object exportToJSON(Collection<OnlinePresence> resources, String userUri) throws Exception;
	
	Object exportToJSON(OnlinePresence resource, String userUri) throws Exception;
}
