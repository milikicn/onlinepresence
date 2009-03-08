/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 10, 2008
* @version: 0.1
*/
package handlers.agentHandlers;

import presence.OnlinePresence;
import presenceProperties.StringProperty;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import handlers.AbstractHandler;

/**
 * @author Nikola Milikic
 *
 */
public class AgentNameHandler implements AbstractHandler {

	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		Agent agent = oPresence.getAgent();
		
		agent.addComponent(new StringProperty("name", object.toString()));
	}

}
