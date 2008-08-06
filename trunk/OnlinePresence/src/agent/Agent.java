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
	
	private OnlinePresence onlinePresence;

	/**
	 * Plain constructor
	 */
	public Agent(){}
	
	/**
	 * Constructor tha recives URI of the agent
	 * @param uri
	 */
	public Agent(URI uri) {
		setURI(uri);
	}
		
	/* (non-Javadoc)
	 * @see presence.OntologyConcept#getNameSpace()
	 */
	@Override
	public String getNameSpace() {
		return Agent.AGENTNS;
	}

	/**
	 * Returns the value of the field containing current online presence of the Agent
	 * @return the onlinePresence
	 */
	public OnlinePresence getOnlinePresence() {
		return onlinePresence;
	}
	
	/**
	 * Sets Agent's OnlinePresence properties.
	 * 
	 * @param OnlinePresence
	 */
	public void setOnlinePresence(OnlinePresence OnlinePresence) {
		this.onlinePresence = OnlinePresence;
	}

	/**
	 * 
	 * @param name
	 * @param content
	 */
	public void addComponent(String name, String content) {
		propertyList.add(new AgentStringProperty(name, content));
	}
	
	/**
	 * 
	 * @param name
	 * @param uri
	 */
	public void addComponent(String name, URI uri) {
		propertyList.add(new AgentURIProperty(name, uri));
	}
	
	public Resource createAsBlankNode(Model model){
		Resource s = null;
		if(getURI() != null)
			s = model.createResource(getURI().toString());
		else{
			s = model.createResource();
			s.addProperty(RDF.type, model.createResource(getClassURI().toString()));
			makeResource(s);
		}
		return s;
	}
}
