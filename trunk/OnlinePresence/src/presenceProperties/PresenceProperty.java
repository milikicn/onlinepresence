/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceProperties;

import presence.OntologyConcept;

import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author Nikola Milikic
 * 
 */
@SuppressWarnings("unchecked")
public abstract class PresenceProperty<T> extends OntologyConcept {
	protected String name;
	protected T value;

	/**
	 * @param name
	 */
	public PresenceProperty(String name, T value) {
		this.name = name;
		this.value = value;
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

	@Override
	public boolean equals(Object o) {
		if (o instanceof PresenceProperty) {
			PresenceProperty p = (PresenceProperty) o;
			if (this.getClass().equals(p.getClass())) {
				return true;
			}
			return false;
		}
		return false;
	}

	// public abstract E getContent();

	/**
	 * A getter method for value
	 * 
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	public abstract void attachAsProperty(Resource res);

	/*
	 * (non-Javadoc)
	 * 
	 * @see presence.OntologyConcept#getOPOName(java.lang.String)
	 */
	@Override
	protected String getOPOName() {
		return name;
	}

	/**
	 * A setter method for value
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
}
