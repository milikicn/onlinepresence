package net.onlinepresence.opos.semanticstuff.query.sparqlendpoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import net.onlinepresence.opos.semanticstuff.query.results.ResultsCollection;

import com.hp.hpl.jena.ontology.OntModel;

public interface SPARQLEnpointQueryService {

	/**
	 * retrieves all instances of the given class (POJO) from the given SPARQL
	 * endpoint
	 * 
	 * @param <T>
	 * @param instanceType
	 * @param endpoint
	 * @return collection of instances of the given class
	 */
	public <T> Collection<T> retrieveAllInstances(Class<T> instanceType,
			String endpoint);

	/**
	 * retrieves instance of the given class (instanceType), with the given URI
	 * (instanceUri), from the given SPARQL endpoint (endpoint)
	 * 
	 * @param <T>
	 * @param instanceType
	 * @param instanceUri
	 * @param endpoint
	 * @return
	 */
	public <T> T retrieveInstance(Class<T> instanceType, String instanceUri,
			String endpoint);

	/**
	 * 
	 * @param query
	 *            the SELECT SPARQL query to be executed
	 * @param endpoint
	 *            the URL of the SPARQL Endpoint
	 * @return SPARQL query result as an instance of the ResultsCollection
	 */
	public ResultsCollection executeSelectSparqlQuery(String query,
			String endpoint);

	/**
	 * 
	 * @param queryStream
	 *            input stream to the SELECT SPARQL query to be executed
	 * @param endpoint
	 *            the URL of the SPARQL Endpoint
	 * @return SPARQL query result as an instance of the ResultsCollection
	 * @throws IOException
	 */
	public ResultsCollection executeSelectSparqlQuery(InputStream queryStream,
			String endpoint) throws IOException;

	/**
	 * 
	 * @param queryStream
	 *            input stream to the CONSTRUCT SPARQL query to be executed
	 * @param endpoint
	 *            the URL of the SPARQL Endpoint
	 * @return the resulting RDF graph as an instance of the Jena's OntModel
	 *         interface
	 * @throws IOException
	 */
	public OntModel executeConstructSparqlQuery(InputStream queryStream,
			String endpoint) throws IOException;

	public OntModel executeConstructSparqlQuery(String query, String endpoint);

	/**
	 * 
	 * @param queryStream
	 *            input stream to the DESCRIBE SPARQL query to be executed
	 * @param endpoint
	 *            the URL of the SPARQL Endpoint
	 * @return the resulting RDF graph as an instance of the Jena's OntModel
	 *         interface
	 * @throws IOException
	 */
	public OntModel executeDescribeSparqlQuery(InputStream queryStream,
			String endpoint) throws IOException;

	public OntModel executeDescribeSparqlQuery(String query, String endpoint);

}
