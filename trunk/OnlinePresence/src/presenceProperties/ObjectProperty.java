package presenceProperties;

import presence.PresenceClass;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class ObjectProperty extends PresenceProperty<PresenceClass> {

	public ObjectProperty(String name, PresenceClass value) {
		super(name, value);

	}

	@Override
	public void attachProperty(Resource res) {
		Model model = ModelFactory.createDefaultModel();
		Resource s = model.createResource(value.getURI().toString());
		s.addProperty(RDF.type, model.createResource(value.getClassURI()
				.toString()));
		value.makeResource(s);
		res.addProperty(model.createProperty(getClassURI().toString()), s);
		res.getModel().add(model);

	}

}
