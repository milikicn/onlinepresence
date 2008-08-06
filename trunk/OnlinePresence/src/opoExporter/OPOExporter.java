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

import presence.OnlinePresence;
import presence.OntologyConcept;
import presence.PresenceClass;
import presenceComponents.Findability;
import presenceComponents.Notifiability;
import presenceComponents.OnlineStatus;
import statusComponents.Activity;
import statusComponents.Contactability;
import statusComponents.Disturbability;
import statusComponents.Visibility;
import agent.Agent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * @author Filip Radulovic
 * 
 */
public class OPOExporter {

	private String opoNS = "http://ggg.milanstankovic.org/opo/ns#";
	private OnlinePresence onlinePresence;
	private Model model = ModelFactory.createDefaultModel();
	{
		model.setNsPrefix("opo", opoNS);
		model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
	}

	/**
	 * 
	 * @param agent
	 */
	public OPOExporter(Agent agent) {
		this(agent.getOnlinePresence());
		onlinePresence.setAgent(agent);
	}

	/**
	 * @param op
	 */
	public OPOExporter(OnlinePresence op) {
		this.onlinePresence = op;
	}

	/**
	 * 
	 */

	public void makeModel() {
		Resource resource = model.createResource(onlinePresence.getURI()
				.toString());
		resource.addProperty(RDF.type, model.createResource(onlinePresence
				.getClassURI().toString()));

		onlinePresence.makeResource(resource);
	}
/*
	*//**
	 * 
	 * @param <T>
	 * @param opc
	 * @return
	 *//*
	private <T extends OntologyConcept> Property returnAsProperty(T opc) {
		return model.createProperty(opoNS + "hasPresenceComponent");
	}*/

	/**
	 * 
	 * @param rName
	 * @throws FileNotFoundException
	 */
	public void serializeToXMLRDF(String rName) throws FileNotFoundException {
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
	public void serializeToRDFTurtle(String rName) throws FileNotFoundException {
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
		a.addComponent("name", "nikola milikic");
		a.addComponent("img", URI.create("http://mojaslika.com/slika.jpg"));
		
		OnlineStatus os = new OnlineStatus(URI
				.create("http://nekiUriZaOnlineStatus.com"));
		os.addComponent(Disturbability.AVAILABLE);
		os.addComponent(Contactability.FREELY_CONTACTABLE);
		os.addComponent(Activity.ACTIVE);
		os.addComponent(Visibility.INVISIBLE);

		OnlinePresence op = new OnlinePresence(null, URI
				.create("http://nekiUriZaOnlinePresence.com"));
		op.setURI(URI.create("http://nekiUriZaOnlinePresence.com"));
		op.setOnlineStatus(os);

		op.addComponent(Findability.PUBLICLY_FINDABLE);
		op.addComponent(Notifiability.NOTIFICATIONS_CONSTRAINED);

		op.setAvatar(URI.create("http://nikola.em3.rs/images/photo.jpg"));

		op.setCustomMessage("watching a game, having a bud");
		
		op.setAgent(a);

		// Model m = ModelFactory.createDefaultModel();
		// Resource res = m.createResource("");
		// res.addLiteral(RDF.type, "http://www.fdfd.com");
		//		
		// try {
		// m.write(new FileOutputStream("aaa.rdf"), "RDF/XML-ABBREV");
		// } catch (FileNotFoundException e1) {
		//			
		// e1.printStackTrace();
		// }

		OPOExporter oe = new OPOExporter(op);

		oe.makeModel();
		try {
			oe.serializeToXMLRDF("works.rdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
