/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 9, 2008
* @version: 0.1
*/
package handlers;

import java.net.URI;

import presence.OnlinePresence;

import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author Nikola Milikic
 *
 */
public class AvatarHandler implements AbstractHandler{

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, RDFNode node) {
		oPresence.setAvatar(URI.create(node.toString()));
	}
}
