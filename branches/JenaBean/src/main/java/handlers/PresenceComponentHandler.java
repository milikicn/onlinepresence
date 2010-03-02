/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 9, 2008
* @version: 0.1
*/
package handlers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import presence.OnlinePresence;
import presenceComponents.OnlinePresenceComponent;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


/**
 * @author Nikola Milikic
 *
 */
public class PresenceComponentHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		if(!object.isAnon())
			oPresence.addComponent(getComponent(object));
	}

	private OnlinePresenceComponent getComponent(RDFNode node) {
		Properties prop = new Properties();

		try {
			prop.loadFromXML(ClassLoader.getSystemResourceAsStream("conf/opoProperties.xml"));
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
