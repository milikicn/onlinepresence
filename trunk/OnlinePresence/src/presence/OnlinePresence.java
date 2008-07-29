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
public class OnlinePresence extends OntologyConcept{

	private Agent agent;

	private OnlineStatus onlineStatus;
	
//	private Date startTime;
	
//	private Date duration;

	LinkedList<OnlinePresenceComponent> presenceComponents = new LinkedList<OnlinePresenceComponent>();

	Set<PresenceProperty> presenceProperties = new HashSet<PresenceProperty>();

	/**
	 * @param agent
	 * @param onlineStatus
	 * @param presenceComponents
	 * @param presenceProperties
	 * @param startTime
	 */
	public OnlinePresence(Agent agent, OnlineStatus onlineStatus,
			LinkedList<OnlinePresenceComponent> presenceComponents,
			Set<PresenceProperty> presenceProperties) {
		this.agent = agent;
		this.onlineStatus = onlineStatus;
		this.presenceComponents = presenceComponents;
		this.presenceProperties = presenceProperties;
//		this.startTime = Calendar.getInstance().getTime();
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
	 * @return the onlineStatus
	 */
	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
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
	 * 
	 * @return
	 */
	public LinkedList<OnlinePresenceComponent> getPresenceComponents(){
		return presenceComponents;
	}
	
	/**
	 * @param presenceComponents the presenceComponents to set
	 */
	public void setPresenceComponents(
			LinkedList<OnlinePresenceComponent> presenceComponents) {
		this.presenceComponents = presenceComponents;
	}
	
	/**
	 * @return the presenceProperties
	 */
	public Set<PresenceProperty> getPresenceProperties() {
		return presenceProperties;
	}

	/**
	 * @param presenceProperties the presenceProperties to set
	 */
	public void setPresenceProperties(Set<PresenceProperty> presenceProperties) {
		this.presenceProperties = presenceProperties;
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
	 * Returns an instance of the class Property with 'pName' as its name.
	 * 
	 * @param pName
	 * @return
	 */
	public PresenceProperty getProperty(String pName) {
		PresenceProperty prop = null;
		Iterator<PresenceProperty> it = presenceProperties.iterator();

		while (it.hasNext()) {
			PresenceProperty temp = it.next();
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
	public boolean addProperty(PresenceProperty property) {
		return presenceProperties.add(property);
	}
}

