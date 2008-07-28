package agent;

import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.*;

import presence.OnlinePresence;
import presenceComponents.OnlinePresenceComponent;
import presenceComponents.OnlineStatus;
import statusComponents.OnlineStatusComponent;

public class OPOExporter {
	
	OnlinePresence onlinePresence;
	OnlineStatus onlineStatus;
	Model model = ModelFactory.createDefaultModel();
	Resource resource;

	public OPOExporter(Agent agent){
		this.onlinePresence = agent.onlinePresence;
		this.onlineStatus = agent.onlinePresence.getOnlineStatus();
		resource = model.createResource(agent.getUri().toString());
	}
	
	public OPOExporter(OnlinePresence op){
		this.onlinePresence = op;
		this.onlineStatus = op.getOnlineStatus();
		resource = model.createResource(op.getAgent().getUri().toString());
	}
	
	public void exportOnlinePresence(){
		Resource os;
		LinkedList<OnlinePresenceComponent> presenceComponents = onlinePresence.getPresenceComponents();
		for(int i=0; i<presenceComponents.size(); i++){
			if (presenceComponents.get(i) instanceof OnlineStatus){
				os = model.createResource(onlineStatus.getClassURI().toString());
				LinkedList<OnlineStatusComponent> statusComponents = onlineStatus.getStatusComponents();
				for (int j = 0; j < statusComponents.size(); j++) {
					os.addProperty(RDFS.subClassOf, statusComponents.get(i).toString());
				}
				resource.addProperty(RDFS.subClassOf, os);
			}
			else{
				resource.addProperty(RDFS.subClassOf, presenceComponents.get(i).toString());
			}
		}
	}
}
