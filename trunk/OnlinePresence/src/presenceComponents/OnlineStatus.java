/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

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

	private String statusName;

	/**
	 * A setter method for statusName
	 * 
	 * @param statusName
	 *            the statusName to set
	 */
	public void setStatusName(String statusName) {
		propertyList.add(new StringProperty("statusName", statusName));
	}

	/**
	 * @param statusName
	 */
	public OnlineStatus(URI uri) {
		setURI(uri);
	}

	/**
	 * adds a specific OnlineStatusComponent
	 * 
	 * @param onlineStatusComponent
	 */
	public void addComponent(OnlineStatusComponent onlineStatusComponent) {
		propertyList.add(new URIProperty("hasStatusComponent",
				onlineStatusComponent.getURI()));
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
