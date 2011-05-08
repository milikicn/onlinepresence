package net.onlinepresence.opos.tapestry.rest.parsers;

import java.util.Collection;

import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;

public interface OnlinePresenceJSONParser {

	String exportToJSON(Collection<OnlinePresence> resources, Person person) throws Exception;
	
	String exportToJSON(OnlinePresence resource, Person person) throws Exception;
}
