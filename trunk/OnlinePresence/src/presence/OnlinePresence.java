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
import statusComponents.OnlineStatusComponent;
import agent.Agent;

/**
 * @author Nikola Milikic
 * 
 */
public class OnlinePresence extends PresenceClass {

	public static String ONLINEPRESENCENS = "http://ggg.milanstankovic.org/opo/ns#";

	/**
	 * 
	 * @param agent
	 * @param uri
	 */
	public OnlinePresence(Agent agent, URI uri) {
		setURI(uri);
		if(agent!= null)
			setAgent(agent);
	}
	
	//ovo sam dodao kako bi se u klasi OPOImporter mogao instancirati prazan objekat 
	//ove klase
	public OnlinePresence(){}
	
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
	public <T> void addComponent(T onlinePresenceComponent) {
		OnlinePresenceComponent opc = (OnlinePresenceComponent) onlinePresenceComponent;
		propertyList.add(new URIProperty("hasPresenceComponent",
				opc.getURI()));
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
	
	//u klasi koja poziva ovu metodu treba obraditi sta se desava ako se vrati null     N.
	/**
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PresenceProperty getComponent(String name){
		PresenceProperty pp = null;
		for (int i = 0; i < propertyList.size(); i++) {
			PresenceProperty temp = propertyList.get(i);
			if(temp.getName().equals(name)){
				pp = temp;
				break;
			}
		}
		return pp;
	}
	
	//treba baciti izuzetak ako nista ne uradi ili upisati u log (ako cemo to da ubacujemo)
	@SuppressWarnings("unchecked")
	public void addComponentToOnlineStatus(OnlineStatusComponent osc){
		PresenceProperty<ObjectProperty> os = getComponent("onlineStatus");
		if(os != null){
			os.getValue()
		}
	}
}
