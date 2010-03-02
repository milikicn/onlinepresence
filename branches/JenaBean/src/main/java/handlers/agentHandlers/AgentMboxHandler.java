package handlers.agentHandlers;

import java.net.URI;

import presence.OnlinePresence;
import presenceProperties.URIProperty;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import handlers.AbstractHandler;

public class AgentMboxHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		Agent agent = oPresence.getAgent();
		
		agent.addComponent(new URIProperty("mbox", URI.create(object.toString())));
	}

}
