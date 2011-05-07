package net.onlinepresence.opos.tapestry.rest.util;

import java.io.StringWriter;

import net.onlinepresence.jopo.ontmodel.general.Resource;

import thewebsemantic.Bean2RDF;

//import com.epimorphics.jsonrdf.Encoder;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class JSONFormater {
	
	public String exportToJSON(Resource resource) {
		Model tempModel = ModelFactory.createDefaultModel();
		
		Bean2RDF b2rwriter = new Bean2RDF(tempModel);
		b2rwriter.saveDeep(resource);
		
		StringWriter writer = new StringWriter();
		
//		try {
//			Encoder.get().encode(tempModel, writer, true);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return writer.toString();
	}
	
//	public Model importFromJSONFile(String pathtofile) {
//		Model m = ModelFactory.createDefaultModel();
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(pathtofile);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		try {
//			ResultSet results = ResultSetFactory.fromJSON(fis);
//			for (; results.hasNext();) {
//				QuerySolution soln = results.nextSolution();
//				Resource s = soln.getResource("s");
//				// Turn resources into properties because they have URIs
//				Property p = m.createProperty(soln.getResource("p").getURI());
//				RDFNode o = soln.get("o");
//				m.add(s, p, o);
//			}
//		} finally { // nothing
//		}
//		return m;
//	}
}
