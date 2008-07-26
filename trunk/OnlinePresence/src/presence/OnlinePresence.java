package presence;

import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import agent.Agent;

import presenceComponents.OnlinePresenceComponent;
import presenceComponents.OnlineStatus;
import presenceProperties.*;

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

	Agent agent;

	OnlineStatus onlineStatus;

	LinkedList<OnlinePresenceComponent> presenceComponents = new LinkedList<OnlinePresenceComponent>();

	Set<Property> presenceProperties = new HashSet<Property>();

	/**
	 * Sets the current OnlinePresence Status.
	 * 
	 * @param OnlineStatus
	 */
	public void setStatus(OnlineStatus OnlineStatus) {
		this.onlineStatus = OnlineStatus;
		// presenceComponents.add(os);
	}

	/**
	 * Adds a specific OnlinePresenceComponent.
	 * 
	 * @param OnlinePresenceComponent
	 */
	public void addComponent(OnlinePresenceComponent OnlinePresenceComponent) {
		presenceComponents.add(OnlinePresenceComponent);
	}

	/**
	 * Sets the Avatar.
	 * 
	 * @param avatar
	 */
	public void setAvatar(Avatar avatar) {
		presenceProperties.remove(getProperty("avatar"));
		presenceProperties.add(avatar);
	}

	/**
	 * 
	 * @param avatarURI
	 */
	public void setAvatar(URI avatarURI) {
		setAvatar(new Avatar(avatarURI));
	}

	/**
	 * 
	 * @param curMessage
	 */
	public void setCustomMessage(CustomMessage curMessage) {
		presenceProperties.remove(getProperty("currentMessage"));
		presenceProperties.add(curMessage);
	}

	/**
	 * 
	 * @param curMessage
	 */
	public void setCustomMessage(String curMessage) {
		setCustomMessage(new CustomMessage(curMessage));
	}

	/**
	 * 
	 * @param agent
	 */
	public void setDeclaredByAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * Returns an instance of the class Property with 'oName' as its name.
	 * 
	 * @param pName
	 * @return
	 */
	public Property getProperty(String pName) {
		Property prop = null;
		Iterator<Property> it = presenceProperties.iterator();

		while (it.hasNext()) {
			Property temp = it.next();
			if (temp.getName().equals(pName)) {
				prop = temp;
				break;
			}
		}
		return prop;
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public boolean addProperty(Property property) {
		return presenceProperties.add(property);
	}
}
