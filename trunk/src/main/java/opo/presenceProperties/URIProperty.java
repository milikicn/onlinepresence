package opo.presenceProperties;

import java.net.URI;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class URIProperty extends PresenceProperty<URI> {

	public URIProperty(String name, URI value) {
		super(name, value);

	}

	@Override
	public void attachAsProperty(Resource res) {
		res.addProperty(ModelFactory.createDefaultModel().createProperty(
				getClassURI().toString()), ModelFactory.createDefaultModel()
				.createResource(value.toString()));
	}

}
