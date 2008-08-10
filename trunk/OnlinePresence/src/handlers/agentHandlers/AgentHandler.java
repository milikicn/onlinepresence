/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 10, 2008
* @version: 0.1
*/
package handlers.agentHandlers;

import presence.OnlinePresence;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.RDFNode;

import handlers.AbstractHandler;

/**
 * @author Nikola Milikic
 *
 */
public class AgentHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPesence, RDFNode node) {
		Agent agent = oPesence.getAgent();
		
		if (!node.isAnon()) {
			agent.setURI(node.toString());
		}
	}

}
