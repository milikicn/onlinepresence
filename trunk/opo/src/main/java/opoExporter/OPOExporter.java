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

import presence.OnlinePresence;
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
		onlinePresence.createAsNode(model);
	}


	/**
	 * 
	 * @param rName
	 * @throws FileNotFoundException
	 */
	public void serializeToXMLRDF(String rName){
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(rName);
			model.write(fout, "RDF/XML-ABBREV", "http://ggg.milanstankovic.org/opo/ns#");
			fout.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param rName
	 * @throws FileNotFoundException
	 */
	public void serializeToRDFNTripple(String rName) {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(rName);
			model.write(fout, "N-TRIPLE", "http://ggg.milanstankovic.org/opo/ns#");
			fout.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Agent a = new Agent("http://nekiURIZaAgenta.com");
		a.addComponent("name", "nikola milikic");
		a.addComponent("img", URI.create("http://mojaslika.com/slika.jpg"));
		
		OnlineStatus os = new OnlineStatus();
		os.addComponent(Disturbability.AVAILABLE);
		os.addComponent(Contactability.FREELY_CONTACTABLE);
		os.addComponent(Activity.ACTIVE);
		os.addComponent(Visibility.INVISIBLE);

		OnlinePresence op = new OnlinePresence(null, URI.create("http://nekiURIZaOnlinePresence.com"));
		op.setOnlineStatus(os);

		op.addComponent(Findability.PUBLICLY_FINDABLE);
		op.addComponent(Notifiability.NOTIFICATIONS_CONSTRAINED);

		op.setAvatar(URI.create("http://nikola.em3.rs/images/photo.jpg"));

		op.setCustomMessage("watching a game, having a bud");
		
		op.setAgent(a);
		
		op.setStartTime("2008-03-01T18:51:19");
		
		op.setDuration("T18:51:19");

		OPOExporter oe = new OPOExporter(op);

		oe.makeModel();

		oe.serializeToXMLRDF("works.rdf");
	}
}
