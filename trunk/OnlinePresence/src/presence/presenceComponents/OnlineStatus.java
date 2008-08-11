/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presence.presenceComponents;

import java.net.URI;
import java.util.LinkedList;

import presence.PresenceClass;
import presenceProperties.StringProperty;
import presenceProperties.URIProperty;
import statusComponents.OnlineStatusComponent;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlineStatus extends PresenceClass {

	/**
	 * @param statusName
	 */
	public OnlineStatus(URI uri) {
		setURI(uri);
	}
	
	/**
	 * 
	 */
	public OnlineStatus(){}
	
	/**
	 * A setter method for statusName
	 * 
	 * @param statusName
	 *            the statusName to set
	 */
	public void setStatusName(String statusName) {
		addProperty(new StringProperty("statusName", statusName));
	}

	/**
	 * adds a specific OnlineStatusComponent
	 * 
	 * @param onlineStatusComponent
	 */
	public <T> void addComponent(T onlineStatusComponent) {
		OnlineStatusComponent osc = (OnlineStatusComponent) onlineStatusComponent;
		addProperty(new URIProperty("hasStatusComponent",
				osc.getURI()));
	}

	/**
	 * 
	 * @param listOSComponents
	 */
	public void addComponents(LinkedList<OnlineStatusComponent> listOSComponents) {
		for (OnlineStatusComponent osc : listOSComponents) {
			this.addComponent(osc);
		}
	}
		
}
