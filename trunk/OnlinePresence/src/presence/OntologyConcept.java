/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: Jul 26, 2008
 * @version: 0.1
 */
package presence;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Filip Radulovic
 * 
 */
public abstract class OntologyConcept {

	protected String nameSpace = "";
	private URI uri;

	/**
	 * 
	 * @return
	 */
	public URI getURI() {
		return this.uri;
	}
	
	/**
	 * @param stringURI
	 *            the uri to set
	 */
	public void setURI(String stringURI) {
		setURI(URI.create(stringURI));
	}
	
	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setURI(URI uri) {
		this.uri = uri;
	}
	
		/**
	 * @return the nameSpace
	 */
	public String getNameSpace() {
		return nameSpace;
	}

	/**
	 * @param nameSpace the nameSpace to set
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	/**
	 * @return the uri
	 */
	public URI getClassURI() {
		try {
			return new URI(getNameSpace()+ getOPOName());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getOPOName() {
		String className = this.getClass().getName();
		return className.substring(className.lastIndexOf(".") + 1);
	}
}
