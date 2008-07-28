/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: Jul 28, 2008
 * @version: 0.1
 */
package opoExporter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import presence.OnlinePresence;
import presence.OntologyConcept;
import presenceComponents.*;
import presenceProperties.*;
import statusComponents.*;

/**
 * @author Filip Radulovic
 * 
 */
public class OPOExporter {
	
	OnlinePresence onlinePresence;
	OnlineStatus onlineStatus;
	Model model = ModelFactory.createDefaultModel();
	{
		model.setNsPrefix("opo", "http://ggg.milanstankovic.org/opo/ns#");
	}
	Resource resource = model.createResource("http://ggg.milanstankovic.org/opo/ns#OnlinePresence");

	/**
	 * 
	 * @param agent
	 */
	public OPOExporter(Agent agent){
		this.onlinePresence = agent.getOnlinePresence();
		this.onlineStatus = agent.getOnlinePresence().getOnlineStatus();
//		resource = model.createResource(agent.getURI().toString());
	}
	
	/**
	 * 
	 * @param op
	 */
	public OPOExporter(OnlinePresence op){
		this.onlinePresence = op;
		this.onlineStatus = op.getOnlineStatus();
//		resource = model.createResource(op.getAgent().getURI().toString());
	}
	
	/**
	 * 
	 */
	public void exportOnlinePresence(){
		LinkedList<OnlinePresenceComponent> presenceComponents = onlinePresence.getPresenceComponents();
		
		for(int i=0; i<presenceComponents.size(); i++){
			resource.addProperty(returnAsProperty(presenceComponents.get(i)), presenceComponents.get(i).getURI().toString());
		}
		
//		Iterator<PresenceProperty> it = onlinePresence.getPresenceProperties().iterator();
//		while(it.hasNext()){
//			PresenceProperty pp = it.next();
//			resource.addProperty(returnAsProperty(pp), pp.);
//		}
				
		resource.addProperty(returnAsProperty(onlineStatus), getResource(onlineStatus));
		
	}
	
	/**
	 * 
	 * @param onlineStatus
	 * @return
	 */
	private RDFNode getResource(OnlineStatus onlineStatus){
		Resource res = model.createResource(onlineStatus.getClassURI().toString());
		
		LinkedList<OnlineStatusComponent> statusComponents = onlineStatus.getStatusComponents();
		for (int j = 0; j < statusComponents.size(); j++) {
			res.addProperty(returnAsProperty(statusComponents.get(j)), statusComponents.get(j).getURI().toString());
		}
		return res;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param opc
	 * @return
	 */
	private <T extends OntologyConcept> Property returnAsProperty(T opc){
		return ModelFactory.createDefaultModel().createProperty(opc.getClassURI().toString());
	}
	
	/**
	 * 
	 * @param rName
	 * @throws FileNotFoundException
	 */
	public void serializeToXMLRDF(String rName)throws FileNotFoundException{
		FileOutputStream fout = new FileOutputStream(rName);
		model.write(fout, "RDF/XML-ABBREV");
		try {
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param rName
	 * @throws FileNotFoundException
	 */
	public void serializeToRDFTurtle(String rName)throws FileNotFoundException{
		FileOutputStream fout = new FileOutputStream(rName);
		model.write(fout, "N-TRIPLE");
		try {
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Agent a = new Agent();
		try {
			a.setURI(new URI("http://nikola.em3.rs/foaf.rdf#nikola"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		OnlineStatus os = new OnlineStatus();
		os.addComponent(Disturbability.AVAILABLE);
		os.addComponent(Contactability.FREELY_CONTACTABLE);
		os.addComponent(Activity.ACTIVE);
		os.addComponent(Activity.ACTIVE);

		LinkedList<OnlinePresenceComponent> li = new LinkedList<OnlinePresenceComponent>();
		li.add(Findability.PUBLICLY_FINDABLE);
		li.add(Notifiability.NOTIFICATIONS_CONSTRAINED);
		
		Set<PresenceProperty> ha = new HashSet<PresenceProperty>();
		try {
			ha.add(new Avatar(new URI("http://nikola.em3.rs/images/photo.jpg")));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ha.add(new CustomMessage("watching a game, having a bud"));
		
		OnlinePresence o = new OnlinePresence(a, os, li, ha);
		
		OPOExporter oe = new OPOExporter(o);
		
		oe.exportOnlinePresence();
		try {
			oe.serializeToXMLRDF("works.rdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
