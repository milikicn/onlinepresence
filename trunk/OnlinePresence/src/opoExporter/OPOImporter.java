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
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

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
			StmtIterator iter = model.listStatements();
			
			while(iter.hasNext()){
				Statement stat = iter.nextStatement();
				
				String res = stat.getPredicate().toString();
				String componentClass = res.substring(res.lastIndexOf("#") + 1);
				
				System.out.println(componentClass);

	//			System.out.println(stringStat.substring(stringStat.lastIndexOf("#")));
			}
			
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
