/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 10, 2008
* @version: 0.1
*/
package opo.handlers.agentHandlers;

import java.net.URI;

import opo.agent.Agent;
import opo.handlers.AbstractHandler;
import opo.presence.OnlinePresence;
import opo.presenceProperties.URIProperty;



import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * @author Nikola Milikic
 *
 */
public class AgentImgHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		Agent agent = oPresence.getAgent();
		
		agent.addComponent(new URIProperty("img", URI.create(object.toString())));
	}

}
