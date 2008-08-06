/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 6, 2008
* @version: 0.1
*/
package agent;

import presenceProperties.StringProperty;

/**
 * @author Nikola Milikic
 *
 */
public class AgentStringProperty extends StringProperty {

	/**
	 * @param name
	 * @param value
	 */
	public AgentStringProperty(String name, String value) {
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
