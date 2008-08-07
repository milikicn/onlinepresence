/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: Jul 26, 2008
 * @version: 0.1
 */
package agent;

import java.net.URI;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import presence.OnlinePresence;
import presence.PresenceClass;


/**
 * @author Filip Radulovic
 * 
 */
public class Agent extends PresenceClass {

	public static String AGENTNS = "http://xmlns.com/foaf/0.1/";

	/**
	 * Plain constructor.
	 */
	public Agent(){}
	
	/**
	 * Constructor tha recives URI of the agent.
	 * @param uri
	 */
	public Agent(URI uri) {
		setURI(uri);
	}
	
	public Agent(String stringURI){
		this(URI.create(stringURI));
	}
	
		
	/* (non-Javadoc)
	 * @see presence.OntologyConcept#getNameSpace()
	 */
	@Override
	public String getNameSpace() {
		return Agent.AGENTNS;
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
		propertyList.add(new AgentStringProperty(name, content));
	}
	
	/**
	 * Adds new component to agent's property list with the name 'name' and the 'uri' as the URI of the property.
	 * @param name
	 * @param uri
	 */
	public void addComponent(String name, URI uri) {
		propertyList.add(new AgentURIProperty(name, uri));
	}
}
