package net.onlinepresence.opos.semanticstuff.rdfpersistance.urigenerator;

import net.onlinepresence.jopo.ontmodel.general.Resource;
import net.onlinepresence.opos.config.Settings;

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
				Settings.getInstance().config.rdfRepositoryConfig.namespace);
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
