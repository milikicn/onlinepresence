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
import statusComponents.OnlineStatusComponent;

import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author Nikola Milikic
 *
 */
public class StatusComponentHandler implements AbstractHandler{

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	@Override
	public void handleNode(OnlinePresence oPresence, RDFNode node) {
		oPresence.addComponentToOnlineStatus(getComponent(node));
	}

	private OnlineStatusComponent getComponent(RDFNode node) {
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
		
		OnlineStatusComponent osc = null;
		String obj = node.toString();
		
		try {
			osc = (OnlineStatusComponent) Class.forName(prop.getProperty(obj)).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return osc.getStaticInstance(obj.substring(obj.lastIndexOf("#") + 1));
	}
}