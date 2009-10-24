/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 10, 2008
* @version: 0.1
*/
package handlers.statusMessageHandlers;

import handlers.AbstractHandler;

import java.net.URI;

import presence.OnlinePresence;
import presenceComponents.StatusMessage;
import presenceProperties.URIProperty;
import presenceProperties.specificProperties.StatusMessageProperty;



import agent.Agent;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * @author Nikola Milikic
 *
 */
public class StatusMessageContentHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		StatusMessage statusMessage = oPresence.getStatusMessage();
		
		statusMessage.addComponent(new StatusMessageProperty("content", object.toString()));
	}

}
