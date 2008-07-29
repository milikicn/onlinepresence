/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceProperties;

import presence.OntologyConcept;

/**
 * @author Nikola Milikic
 * 
 */
public abstract class PresenceProperty extends OntologyConcept{
	private String name;
	
	/**
	 * @param name
	 */
	public PresenceProperty(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if (o instanceof PresenceProperty) {
			PresenceProperty p = (PresenceProperty) o;
			if (this.getClass().equals(p.getClass()))
				return true;
			return false;
		}
		return false;
	}
	
	public abstract String getContent();
}
