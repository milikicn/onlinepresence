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
	
	public URI uri;

	/**
	 * @return the uri
	 */
	public URI getClassURI() {
		try {
			return new URI("http://ggg.milanstankovic.org/opo/ns#" + this.getClass().getName());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param uri the uri to set
	 */
	public void setURI(URI uri) {
		this.uri = uri;
	}
	
	
	public URI getClassURI(){
		URI uri = null;
		String s = "http://ggg.milanstankovic.org/opo/ns#" + this.getClass().getName();
		try {
			uri = new URI(s);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
		}
}
