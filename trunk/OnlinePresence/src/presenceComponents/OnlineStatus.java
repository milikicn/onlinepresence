/**
* @autor: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package presenceComponents;

import java.util.LinkedList;

import statusComponents.OnlineStatusComponent;

/**
 * @author Nikola Milikic
 *
 */
public class OnlineStatus implements OnlinePresenceComponent{
	private LinkedList<OnlineStatusComponent> statusComponents = new LinkedList<OnlineStatusComponent>();

	public LinkedList<OnlineStatusComponent> getStatusComponents() {
		return statusComponents;
	}

	public boolean addOnlineStatusComponent(OnlineStatusComponent e) {
		return statusComponents.add(e);
	}
}
