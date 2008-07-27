package presence;

import java.net.URI;

public abstract class OntologyConcept {
	
	public URI uri;

	/**
	 * @return the uri
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(URI uri) {
		this.uri = uri;
	}
}
