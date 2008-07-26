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
public class OnlineStatus extends OntologyConcept implements OnlinePresenceComponent{
	private LinkedList<OnlineStatusComponent> statusComponents = new LinkedList<OnlineStatusComponent>();

	public LinkedList<OnlineStatusComponent> getStatusComponents() {
		return statusComponents;
	}

	public boolean addOnlineStatusComponent(OnlineStatusComponent e) {
		return statusComponents.add(e);
	}
	public String statusName;
	
	//Ads a specific OnlineStatusComponent
	public void addComponent(OnlineStatusComponent OnlineStatusComponent){
		statusComponents.add(OnlineStatusComponent);
	}
}
