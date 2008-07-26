/**
 * @autor: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

import java.util.LinkedList;

import presence.OntologyConcept;

import statusComponents.OnlineStatusComponent;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlineStatus extends OntologyConcept implements
		OnlinePresenceComponent {
	public String statusName;

	private LinkedList<OnlineStatusComponent> statusComponents = new LinkedList<OnlineStatusComponent>();

	public LinkedList<OnlineStatusComponent> getStatusComponents() {
		return statusComponents;
	}

	/**
	 * dds a specific OnlineStatusComponent
	 * 
	 * @param onlineStatusComponent
	 * @return
	 */
	public boolean addComponent(OnlineStatusComponent onlineStatusComponent) {
		return statusComponents.add(onlineStatusComponent);
	}
}
