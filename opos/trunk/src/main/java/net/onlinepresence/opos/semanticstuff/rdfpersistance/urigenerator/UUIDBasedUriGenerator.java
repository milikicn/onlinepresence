package net.onlinepresence.opos.semanticstuff.rdfpersistance.urigenerator;

import java.util.UUID;

import net.onlinepresence.ontmodel.general.Resource;

/**
 * This generator is based on a immutable universally unique identifier (UUID).
 * A UUID represents a 128-bit value. URI is created by appending randomly
 * generated UUID to the given namespace.
 * 
 */
public class UUIDBasedUriGenerator extends UriGenerator {

	@Override
	public <E extends Resource> String generateInstanceURI(E instance,
			String namespace) {
		return namespace + instance.getClass().getSimpleName() + "/"
				+ UUID.randomUUID();
	}

}
