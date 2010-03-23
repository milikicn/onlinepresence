package net.onlinepresence.util.urigenerator;

/**
 * This generator is based on a very simple idea:
 * an "unique id" is appended to the given namespace to form the unique URI;
 * the "unique id" is the system time (at the moment the method is executed) 
 * expressed in milliseconds (System.currentTimeMillis()) 
 * @author admin
 *
 */
public class TimeBasedUriGenerator extends UriGenerator {
	
	@Override
	public String generateInstanceUri(Object instance, String namespace) {
		long currentTime = System.currentTimeMillis();
		return namespace +
				instance.getClass().getSimpleName() +
				"/" +
				String.valueOf(currentTime);
	}

}
