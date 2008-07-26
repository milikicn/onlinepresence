package presence;

import java.util.LinkedList;

import presenceComponents.OnlinePresenceComponent;

/**
 * @autor: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */

/**
 * @author Nikola Milikic
 *
 */
public class OnlinePresence {
	private LinkedList<OnlinePresenceComponent> presenceComponents=new LinkedList<OnlinePresenceComponent>();

	public LinkedList<OnlinePresenceComponent> getPresenceComponents() {
		return presenceComponents;
	}

	public boolean addOnlinePresenceComponent(OnlinePresenceComponent e) {
		return presenceComponents.add(e);
	}

	
}
