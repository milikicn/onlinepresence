/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: 24.10.2009.
* @version: 0.1
*/
package presenceProperties.specificProperties;

import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import presenceProperties.StringProperty;

/**
 * @author Nikola Milikic
 *
 */
public class StatusMessageProperty extends StringProperty{
	
	/**
	 * @param name
	 * @param value
	 */
	public StatusMessageProperty(String name, String value) {
		super(name, value);
	}
	
	@Override
	public void attachAsProperty(Resource res) {
		setNameSpace("http://rdfs.org/sioc/ns#");
		res.addProperty(ModelFactory.createDefaultModel().createProperty(
				getClassURI().toString()), value);
	}
}
