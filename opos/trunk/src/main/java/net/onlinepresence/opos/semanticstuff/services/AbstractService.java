package net.onlinepresence.opos.semanticstuff.services;

import java.util.Collection;

import net.onlinepresence.jopo.ontmodel.general.Resource;

public interface AbstractService {
	/**
	 * Saves the given resource to the repository. deep indicates whether all
	 * referenced beans should be saved too.
	 * 
	 * @param <T>
	 * @param t
	 *            resource to be saved
	 * @param deep
	 *            indicates whether all referenced beans should be saved
	 * @return the resource which has been saved
	 * @throws Exception
	 */
	<T extends Resource> T saveResource(T t, boolean deep) throws Exception;

	/**
	 * Updates the resource in the repository with the version of that resource
	 * given. deep indicates whether all referenced beans should be updated too.
	 * 
	 * @param <T>
	 * @param t
	 *            resource to be updated
	 * @param deep
	 *            indicates whether all referenced beans should be updated
	 * @return the resource which as been updated
	 * @throws Exception
	 */
	<T extends Resource> T updateResource(T t, boolean deep) throws Exception;

	/**
	 * Removes the Resource identified by parameter <code>clazz</code> and
	 * parameter <code>resourceURI</code> from the repository. <code>deep</code>
	 * indicates whether all referenced resources should also be removed.
	 * 
	 * @param <T>
	 * @param clazz
	 *            class of resource to be removed
	 * @param resourceURI
	 *            URI of resource to be removed
	 * @param deep
	 *            indicates whether all referenced beans should be removed
	 * @throws Exception
	 */
	<T extends Resource> void deleteResourceByURI(Class<T> clazz,
			String resourceURI, boolean deep) throws Exception;

	/**
	 * 
	 * @param <T>
	 * @param className
	 * @param deep
	 * @throws Exception
	 */
	<T extends Resource> void deleteResourceByURIJSON(String className,
			String resourceURI, boolean deep) throws Exception;

	/**
	 * Removes the given Resource from the repository. <code>deep</code>
	 * indicates whether all referenced resources should also be removed.
	 * 
	 * @param <T>
	 * @param t
	 *            resource to be removed
	 * @param deep
	 *            indicates whether all referenced beans should be removed
	 * @throws Exception
	 */
	<T extends Resource> void deleteResource(T t, boolean deep)
			throws Exception;

	/**
	 * Loads all resources which are of the given class. <code>deep</code>
	 * indicates whether all referenced beans should be loaded too.
	 * 
	 * @param <T>
	 * @param clazz
	 *            class of resources to be loaded
	 * @param deep
	 *            indicates whether all referenced beans should be loaded
	 * @return collection of all resources which are of the given class
	 * @throws Exception
	 */
	<T extends Resource> Collection<T> loadAllResources(Class<T> clazz,
			boolean deep) throws Exception;

	/**
	 * 
	 * @param <T>
	 * @param className
	 * @param deep
	 * @return
	 * @throws Exception
	 */
	<T extends Resource> Collection<T> loadAllResourcesJSON(String className,
			boolean deep) throws Exception;

	/**
	 * Loads resources with specific URIs of specified class. <code>deep</code>
	 * indicates whether all referenced beans should be loaded too.
	 * 
	 * @param <T>
	 * @param clazz
	 *            class of resources to be loaded
	 * @param resourceURIs
	 *            URIs of resources to be loaded
	 * @param deep
	 *            indicates whether all referenced beans should be loaded
	 * @return collection of all resources which are of the given class and with
	 *         specified URIs
	 * @throws Exception
	 */
	<T extends Resource> Collection<T> loadResourcesByURIs(Class<T> clazz,
			Collection<String> resourceURIs, boolean deep) throws Exception;

	/**
	 * 
	 * @param <T>
	 * @param className
	 * @param resourceURIs
	 * @param deep
	 * @return
	 * @throws Exception
	 */
	<T extends Resource> Collection<T> loadResourcesByURIsJSON(
			String className, String[] resourceURIs, boolean deep)
			throws Exception;

	/**
	 * Loads specific resource with specified URI and class. <code>deep</code>
	 * indicates whether all referenced beans should be loaded too.
	 * 
	 * @param <T>
	 * @param clazz
	 *            class of resource to be loaded
	 * @param resourceURI
	 *            URI of the resource to be loaded
	 * @param deep
	 *            indicates whether all referenced beans should be loaded
	 * @return resource with specified URI and class
	 * @throws Exception
	 */
	<T extends Resource> T loadResourceByURI(Class<T> clazz,
			String resourceURI, boolean deep) throws Exception;

	/**
	 * 
	 * @param <T>
	 * @param className
	 * @param resourceURI
	 * @param deep
	 * @return
	 * @throws Exception
	 */
	<T extends Resource> T loadResourceByURIJSON(String className,
			String resourceURI, boolean deep) throws Exception;
}
