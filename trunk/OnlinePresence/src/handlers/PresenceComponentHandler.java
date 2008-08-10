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

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;

import presence.OnlinePresence;
import presenceComponents.OnlinePresenceComponent;

/**
 * @author Nikola Milikic
 *
 */
public class PresenceComponentHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, RDFNode node) {
		if(!node.isAnon())
			oPresence.addComponent(getComponent(node));
	}

	private OnlinePresenceComponent getComponent(RDFNode node) {
		Properties prop = new Properties();

		try {
			prop.loadFromXML(new FileInputStream("conf/opoProperties.xml"));
		} catch (InvalidPropertiesFormatException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		OnlinePresenceComponent opc = null;
		String obj = node.toString();
				
		try {
			opc = (OnlinePresenceComponent) Class.forName(prop.getProperty(obj)).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
				
		return opc.getStaticInstance(obj.substring(obj.lastIndexOf("#") + 1));
	}
}
