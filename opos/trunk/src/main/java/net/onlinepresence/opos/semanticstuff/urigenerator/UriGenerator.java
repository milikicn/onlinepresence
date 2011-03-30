package net.onlinepresence.opos.semanticstuff.urigenerator;

import net.onlinepresence.ontmodel.general.Resource;

public abstract class UriGenerator {

	/**
	 * Generates unique URI for the given instance based on default namespace.
	 * 
	 * @param <E>
	 * @param instance
	 *            the Reource instance for which to generate the URI
	 * @return the generated URI
	 */
	public <E extends Resource> String generateInstanceURI(E instance) {
		return this.generateInstanceURI(instance,
				"http://online-presence.org/opos/triplestore#");
	}

	/**
	 * Generates unique URI for the given instance based on given namespace.
	 * 
	 * @param <E>
	 * @param instance
	 *            the Reource instance for which to generate the URI
	 * @param namespace
	 *            the base namespace for the URI
	 * @return the generated URI
	 */
	public abstract <E extends Resource> String generateInstanceURI(E instance,
			String namespace);

}
