/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: Jul 28, 2008
 * @version: 0.1
 */
package service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import presence.OnlinePresence;

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
		//added
		model.setNsPrefix("sioc", "http://rdfs.org/sioc/ns#");
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
	public void serializeToXMLRDF(String rName) {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(rName);
			model.write(fout, "RDF/XML-ABBREV",
					"");
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
			model.write(fout, "N-TRIPLE",
					"http://ggg.milanstankovic.org/opo/ns#");
			fout.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
