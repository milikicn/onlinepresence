package net.onlinepresence.util.urigenerator;

import java.util.UUID;

/**
 * This generator is based on a immutable universally unique identifier (UUID). 
 * A UUID represents a 128-bit value. URI is created by appending randomly generated 
 * UUID to the given namespace. 
 *
 */
public class UUIDBasedUriGenerator extends UriGenerator {

	@Override
	public String generateInstanceUri(Object instance, String namespace) {
		return namespace +
				instance.getClass().getSimpleName() +
				"/" +
				UUID.randomUUID();
	}

}
