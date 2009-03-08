/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 8, 2008
* @version: 0.1
*/
package handlers;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import presence.OnlinePresence;

/**
 * @author Nikola Milikic
 *
 */
public interface AbstractHandler {
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object);
}