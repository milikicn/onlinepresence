package net.onlinepresence.util.urigenerator;

public abstract class UriGenerator {
	
	/**
	 * generates unique URI for the given instance
	 * @param instance 
	 * @param namespace 
	 * @return the generated URI as string value  
	 */
	public abstract String generateInstanceUri(Object instance, String namespace);

}
