/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: July 26, 2008
 * @version: 0.1
 */
package agent;

import java.net.URI;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import presence.OnlinePresence;
import presence.PresenceClass;
import presenceProperties.*;


/**
 * @author Filip Radulovic
 * 
 */
public class Agent extends PresenceClass {

	/**
	 * Plain constructor.
	 */
	public Agent(){}
	
	/**
	 * Constructor that receives URI of the agent.
	 * @param uri
	 */
	public Agent(URI uri) {
		setURI(uri);
	}
	
	public Agent(String stringURI){
		this(URI.create(stringURI));
	}
		
	/**
	 * 
	 * @return
	 */
	public OnlinePresence getOnlinePresence(){
		for (int i = 0; i < propertyList.size(); i++) {
			if(propertyList.get(i).getValue() instanceof OnlinePresence){
				return (OnlinePresence)propertyList.get(i).getValue();
			}
		}
		return null;
	}
	
/*	*//**
	 * Sets Agent's OnlinePresence properties.
	 * 
	 * @param OnlinePresence
	 *//*
	public void setOnlinePresence(OnlinePresence OnlinePresence) {
		propertyList.add(new ObjectProperty)
	}*/

	/**
	 * Adds new component to agent's property list with the name 'name' and content 'content'.
	 * @param name
	 * @param content
	 */
	public void addComponent(String name, String content) {
		addProperty(new StringProperty(name, content));
	}
	
	/**
	 * Adds new component to agent's property list with the name 'name' and the 'uri' as the URI of the property.
	 * @param name
	 * @param uri
	 */
	public void addComponent(String name, URI uri) {
		addComponent(new URIProperty(name, uri));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void addComponent(T component) {
		PresenceProperty pp = (PresenceProperty) component;
		addProperty(pp);
	}
}
