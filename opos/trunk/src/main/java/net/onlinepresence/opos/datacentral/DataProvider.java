package net.onlinepresence.opos.datacentral;

import com.hp.hpl.jena.rdf.model.Model;

public interface DataProvider {

	/**
	 * Retrieves data (i.e. RDF triples) from the (default) data source
	 * 
	 * @return the retrieved (RDF) data as an instance of Jena Model
	 */
	public Model getDataModel(boolean reconnect);

	/**
	 * Flushes changes in the data model to the repository
	 * 
	 * @param dataModel
	 *            the data model to flush
	 */
	public void flushDataModel(Model dataModel);
}
