/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 26, 2008
 * @version: 0.1
 */
package presenceProperties;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Nikola Milikic
 * 
 */
public class Avatar extends PresenceProperty {

	private URI imageURI;

	/**
	 * @param imageURI
	 */
	public Avatar(URI imageURI) {
		super("avatar");
		this.imageURI = imageURI;
	}

	/**
	 * @return the imageURI
	 */
	public URI getImageURI() {
		return imageURI;
	}

	/**
	 * @param imageURI
	 *            the imageURI to set
	 */
	public void setImageURI(URI imageURI) {
		this.imageURI = imageURI;
	}
	
	public String getContent(){
		return getImageURI().toString();
	}
	
	/**
	 * 
	 */
	public URI getClassURI() {
		try {
			return new URI("http://xmlns.com/foaf/0.1/Image");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
