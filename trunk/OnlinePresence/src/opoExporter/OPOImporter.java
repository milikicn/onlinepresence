/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 29, 2008
 * @version: 0.1
 */
package opoExporter;

import handlers.AbstractHandler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import presence.OnlinePresence;

/**
 * @author Nikola Milikic
 * 
 */
public class OPOImporter {

	
	public OnlinePresence importRDF(String fileName) throws FileNotFoundException, IOException, InvalidPropertiesFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		OnlinePresence op = new OnlinePresence();;
		Model model = null;
		
		Properties opoProperties = OPOImporter.readXmlProperties("conf/opoProperties.xml");
		model = getModelFromRDF(fileName);
		
		if(model != null){

			StmtIterator iter = model.listStatements();
			
			while(iter.hasNext()){
				
				Statement stat = iter.nextStatement();
				String pred = stat.getPredicate().toString();
				
				System.out.println(pred);
				
				AbstractHandler handler = (AbstractHandler) Class.forName(opoProperties.getProperty(pred)).newInstance();
					
				System.out.println(handler.getClass().getName());
					
				handler.handleNode(op, stat.getSubject(), stat.getObject());
			}
		}
		
		return op;
	}
	
	public static Properties readXmlProperties(String filePath) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.loadFromXML(new FileInputStream(filePath));
		return prop;
	}

	public Model getModelFromRDF(String fileName) throws IOException, FileNotFoundException{
		Model m = null;
		
		InputStream is = new BufferedInputStream(new FileInputStream(
				fileName));
		m  = ModelFactory.createDefaultModel().read(new InputStreamReader(is), "http://ggg.milanstankovic.org/opo/ns#", "N-TRIPLE");
		is.close();
		return m;
	}
	
	public static void main(String[] args) {
		//Ove testove prebaciti u odgovarajuce JUnit metode u folderu test
		OPOImporter oim = new OPOImporter();
		
		OnlinePresence o = null;
		try {
			o = oim.importRDF("works.rdf");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (InvalidPropertiesFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		OPOExporter oex = new OPOExporter(o);
		oex.makeModel();

		oex.serializeToXMLRDF("worksfine.rdf");

	}
}
