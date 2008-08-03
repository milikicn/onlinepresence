/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

import java.net.URI;
import java.util.LinkedList;
import statusComponents.OnlineStatusComponent;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlineStatus extends OnlinePresenceComponent {
	public String statusName;

	private LinkedList<OnlineStatusComponent> statusComponents = new LinkedList<OnlineStatusComponent>();

	public LinkedList<OnlineStatusComponent> getStatusComponents() {
		return statusComponents;
	}

	/**
	 * @param statusName
	 */
	public OnlineStatus(URI uri) {
		this.setURI(uri);
	}
	
	/**
	 * adds a specific OnlineStatusComponent
	 * 
	 * @param onlineStatusComponent
	 */
	public void addComponent(OnlineStatusComponent onlineStatusComponent) {
		statusComponents.add(onlineStatusComponent);
	}
	
	/**
	 * 
	 * @param listOSComponents
	 */
	public void addComponents(LinkedList<OnlineStatusComponent> listOSComponents){		
		for(OnlineStatusComponent osc : listOSComponents){
			statusComponents.add(osc);
		}		
	}
	
}
