package net.onlinepresence.opos.tapestry.rest.parsers;

import java.util.Collection;

import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;

public interface OnlinePresenceJSONParser {

	Object exportToJSON(Collection<OnlinePresence> resources, Person person) throws Exception;
	
	Object exportToJSON(OnlinePresence resource, Person person) throws Exception;
}
