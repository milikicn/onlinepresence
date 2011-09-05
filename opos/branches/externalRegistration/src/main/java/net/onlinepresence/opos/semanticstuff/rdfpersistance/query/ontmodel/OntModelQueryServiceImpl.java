package net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results.QueryResult;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results.ResultsCollection;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results.ResultsList;
import net.onlinepresence.opos.util.StringUtils;

import org.apache.log4j.Logger;

import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class OntModelQueryServiceImpl implements OntModelQueryService {

	/**
	 * The logger.
	 */
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public OntModel executeConstructSparqlQuery(InputStream queryStream,
			Model model) throws IOException {

		String queryString = StringUtils.readString(queryStream);
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		Model resultModel = qe.execConstruct();

		// Important - free up resources used running the query
		qe.close();

		return (OntModel) resultModel;
	}

	public Collection<String> executeOneVariableSelectSparqlQuery(String query,
			String variable, Model model) {

		List<String> results = new LinkedList<String>();

		Query q = QueryFactory.create(query, Syntax.syntaxARQ);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(q, model);
		ResultSet resultSet = qe.execSelect();

		while (resultSet.hasNext()) {
			QuerySolution solution = resultSet.nextSolution();
			RDFNode value = solution.get(variable);
			
			if (value.isLiteral())
				results.add(((Literal) value).getLexicalForm());
			else
				results.add(((Resource) value).getURI());
		}

		qe.close();

		return results;
	}

	public ResultsCollection executeSelectSparqlQuery(String query, Model model) {

		ResultsCollection resCollection = new ResultsList();

		Query q = QueryFactory.create(query);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(q, model);
		ResultSet results = qe.execSelect();

		for (; results.hasNext();) {
			QuerySolution solution = results.nextSolution();
			Iterator<String> variables = solution.varNames();
			QueryResult queryResult = new QueryResult();
			
			while (variables.hasNext()) {
				String var = (String) variables.next();
				RDFNode valueNode = solution.get(var);
				if (valueNode != null)
					queryResult.addVariableValuePair(var, valueNode.toString());
			}
			resCollection.addQueryResult(queryResult);
		}
		qe.close();

		return resCollection;
	}

	public ResultsCollection executeSelectSparqlQueryStream(
			InputStream queryStream, Model model) throws IOException {

		String queryString = StringUtils.readString(queryStream);
		return executeSelectSparqlQuery(queryString, model);
	}

	public <T> Collection<T> retrieveAllInstances(Class<T> instanceType,
			Model model, boolean loadDeep) {

		RDF2Bean binding = new RDF2Bean(model);
		
		if (loadDeep)
			return binding.loadDeep(instanceType);
		else
			return binding.load(instanceType);
	}

	public <T> T retrieveInstance(Class<T> instanceType, String instanceUri,
			Model model, boolean loadDeep) {

		RDF2Bean binding = new RDF2Bean(model);

		if (loadDeep)
			return binding.loadDeep(instanceType, instanceUri);
		else
			return binding.load(instanceType, instanceUri);
	}

}
