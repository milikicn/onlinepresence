/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presence;

import java.net.URI;

import presenceComponents.OnlinePresenceComponent;
import presenceProperties.ObjectProperty;
import presenceProperties.StringProperty;
import presenceProperties.URIProperty;
import agent.Agent;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlinePresence extends PresenceClass {

	private Agent agent;
	private PresenceClass os;

	/**
	 * @param agent
	 * @param onlineStatus
	 * @param presenceComponents
	 * @param presenceProperties
	 * @param startTime
	 */
	public OnlinePresence(Agent agent, URI uri) {

		setURI(uri);

		this.agent = agent;

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
	 * Sets the current OnlinePresence Status.
	 * 
	 * @param OnlineStatus
	 */
	public void setOnlineStatus(PresenceClass onlineStatus) {
		propertyList.add(new ObjectProperty("hasPresenceComponent",
				onlineStatus));
	}

	/**
	 * adds a specific OnlineStatusComponent
	 * 
	 * @param onlineStatusComponent
	 */
	public void addComponent(OnlinePresenceComponent onlinePresenceComponent) {
		propertyList.add(new URIProperty("hasPresenceComponent",
				onlinePresenceComponent.getURI()));
	}

	/**
	 * 
	 * @param avatarURI
	 * @return
	 */
	public void setAvatar(URI avatarURI) {
		propertyList.add(new URIProperty("avatar", avatarURI));
	}

	/**
	 * 
	 * @param curMessage
	 * @return
	 */
	public void setCustomMessage(String curMessage) {
		propertyList.add(new StringProperty("customMessage", curMessage));
	}

	/**
	 * 
	 * @param agent
	 */
	public void setDeclaredByAgent(Agent agent) {
		this.agent = agent;
	}

	public PresenceClass getOnlineStatus() {
		return os;
	}

}
