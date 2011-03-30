/**
 * 
 */
package net.onlinepresence.opos.datacentral;

import org.apache.log4j.Logger;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.rdf.model.Model;

public class DataModelManager {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private DataProvider dataProvider;

	private Model dataModel;

	private RDF2Bean rdf2BeanBinding;

	private Bean2RDF bean2RDFBinding;

	// private Integer cacheCounter = CleanUp.MAX_COUNTER;

	private boolean firstRun = true;

	// protected static ScheduledExecutorService executor = Executors
	// .newSingleThreadScheduledExecutor();

	private static class DataModelManagerHolder {
		private static final DataModelManager INSTANCE = new DataModelManager();
	}

	public static DataModelManager getInstance() {
		return DataModelManagerHolder.INSTANCE;
	}

	protected DataModelManager() {
		// new CleanUp().run();
	}

	/**
	 * @return the dataProvider
	 */
	public DataProvider getDataProvider() {
		if (null == dataProvider) {
			dataProvider = DataProviderFactory.getDataProvider();
		}
		return dataProvider;
	}

	/**
	 * @return the dataModel
	 */
	public Model getDataModel() {
		// synchronized (executor) {
		// cacheCounter = CleanUp.MAX_COUNTER;
		if (null == dataModel) {
			logger.debug("Retrieving data model...");
			dataModel = getDataProvider().getDataModel(!firstRun);
			firstRun = false;
		}
		// }
		return dataModel;
	}

	/**
	 * 
	 * @return the rdf2BeanBinding
	 */
	public RDF2Bean getRdf2BeanBinding() {
		if (null == rdf2BeanBinding) {
			rdf2BeanBinding = new RDF2Bean(getDataModel());
			rdf2BeanBinding.bindAll("org.intelleo.domainmodel");
		}
		return rdf2BeanBinding;
	}

	/**
	 * 
	 * @return the bean2RDFBinding
	 */
	public Bean2RDF getBean2RDFBinding() {
		if (null == bean2RDFBinding) {
			bean2RDFBinding = new Bean2RDF(getDataModel());
		}
		return bean2RDFBinding;
	}

	/**
	 * 
	 */
	public void flush() {
		getDataProvider().flushDataModel(dataModel);
	}

	// protected class CleanUp implements Runnable
	// {
	// /** Timeout in seconds for each CleanUp cycle. */
	// public int timeout = 60;
	//
	// /** Amount of cleanup cycles until pages will be removed. */
	// public static final int MAX_COUNTER = 2;
	//
	// public CleanUp()
	// {
	//
	// }
	//
	// public CleanUp(int timeout)
	// {
	// this.timeout = timeout;
	// }
	//
	// @Override
	// public void run()
	// {
	// cacheCounter--;
	// if (cacheCounter <= 0) {
	// synchronized (executor) {
	// getDataProvider().flushDataModel(dataModel);
	// dataModel = null;
	// bean2RDFBinding = null;
	// rdf2BeanBinding = null;
	// }
	// }
	// try {
	// executor.schedule(this, this.timeout, TimeUnit.SECONDS);
	// } catch (RejectedExecutionException e) {
	// // appears on connection close so ignore
	// }
	// }
	// }

}
