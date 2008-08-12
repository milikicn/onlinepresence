/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presence;

import java.net.URI;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import presenceComponents.OnlinePresenceComponent;
import presenceComponents.OnlineStatus;
import presenceProperties.*;
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
		this();
		setURI(uri);
		if(agent!= null)
			setAgent(agent);
	}
	
	public OnlinePresence(){}
	
	/**
	 * 
	 * @param agent
	 */
	public void setAgent(Agent agent){
		addProperty(new ObjectProperty("declaredBy", agent));
	}

	/**
	 * Sets the current OnlinePresence Status.
	 * 
	 * @param OnlineStatus
	 */
	public void setOnlineStatus(OnlineStatus onlineStatus) {
		addProperty(new ObjectProperty("hasPresenceComponent", onlineStatus));
	}
	
	/**
	 * adds a specific OnlineStatusComponent
	 * 
	 * @param onlineStatusComponent
	 */
	public <T> void addComponent(T onlinePresenceComponent) {
		OnlinePresenceComponent opc = (OnlinePresenceComponent) onlinePresenceComponent;
		addProperty(new URIProperty("hasPresenceComponent",
				opc.getURI()));
	}

	/**
	 * 
	 * @param avatarURI
	 * @return
	 */
	public void setAvatar(URI avatarURI) {
		addProperty(new URIProperty("avatar", avatarURI));
	}

	/**
	 * 
	 * @param curMessage
	 * @return
	 */
	public void setCustomMessage(String curMessage) {
		addProperty(new StringProperty("customMessage", curMessage));
	}
	
	/**
	 * 
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
		addProperty(new StringProperty("startTime", startTime));
	}
	
	/**
	 * 
	 * @param duration
	 */
	public void setDuration(String duration) {
		addProperty(new StringProperty("duration", duration));
	}
	
	/**
	 * 
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		Format formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		setStartTime(formater.format(startTime));
	}
	
	//u klasi koja poziva ovu metodu treba obraditi sta se desava ako se vrati null     N.
	/**
	 * 
	 * @param name
	 * @return
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public PresenceClass getObjectProperty(String className){
		PresenceClass pc = null;
		
		for (int i = 0; i < propertyList.size(); i++) {
			PresenceProperty temp = propertyList.get(i);
			
			if( (temp instanceof ObjectProperty)){
				ObjectProperty op = (ObjectProperty) temp;
				
				if(op.getValue().getClass().getName().endsWith(className)){
					pc = op.getValue();
					break;
				}
			}
		}
		return pc;
	}
	
	//treba baciti izuzetak ako nista ne uradi ili upisati u log (ako cemo to da ubacujemo)
	public void addComponentToOnlineStatus(OnlineStatusComponent onlineStatusComponent){
		OnlineStatus os;

		os = (OnlineStatus) getObjectProperty("OnlineStatus");
		
		if(os == null){
			os = new OnlineStatus();
			setOnlineStatus(os);
		}
		
		os.addComponent(onlineStatusComponent);
	}
	
	public Agent getAgent(){
		PresenceClass temp = getObjectProperty("Agent");
		Agent agent = null;
		
		if(temp == null){
			agent = new Agent();
			setAgent(agent);
		}else
			agent = (Agent) temp;
		
		return agent;
	}
	
//	public PresenceClass getOrCreateObjectProperty(String className){
//		PresenceClass presenceComponent = getObjectProperty(className);
//		
//		if(presenceComponent == null)
//			addProperty(new ObjectProperty(null, null));
//		
//		return presenceComponent;
//	}
}
