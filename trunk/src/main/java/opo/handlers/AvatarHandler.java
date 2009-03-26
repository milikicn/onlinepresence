/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 9, 2008
* @version: 0.1
*/
package opo.handlers;

import java.net.URI;

import opo.presence.OnlinePresence;


import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author Nikola Milikic
 *
 */
public class AvatarHandler implements AbstractHandler{

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		oPresence.setAvatar(URI.create(object.toString()));
	}
}
