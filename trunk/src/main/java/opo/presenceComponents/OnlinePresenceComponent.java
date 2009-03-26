/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package opo.presenceComponents;

import opo.presence.OntologyConcept;

/**
 * @author Nikola Milikic
 * 
 */
public abstract class OnlinePresenceComponent extends OntologyConcept {
	public abstract OnlinePresenceComponent getStaticInstance(String name);
}
