package net.onlinepresence.services.datacentral;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class FileDataProvider implements DataProvider {

	private String dataSourceUrl;
	private String rdfLang;

	/**
	 * @param dataSourceUrl
	 * @param rdfLang
	 */
	public FileDataProvider(String dataSourceUrl, String rdfLang) {
		super();
		this.dataSourceUrl = dataSourceUrl;
		this.rdfLang = rdfLang;
	}

	@Override
	public Model getData() {

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
		model.setNsPrefix("swc", "http://data.semanticweb.org/ns/swc/ontology#");
		model.setNsPrefix("jenabean", "http://thewebsemantic.com/");
		model.setNsPrefix("doap", "http://usefulinc.com/ns/doap#");
		
		File dataFile = new File(dataSourceUrl);
		
		if(dataFile.exists()){
			try {
				model.read(new FileInputStream(dataSourceUrl),
						"http://online-presence.net/opo/ns#", rdfLang);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return model;

	}

	@Override
	public void updateDataSource(Model m) {
		try {
			m.write(new FileOutputStream(dataSourceUrl), rdfLang,
					"http://online-presence.net/opo/ns#");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
