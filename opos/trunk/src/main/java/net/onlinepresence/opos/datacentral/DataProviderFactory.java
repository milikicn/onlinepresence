package net.onlinepresence.opos.datacentral;

import org.apache.log4j.Logger;

public class DataProviderFactory {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(DataProviderFactory.class
			.getName());

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static DataProvider getDataProvider() {
		return new RepositoryDataProvider();
	}

}
