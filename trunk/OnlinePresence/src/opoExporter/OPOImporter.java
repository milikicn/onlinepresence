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
import java.net.URI;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import presence.OnlinePresence;
import presenceComponents.*;
import presenceProperties.*;
import statusComponents.*;


/**
 * @author Nikola Milikic
 * 
 */
public class OPOImporter {

	
	public OnlinePresence importRDF(String fileName) throws FileNotFoundException, IOException, InvalidPropertiesFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		OnlinePresence op = null;
		Model model = null;
		
		Properties opoProperties = OPOImporter.readXmlProperties("opoProperties.xml");

		model = getModelFromRDF(fileName);
		
		if(model != null){
/*			Agent a = new Agent();
			Findability fin = null;
			Notifiability not = null;
			OnlineStatus os = null;
			Disturbability dis = null;
			Contactability con = null;
			Activity act = null;
			Visibility vis = null;
			String presenceURI = null;
			LinkedList<PresenceProperty> presenceList = new LinkedList<PresenceProperty>();
			LinkedList<PresenceProperty> statusList = new LinkedList<PresenceProperty>();
	*/		
		
			StmtIterator iter = model.listStatements();
			
			while(iter.hasNext()){
					
				Statement stat = iter.nextStatement();
				
				String pred = stat.getPredicate().toString();
				
				String obj = stat.getObject().toString();
//				String componentValue = obj.substring(obj.lastIndexOf("#") + 1);

			//	System.out.println(stat);
				
				AbstractHandler handler = (AbstractHandler) Class.forName(opoProperties.getProperty(pred)).newInstance();
				
				System.out.println(handler.getClass().getName());
				
				/*if (componentClass.equalsIgnoreCase("http://xmlns.com/foaf/0.1/img")){
						a.addComponent("img", URI.create(componentValue.toString()));
				}else if (componentClass.equalsIgnoreCase("http://xmlns.com/foaf/0.1/name")){
					a.addComponent("name", componentValue);
				}else if(componentValue.equalsIgnoreCase("ConstrainedFindability") ||
						componentValue.equalsIgnoreCase("PubliclyFindable")){
					presenceURI = stat.getSubject().toString();
					fin = Findability.getInstance(componentValue);
					presenceList.add(new URIProperty("hasPresenceComponent",
							fin.getURI()));
				}else if(componentValue.equalsIgnoreCase("AllNotificationsPass") ||
						componentValue.equalsIgnoreCase("NotificationsConstrained") ||
						componentValue.equalsIgnoreCase("NotificationsProhibited")){
					presenceURI = stat.getSubject().toString();
					not = Notifiability.getInstance(componentValue);
					presenceList.add(new URIProperty("hasPresenceComponent",
							not.getURI()));
				}else if(componentClass.equalsIgnoreCase("Avatar")){
					presenceURI = stat.getSubject().toString();
					URIProperty ava = new URIProperty("avatar", URI.create(obj));
					presenceList.add(ava);
				}else if(componentClass.equalsIgnoreCase("customMessage")){
					presenceURI = stat.getSubject().toString();
					StringProperty cm = new StringProperty("customMessage", obj);
					presenceList.add(cm);
				}else if(componentValue.equalsIgnoreCase("OnlineStatus")){
					os = new OnlineStatus(URI.create(obj));
				}else if(componentValue.equalsIgnoreCase("Available") ||
						componentValue.equalsIgnoreCase("DoNotDisturb")){
					dis = Disturbability.getInstance(componentValue);
					statusList.add(new URIProperty("hasStatusComponent", dis.getURI()));
				}else if(componentValue.equalsIgnoreCase("FreelyContactable") ||
						componentValue.equalsIgnoreCase("ConstrainedContactability")){
					con = Contactability.getInstance(componentValue);
					statusList.add(new URIProperty("hasStatusComponent", con.getURI()));
				}else if(componentValue.equalsIgnoreCase("Active") ||
						componentValue.equalsIgnoreCase("Inactive") ||
						componentValue.equalsIgnoreCase("ProlongedInactive")){					
					act = Activity.getInstance(componentValue);
					statusList.add(new URIProperty("hasStatusComponent", act.getURI()));
				}else if(componentValue.equalsIgnoreCase("Visible") ||
						componentValue.equalsIgnoreCase("Invisible")){
					vis = Visibility.getInstance(componentValue);
					statusList.add(new URIProperty("hasStatusComponent", vis.getURI()));
				}*/
			}
			
/*			os.setPropertyList(statusList);
									
			op = new OnlinePresence(null, URI.create(presenceURI));
			//op.setAgent(a);
			op.setPropertyList(presenceList);
			
			op.setOnlineStatus(os);
			
			op.setAgent(a);*/
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
		m  = ModelFactory.createDefaultModel().read(new InputStreamReader(is), "http://ggg.milanstankovic.org/opo/ns#");
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
		
/*		OPOExporter oex = new OPOExporter(o);
		oex.makeModel();
		try {
			oex.serializeToXMLRDF("worksfine.rdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
}
