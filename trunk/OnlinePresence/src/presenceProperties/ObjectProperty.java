package presenceProperties;

import presence.PresenceClass;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class ObjectProperty extends PresenceProperty<PresenceClass> {

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public ObjectProperty(String name, PresenceClass value) {
		super(name, value);
	}

	@Override
	public void attachAsProperty(Resource res) {
		Model model = ModelFactory.createDefaultModel();
		Resource s = value.createAsNode(model);
		res.addProperty(model.createProperty(getClassURI().toString()), s);
		res.getModel().add(model);
	}
}
