/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

import java.net.URI;
import java.util.LinkedList;
import presence.OntologyConcept;
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
	 * @return
	 */
	public boolean addComponent(OnlineStatusComponent onlineStatusComponent) {
		return statusComponents.add(onlineStatusComponent);
	}
	
	/**
	 * 
	 * @param listOSComponents
	 */
	public void addComponent(LinkedList<OnlineStatusComponent> listOSComponents){
		for (int i = 0; i < listOSComponents.size(); i++) {
			statusComponents.add(listOSComponents.get(i));
		} 
	}
	
}
