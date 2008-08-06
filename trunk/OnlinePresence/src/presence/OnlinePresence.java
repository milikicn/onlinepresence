/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presence;

import java.net.URI;
import java.util.LinkedList;

import presenceComponents.OnlinePresenceComponent;
import presenceComponents.OnlineStatus;
import presenceProperties.ObjectProperty;
import presenceProperties.PresenceProperty;
import presenceProperties.StringProperty;
import presenceProperties.URIProperty;
import agent.Agent;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlinePresence extends PresenceClass {

	public static String ONLINEPRESENCENS = "http://ggg.milanstankovic.org/opo/ns#";
	private Agent agent;
	private PresenceClass os;

	/**
	 * 
	 * @param agent
	 * @param uri
	 */
	public OnlinePresence(Agent agent, URI uri) {
		setURI(uri);
		this.agent = agent;
	}

	/**
	 * 
	 * @return
	 */
	public PresenceClass getOnlineStatus() {
		return os;
	}
	
	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}
	
	/**
	 * 
	 * @param agent
	 */
	public void setAgent(Agent agent){
		propertyList.add(new ObjectProperty("declaredBy", agent));
	}

	/**
	 * Sets the current OnlinePresence Status.
	 * 
	 * @param OnlineStatus
	 */
	public void setOnlineStatus(OnlineStatus onlineStatus) {
		propertyList.add(new ObjectProperty("hasPresenceComponent", onlineStatus));
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
		
}
