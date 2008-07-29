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
import java.net.URI;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import agent.Agent;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
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

	String opoNS = "http://ggg.milanstankovic.org/opo/ns#";

	public OnlinePresence importRDF(String fileName){
		OnlinePresence op = null;
		Model model = getModelFromRDF(fileName);
		
		if(model != null){
			Agent a = null;
			Findability fin = null;
			Notifiability not = null;
			Avatar ava = null;
			CustomMessage cm = null;
			OnlineStatus os = null;
			Disturbability dis = null;
			Contactability con = null;
			Activity act = null;
			Visibility vis = null;
			LinkedList<OnlineStatusComponent> listOSC = new LinkedList<OnlineStatusComponent>();
			LinkedList<OnlinePresenceComponent> listOPC = new LinkedList<OnlinePresenceComponent>();
			Set<PresenceProperty> setPC = new HashSet<PresenceProperty>();
			String presenceURI = null;
			
			StmtIterator iter = model.listStatements();
			
			while(iter.hasNext()){
					
				Statement stat = iter.nextStatement();
				
				String pred = stat.getPredicate().toString();
				String componentClass = pred.substring(pred.lastIndexOf("#") + 1);
				
				String obj = stat.getObject().toString();
				String componentValue = obj.substring(obj.lastIndexOf("#") + 1);
				
				System.out.println("class: " + componentClass + " value: " + componentValue);

				if(componentClass.equalsIgnoreCase("Agent")){
					presenceURI = stat.getSubject().toString();
					a = new Agent();
					a.setURI(URI.create(componentValue));
				}else if(componentClass.equalsIgnoreCase("Findability")){
					presenceURI = stat.getSubject().toString();
					fin = Findability.getInstance(componentValue);
					listOPC.add(fin);
				}else if(componentClass.equalsIgnoreCase("Notifiability")){
					presenceURI = stat.getSubject().toString();
					not = Notifiability.getInstance(componentValue);
					listOPC.add(not);
				}else if(componentClass.equalsIgnoreCase("http://xmlns.com/foaf/0.1/Image")){
					presenceURI = stat.getSubject().toString();
					ava = new Avatar(URI.create(componentValue));
					setPC.add(ava);
				}else if(componentClass.equalsIgnoreCase("string")){
					presenceURI = stat.getSubject().toString();
					cm = new CustomMessage(componentValue);
					setPC.add(cm);
				}else if(componentClass.equalsIgnoreCase("OnlineStatus")){
					os = new OnlineStatus(URI.create(componentValue));
				}else if(componentClass.equalsIgnoreCase("Disturbability")){
					dis = Disturbability.getInstance(componentValue);
					listOSC.add(dis);
				}else if(componentClass.equalsIgnoreCase("Contactability")){
					con = Contactability.getInstance(componentValue);
					listOSC.add(con);
				}else if(componentClass.equalsIgnoreCase("Activity")){
					act = Activity.getInstance(componentValue);
					listOSC.add(act);
				}else if(componentClass.equalsIgnoreCase("Visibility")){
					vis = Visibility.getInstance(componentValue);
					listOSC.add(vis);
				}
			}
			
			os.addComponent(listOSC);
			
			op = new OnlinePresence(a , os, listOPC, setPC);
			op.setURI(URI.create(presenceURI));
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
		
		OnlinePresence o = oim.importRDF("works.rdf");
		
		OPOExporter oex = new OPOExporter(o);
		oex.exportOnlinePresence();
		try {
			oex.serializeToXMLRDF("worksfine.rdf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
