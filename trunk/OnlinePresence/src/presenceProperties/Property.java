/**
 * @autor: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceProperties;

/**
 * @author Nikola Milikic
 * 
 */
public abstract class Property {
	private String name;

	/**
	 * @param name
	 */
	public Property(String name) {
		super();
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
		if (o instanceof Property) {
			Property p = (Property) o;
			if (this.getClass().equals(p.getClass()))
				return true;
			return false;
		}
		return false;
	}
}
