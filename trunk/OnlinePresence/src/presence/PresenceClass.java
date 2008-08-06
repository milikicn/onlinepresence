package presence;

import java.util.LinkedList;

import presenceProperties.PresenceProperty;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
@SuppressWarnings("unchecked")
public abstract class PresenceClass extends OntologyConcept {

	protected LinkedList<PresenceProperty> propertyList = new LinkedList<PresenceProperty>();

	/**
	 * 
	 * @param resource
	 */
	public void makeResource(Resource resource) {
		for (PresenceProperty pp : propertyList) {
			pp.attachAsProperty(resource);
		}
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public Resource createAsBlankNode(Model model){
		Resource s = model.createResource(getURI().toString());
		s.addProperty(RDF.type, model.createResource(getClassURI().toString()));
		makeResource(s);
		
		return s;
	}
	
	/**
	 * 
	 * 
	 * @param list
	 */
	public void setPropertyList(LinkedList<PresenceProperty> list){
		propertyList = list;
	}
}
