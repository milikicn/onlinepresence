/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

import presence.OntologyConcept;

/**
 * @author Nikola Milikic
 * 
 */
public abstract class OnlinePresenceComponent extends OntologyConcept {
	public abstract OnlinePresenceComponent getInstance(String name);
}
