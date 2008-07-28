/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presence;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import agent.Agent;

import presenceComponents.OnlinePresenceComponent;
import presenceComponents.OnlineStatus;
import presenceProperties.*;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlinePresence {

	private Agent agent;

	private OnlineStatus onlineStatus;
	
//	private Date startTime;
	
//	private Date duration;

	LinkedList<OnlinePresenceComponent> presenceComponents = new LinkedList<OnlinePresenceComponent>();

	Set<Property> presenceProperties = new HashSet<Property>();

	/**
	 * @param agent
	 * @param onlineStatus
	 * @param presenceComponents
	 * @param presenceProperties
	 * @param startTime
	 */
//	public OnlinePresence(Agent agent, OnlineStatus onlineStatus,
//			LinkedList<OnlinePresenceComponent> presenceComponents,
//			Set<Property> presenceProperties, Date startTime) {
//		this.agent = agent;
//		this.onlineStatus = onlineStatus;
//		this.presenceComponents = presenceComponents;
//		this.presenceProperties = presenceProperties;
//		this.startTime = startTime;
//	}
	
	/**
	 * @param agent
	 * @param onlineStatus
	 * @param presenceComponents
	 * @param presenceProperties
	 * @param startTime
	 */
	public OnlinePresence(Agent agent, OnlineStatus onlineStatus,
			LinkedList<OnlinePresenceComponent> presenceComponents,
			Set<Property> presenceProperties) {
		this.agent = agent;
		this.onlineStatus = onlineStatus;
		this.presenceComponents = presenceComponents;
		this.presenceProperties = presenceProperties;
//		this.startTime = Calendar.getInstance().getTime();
	}

	/**
	 * Sets the current OnlinePresence Status.
	 * 
	 * @param OnlineStatus
	 */
	public void setOnlineStatus(OnlineStatus OnlineStatus) {
		this.onlineStatus = OnlineStatus;
		// presenceComponents.add(os);
	}

	/**
	 * @return the onlineStatus
	 */
	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * Adds a specific OnlinePresenceComponent.
	 * 
	 * @param OnlinePresenceComponent
	 * @return
	 */
	public boolean addComponent(OnlinePresenceComponent OnlinePresenceComponent) {
		return presenceComponents.add(OnlinePresenceComponent);
	}

	/**
	 * Sets the Avatar.
	 * 
	 * @param avatar
	 * @return
	 */
	public boolean setAvatar(Avatar avatar) {
		presenceProperties.remove(getProperty("avatar"));
		return addProperty(avatar);
	}

	/**
	 * 
	 * @param avatarURI
	 * @return
	 */
	public boolean setAvatar(URI avatarURI) {
		return setAvatar(new Avatar(avatarURI));
	}

	/**
	 * 
	 * @param curMessage
	 * @return
	 */
	public boolean setCustomMessage(CustomMessage curMessage) {
		presenceProperties.remove(getProperty("currentMessage"));
		return addProperty(curMessage);
	}

	/**
	 * 
	 * @param curMessage
	 * @return
	 */
	public boolean setCustomMessage(String curMessage) {
		return setCustomMessage(new CustomMessage(curMessage));
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
