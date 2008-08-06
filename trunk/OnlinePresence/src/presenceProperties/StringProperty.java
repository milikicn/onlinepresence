package presenceProperties;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class StringProperty extends PresenceProperty<String> {

	public StringProperty(String name, String value) {
		super(name, value);

	}

	@Override
	public void attachAsProperty(Resource res) {
		res.addProperty(ModelFactory.createDefaultModel().createProperty(
				getClassURI().toString()), value);
	}

}
