package net.onlinepresence.services;

import java.util.Collection;

import net.onlinepresence.ontmodel.general.Resource;

public interface OPOManager {

	<T extends Resource> void saveResource(T t, boolean deep) throws Exception;

	<T extends Resource> void updateResource(T t, boolean deep)
			throws Exception;

	<T extends Resource> void deleteResource(Class<T> clazz,
			String resourceURI, boolean deep) throws Exception;

	<T extends Resource> void deleteResource(T t) throws Exception;

	<T extends Resource> Collection<T> loadAllResources(Class<T> clazz,
			boolean loadDeep) throws Exception;

	<T extends Resource> Collection<T> loadResources(Class<T> clazz,
			Collection<String> resourceURIs, boolean loadDeep) throws Exception;

	<T extends Resource> T loadResource(Class<T> clazz, String resourceURI,
			boolean loadDeep) throws Exception;

}
