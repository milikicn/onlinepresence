package presence;

import java.util.LinkedList;

import agent.Agent;

import presenceComponents.OnlinePresenceComponent;
import presenceComponents.OnlineStatus;

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
	LinkedList<OnlinePresenceComponent> presenceComponents = new LinkedList<OnlinePresenceComponent>();;
	Image image;
	
	//Sets the current OnlinePresence Status
	public void setStatus(OnlineStatus OnlineStatus){
		this.onlineStatus = OnlineStatus;
		//presenceComponents.add(os);
	}
	
	//Ads a specific OnlinePresenceComponent
	public void addComponent(OnlinePresenceComponent OnlinePresenceComponent){
		presenceComponents.add(OnlinePresenceComponent);
	}
	
	//Sets the Image (Avatar)
	public void setImage(Image image){
		this.image = image;
	}
	
	public void setDeclaredByAgent(Agent agent){
		this.agent = agent;
	}
}
