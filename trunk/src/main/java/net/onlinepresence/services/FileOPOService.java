package net.onlinepresence.services;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresence;

public class FileOPOService extends DefaultOPOService {


	public void saveOnlinePresenceToFile(OnlinePresenceBean onlinePresence,
			String fileName, String syntax) {

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("opo", "http://online-presence.net/opo/ns#");
		model.setNsPrefix("opo-actions",
				"http://online-presence.net/opo-actions/ns#");
		model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		model.setNsPrefix("sioc", "http://rdfs.org/sioc/ns#");
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		model.setNsPrefix("wgs84", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		model.setNsPrefix("event", "http://purl.org/NET/c4dm/event.owl#");
		model
				.setNsPrefix("swc",
						"http://data.semanticweb.org/ns/swc/ontology#");
		model.setNsPrefix("jenabean", "http://thewebsemantic.com/");
		model.setNsPrefix("doap", "http://usefulinc.com/ns/doap#");

		model = super.save(model, onlinePresence);
		
		try {
			model.write(new FileOutputStream(fileName), syntax);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public OnlinePresence loadOnlinePresenceFromFile(String fileName, String syntax) {
		InputStream is;
		Model model = null;
		try {
			is = new BufferedInputStream(new FileInputStream(fileName));
			model = ModelFactory.createDefaultModel().read(
					new InputStreamReader(is),
					"http://online-presence.net/opo/ns#", syntax);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collection<OnlinePresence> cop = super.load(model);
		
		Iterator<OnlinePresence> iter = cop.iterator();
		return iter.next();
	}

}
