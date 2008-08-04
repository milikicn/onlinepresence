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

	private URI uri;

	/**
	 * @return the uri
	 */
	public URI getClassURI() {
		try {

			return new URI("http://ggg.milanstankovic.org/opo/ns#"
					+ getOPOName());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String getOPOName() {
		String className = this.getClass().getName();
		return className.substring(className.lastIndexOf(".") + 1);
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setURI(URI uri) {
		this.uri = uri;
	}

	public URI getURI() {
		return this.uri;
	}
}
