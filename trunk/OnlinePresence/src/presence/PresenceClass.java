package presence;

import java.util.LinkedList;

import presenceProperties.PresenceProperty;

import com.hp.hpl.jena.rdf.model.Resource;
@SuppressWarnings("unchecked")
public abstract class PresenceClass extends OntologyConcept {

	protected LinkedList<PresenceProperty> propertyList = new LinkedList<PresenceProperty>();

	public void makeResource(Resource res) {

		for (PresenceProperty pp : propertyList) {
			pp.attachProperty(res);

		}

	}
}
