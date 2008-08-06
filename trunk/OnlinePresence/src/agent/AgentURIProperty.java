/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 6, 2008
* @version: 0.1
*/
package agent;

import java.net.URI;

import presenceProperties.URIProperty;

/**
 * @author Nikola Milikic
 *
 */
public class AgentURIProperty extends URIProperty {

	/**
	 * @param name
	 * @param value
	 */
	public AgentURIProperty(String name, URI value) {
		super(name, value);
	}

	/* (non-Javadoc)
	 * @see presence.OntologyConcept#getNameSpace()
	 */
	@Override
	public String getNameSpace() {
		return Agent.AGENTNS;
	}
}
