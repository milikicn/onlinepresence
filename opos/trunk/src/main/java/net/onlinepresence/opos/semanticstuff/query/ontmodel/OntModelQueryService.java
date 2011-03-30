package net.onlinepresence.opos.semanticstuff.query.ontmodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import net.onlinepresence.opos.semanticstuff.query.results.ResultsCollection;

import com.hp.hpl.jena.rdf.model.Model;

public interface OntModelQueryService {

	/**
	 * retrieves all instances of the given class (POJO) from the given ontology
	 * model providing access to (RDF) instance data
	 * 
	 * @param <T>
	 * @param instanceType
	 * @param model
	 * @param loadDeep
	 *            If loadDeep is set to false, the method loads all functional
	 *            properties but ignores bean properties of type Collection;
	 *            this is a safe option for dense graphs; when loadDeep is set
	 *            to true, the method will load a particular individual and all
	 *            it's properties, recursively; this could result in loading the
	 *            entire model into memory as java objects depending on the
	 *            density of the graph.
	 * @return collection of instances of the given class
	 */
	public <T> Collection<T> retrieveAllInstances(Class<T> instanceType,
			Model model, boolean loadDeep);

	/**
	 * retrieves instance of the given class (instanceType), with the given URI
	 * (instanceUri), from the given ontology model providing access to (RDF)
	 * instance data
	 * 
	 * @param <T>
	 * @param instanceType
	 * @param instanceUri
	 * @param model
	 * @param loadDeep
	 *            The same as for the previous method
	 * @return
	 */
	public <T> T retrieveInstance(Class<T> instanceType, String instanceUri,
			Model model, boolean loadDeep);

	/**
	 * executes SPARQL queries that have just one variable in the SELECT clause
	 * 
	 * @param query
	 *            the SELECT SPARQL query to be executed
	 * @param variable
	 *            the name of the variable in the SELECT clause
	 * @param model
	 *            the data model providing access to (RDF) instance data
	 * @return collection of the retrieved values for the given variable
	 */
	public Collection<String> executeOneVariableSelectSparqlQuery(String query,
			String variable, Model model);

	/**
	 * 
	 * @param query
	 *            the SELECT SPARQL query to be executed
	 * @param model
	 *            the ontology model providing access to (RDF) instance data
	 * @return SPARQL query result as an instance of the ResultsCollection
	 */
	public ResultsCollection executeSelectSparqlQuery(String query, Model model);

	/**
	 * 
	 * @param queryStream
	 *            input stream to the SELECT SPARQL query to be executed
	 * @param model
	 *            the ontology model providing access to (RDF) instance data
	 * @return SPARQL query result as an instance of the ResultsCollection
	 * @throws IOException
	 */
	public ResultsCollection executeSelectSparqlQueryStream(InputStream queryStream,
			Model model) throws IOException;

	/**
	 * 
	 * @param queryStream
	 *            input stream to the CONSTRUCT SPARQL query to be executed
	 * @param model
	 *            the ontology model providing access to (RDF) instance data
	 * @return the resulting RDF graph as an instance of the Jena's OntModel
	 *         interface
	 * @throws IOException
	 */
	public Model executeConstructSparqlQuery(InputStream queryStream,
			Model model) throws IOException;

}
