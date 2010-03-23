package net.onlinepresence.services;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresence;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OPOService {

	public static void export(OnlinePresenceBean onlinePresence, String fileName, String syntax){
		
		try {
			Model m = ModelFactory.createDefaultModel();
			m.setNsPrefix("opo", "http://online-presence.net/opo/ns#");
			m.setNsPrefix("opo-actions", "http://online-presence.net/opo-actions/ns#");
			m.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
			m.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
			m.setNsPrefix("sioc", "http://rdfs.org/sioc/ns#");
			m.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
			m.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
			m.setNsPrefix("wgs84", "http://www.w3.org/2003/01/geo/wgs84_pos#");
			m.setNsPrefix("event", "http://purl.org/NET/c4dm/event.owl#");
			m.setNsPrefix("swc", "http://data.semanticweb.org/ns/swc/ontology#");	
			m.setNsPrefix("jenabean", "http://thewebsemantic.com/");
			m.setNsPrefix("doap", "http://usefulinc.com/ns/doap#");

			Bean2RDF writer = new Bean2RDF(m);
			
			writer.saveDeep(onlinePresence);
			m.write(new FileOutputStream(fileName), syntax);
			
		} catch (Exception e) {
			System.err.println("Error in writting");
			e.printStackTrace();
		}
	}
	
	public static OnlinePresence importOP(String fileName, String syntax){
		
		OnlinePresence onlinePresence = null;
		
		try {	
			
			InputStream is = new BufferedInputStream(new FileInputStream(fileName));
			Model m  = ModelFactory.createDefaultModel().
				read(new InputStreamReader(is), "http://online-presence.net/opo/ns#", syntax);
			is.close();
			
			RDF2Bean reader = new RDF2Bean(m);
		//	reader.bindAll("OnlinePresence");
			
			Collection<OnlinePresence> cop  = reader.loadDeep(OnlinePresence.class);			
			Iterator<OnlinePresence> iter = cop.iterator();			
			onlinePresence = iter.next();
						
		} catch (Exception e) {
			System.err.println("Error in reading");
			e.printStackTrace();
		}
		
		return onlinePresence;
	}
}
