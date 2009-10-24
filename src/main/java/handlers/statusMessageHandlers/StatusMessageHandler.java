/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Aug 9, 2008
 * @version: 0.1
 */
package handlers.statusMessageHandlers;

import handlers.AbstractHandler;
import presence.OnlinePresence;
import presenceComponents.StatusMessage;
import presenceProperties.specificProperties.StatusMessageProperty;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author Nikola Milikic
 * 
 */
public class StatusMessageHandler implements AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence,
	 * com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, Resource subject,
			RDFNode object) {
		StatusMessage statusMessage = oPresence.getStatusMessage();

		if (!object.isAnon()) {
			statusMessage.setURI(object.toString());
		}
	}
}
