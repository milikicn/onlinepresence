/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: August 11, 2008
 * @version: 0.1
 */

package handlers;

import presence.OnlinePresence;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author Filip Radulovic
 * 
 */
public class TimeHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		oPresence.setStartTime(object.toString());
	}
}
