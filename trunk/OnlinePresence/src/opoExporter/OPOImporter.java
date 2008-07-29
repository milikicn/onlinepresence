/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 29, 2008
 * @version: 0.1
 */
package opoExporter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import presence.OnlinePresence;

/**
 * @author Nikola Milikic
 * 
 */
public class OPOImporter {

	String opoNS = "http://ggg.milanstankovic.org/opo/ns#";

	public OnlinePresence importRDF(String fileName){
		OnlinePresence op = null;
		Model model = getModelFromRDF(fileName);
		
		if(model != null){
			
			
//			op = new OnlinePresence();
		}
		
		return op;
	}

	public Model getModelFromRDF(String fileName) {
		Model m = null;
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(
					fileName));
			m = ModelFactory.createDefaultModel();
			return m.read(new InputStreamReader(is), "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return m;
		}
	}
	
	public static void main(String[] args) {
		OPOImporter oim = new OPOImporter();
		
		oim.importRDF("works.rdf");
	}
}
