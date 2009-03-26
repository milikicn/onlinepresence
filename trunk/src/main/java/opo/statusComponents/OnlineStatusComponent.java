/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package opo.statusComponents;

import opo.presence.OntologyConcept;

/**
 * The component of the OnlineStatus representing one of its dimensions.
 * 
 * @author Nikola Milikic
 */
public abstract class OnlineStatusComponent extends OntologyConcept {

	public abstract OnlineStatusComponent getStaticInstance(String name);		
}


