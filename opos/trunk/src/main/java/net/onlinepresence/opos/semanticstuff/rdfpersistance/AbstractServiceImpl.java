package net.onlinepresence.opos.semanticstuff.rdfpersistance;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import net.onlinepresence.ontmodel.general.Resource;
import net.onlinepresence.opos.semanticstuff.DataModelManager;
import net.onlinepresence.opos.semanticstuff.urigenerator.URIBuilder;

import org.apache.log4j.Logger;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.rdf.model.Model;

public abstract class AbstractServiceImpl implements AbstractService {

	/**
	 * The logger.
	 */
	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * @return the dataModel
	 */
	protected Model getDataModel() {
		return DataModelManager.getInstance().getDataModel();
	}

	/**
	 * @return the reader
	 */
	protected RDF2Bean getRdf2BeanBinding() {
		return DataModelManager.getInstance().getRdf2BeanBinding();
	}

	/**
	 * @return the writer
	 */
	protected Bean2RDF getBean2RDFBinding() {
		return DataModelManager.getInstance().getBean2RDFBinding();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#saveResource(org.intelleo
	 * .domainmodel.general.ResourceCls, boolean)
	 */
	public <T extends Resource> T saveResource(T t, boolean deep)
			throws Exception {
		try {
			// set URI
			if (null == t.getUri()) {
				t.setUri(URIBuilder.getInstance().uriGenerator
						.generateInstanceURI(t));
			}

			// save to model
			if (deep) {

				getBean2RDFBinding().saveDeep(t);
			} else {

				getBean2RDFBinding().save(t);
			}

			logger.debug("Saved Resource " + t + " (" + t.getUri() + ")");

			return t;

		} catch (Exception e) {
			logger.error("Saving Resource " + t + " failed:", e);
			throw new Exception("Saving Resource " + t + " failed!", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#updateResource(org.intelleo
	 * .domainmodel.general.ResourceCls, boolean)
	 */
	public <T extends Resource> T updateResource(T t, boolean deep)
			throws Exception {
		return saveResource(t, deep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#deleteResourceByURI(
	 * java.lang.Class, java.lang.String, boolean)
	 */
	public <T extends Resource> void deleteResourceByURI(Class<T> clazz,
			String resourceURI, boolean deep) throws Exception {
		T t = loadResourceByURI(clazz, resourceURI, deep);
		deleteResource(t, deep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#deleteResourceByURIJSON
	 * (java.lang.String, java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Resource> void deleteResourceByURIJSON(String className,
			String resourceURI, boolean deep) throws Exception {
		Class<T> clazz = (Class<T>) Class.forName(className);
		deleteResourceByURI(clazz, resourceURI, deep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#deleteResource(org.intelleo
	 * .domainmodel.general.ResourceCls, boolean)
	 */
	public <T extends Resource> void deleteResource(T t, boolean deep)
			throws Exception {
		try {
			// delete from model
			if (deep) {
				// TODO: figure out how to handle this...
				// or should a resource only be deleted after all connected
				// resources have been deleted or at least all connections
				// have been removed from this resource?
				getBean2RDFBinding().delete(t);
			} else {
				getBean2RDFBinding().delete(t);
			}

			logger.debug("Deleted Resource " + t);

		} catch (Exception e) {
			logger.error("Deleting Resource " + t + " failed:", e);
			throw new Exception("Deleting Resource " + t + " failed!", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#loadAllResources(java
	 * .lang.Class, boolean)
	 */
	public <T extends Resource> Collection<T> loadAllResources(Class<T> clazz,
			boolean deep) throws Exception {
		Collection<T> resources = new LinkedList<T>();

		try {
			if (deep) {
				resources = getRdf2BeanBinding().loadDeep(clazz);
			} else {
				resources = getRdf2BeanBinding().load(clazz);
			}

		} catch (Exception e) {
			logger.error(
					"Loading all resources of class " + clazz + " failed:", e);
			throw new Exception("Loading all resources of class " + clazz
					+ " failed!", e);
		}

		return resources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#loadAllResourcesJSON
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Resource> Collection<T> loadAllResourcesJSON(
			String className, boolean deep) throws Exception {
		Class<T> clazz = (Class<T>) Class.forName(className);
		return loadAllResources(clazz, deep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#loadResourcesByURIs(
	 * java.lang.Class, java.util.Collection, boolean)
	 */
	public <T extends Resource> Collection<T> loadResourcesByURIs(
			Class<T> clazz, Collection<String> resourceURIs, boolean deep)
			throws Exception {
		Collection<T> resources = new LinkedList<T>();

		for (String uri : resourceURIs) {
			resources.add(loadResourceByURI(clazz, uri, deep));
		}

		return resources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#loadResourcesByURIsJSON
	 * (java.lang.String, java.lang.String[], boolean)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Resource> Collection<T> loadResourcesByURIsJSON(
			String className, String[] resourceURIs, boolean deep)
			throws Exception {
		Collection<String> resourceURICollection = Arrays.asList(resourceURIs);
		Class<T> clazz = (Class<T>) Class.forName(className);
		return loadResourcesByURIs(clazz, resourceURICollection, deep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#loadResourceByURI(java
	 * .lang.Class, java.lang.String, boolean)
	 */
	public <T extends Resource> T loadResourceByURI(Class<T> clazz,
			String resourceURI, boolean deep) throws Exception {
		T t = null;

		try {
			if (deep) {
				t = (T) getRdf2BeanBinding().loadDeep(clazz, resourceURI);
			} else {
				t = (T) getRdf2BeanBinding().load(clazz, resourceURI);
			}

		} catch (Exception e) {
			logger.error("Loading Resource from URI " + resourceURI
					+ " failed:", e);
			throw new Exception("Loading Resource from URI " + resourceURI
					+ " failed!", e);
		}

		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.intelleo.services.ontologies.AbstractService#loadResourceByURIJSON
	 * (java.lang.String, java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public <T extends Resource> T loadResourceByURIJSON(String className,
			String resourceURI, boolean deep) throws Exception {
		Class<T> clazz = (Class<T>) Class.forName(className);
		return loadResourceByURI(clazz, resourceURI, deep);
	}

}
