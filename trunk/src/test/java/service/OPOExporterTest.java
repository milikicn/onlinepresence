package service;

import java.net.URI;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;


import presence.OnlinePresence;
import presenceComponents.StatusMessage;
import presenceComponents.Findability;
import presenceComponents.Notifiability;
import presenceComponents.OnlineStatus;
import statusComponents.Activity;
import statusComponents.Contactability;
import statusComponents.Disturbability;
import statusComponents.Visibility;
import agent.Agent;

public class OPOExporterTest {

	public static void main(String[] args) {
		Agent a = new Agent("http://nekiURIZaAgenta.com");
		a.addComponent("name", "nikola milikic");
		
		StatusMessage cm = new StatusMessage("http://nekiURIZaCM.com");
		cm.addComponent("content", "neki neki tekst");
		
		Model model = ModelFactory.createDefaultModel();
		a.addComponent("img", URI.create("http://mojaslika.com/slika.jpg"));
//		a.addComponent("mbox", URI.create("mailto:filiprd@gmail.com"));
		
		OnlineStatus os = new OnlineStatus();
		os.addComponent(Disturbability.AVAILABLE);
		os.addComponent(Contactability.FREELY_CONTACTABLE);
		os.addComponent(Activity.ACTIVE);
		os.addComponent(Visibility.INVISIBLE);

		OnlinePresence op = new OnlinePresence(null, URI.create("http://nekiURIZaOnlinePresence.com"));
		op.setOnlineStatus(os);

		op.addComponent(Findability.PUBLICLY_FINDABLE);
		op.addComponent(Notifiability.NOTIFICATIONS_CONSTRAINED);

		op.setAvatar(URI.create("http://nikola.em3.rs/images/photo.jpg"));

//		op.setCustomMessage("watching a game, having a bud");
		
		op.setAgent(a);
		
		op.setStatusMessage(cm);
		
		op.setStartTime("2008-03-01T18:51:19");
		
		op.setDuration("T18:51:19");

		OPOExporter oe = new OPOExporter(op);

		oe.makeModel();

		oe.serializeToXMLRDF("exported.rdf");

	}
}
