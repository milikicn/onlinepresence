/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 9, 2008
* @version: 0.1
*/
package handlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import presence.OnlinePresence;
import presence.PresenceClass;

import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author Nikola Milikic
 *
 */
public class TypeHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, RDFNode node) {
		String uri = node.toString();
		String className = uri.substring(uri.indexOf("#") + 1);
		
		if(className == "OnlinePresence")
			oPresence.setURI(uri);
		else
			oPresence.getObjectProperty(className).setURI(uri);
	}

}
