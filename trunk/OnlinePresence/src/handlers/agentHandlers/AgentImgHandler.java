/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 10, 2008
* @version: 0.1
*/
package handlers.agentHandlers;

import java.net.URI;

import presence.OnlinePresence;
import presence.PresenceClass;

import agent.Agent;
import agent.AgentURIProperty;

import com.hp.hpl.jena.rdf.model.RDFNode;

import handlers.AbstractHandler;

/**
 * @author Nikola Milikic
 *
 */
public class AgentImgHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, RDFNode node) {
		Agent agent = oPresence.getAgent();
		
		agent.addComponent(new AgentURIProperty("img", URI.create(node.toString())));
	}

}
