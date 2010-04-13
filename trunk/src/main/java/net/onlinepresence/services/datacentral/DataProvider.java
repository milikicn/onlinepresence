package net.onlinepresence.services.datacentral;

import com.hp.hpl.jena.rdf.model.Model;

public interface DataProvider {
	
	/**
	 * Retrieves data (i.e., RDF triples) from the (default) data source
	 * @return the retrieved (RDF) data as an instance of Jena Model   
	 */
	public Model getData();
	
	/**
	 * Updates the data source
	 * @param m the (RDF) data (graph) to be stored in the data source 
	 */
	public void updateDataSource(Model m);

}
