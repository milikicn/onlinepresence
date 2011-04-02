package net.onlinepresence.opos.semanticstuff.rdfpersistance.query.sparqlendpoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results.ResultsCollection;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results.ResultsList;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results.XMLSPARQLResultsHandler;
import net.onlinepresence.opos.util.StringUtils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.hp.hpl.jena.ontology.OntModel;

public class SimpleSPARQLEndpointQuery implements SPARQLEnpointQueryService {

	public ResultsCollection executeSelectSparqlQuery(String query,
			String endpoint) {
		StringBuilder sb = new StringBuilder();
		sb.append(endpoint).append("?query=").append(StringUtils.encode(query));

		String request = sb.toString();
		// System.out.println("query:\n" + query + "\n\n");
		// System.out.println( request );

		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(request);

			return XMLSPARQLResultsHandler.processSparqlResult(doc);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultsList();
		}
	}

	public ResultsCollection executeSelectSparqlQuery(InputStream queryStream,
			String endpoint) throws IOException {
		String query = StringUtils.readString(queryStream);
		return executeSelectSparqlQuery(query, endpoint);
	}

	public <T> Collection<T> retrieveAllInstances(Class<T> instanceType,
			String endpoint) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T retrieveInstance(Class<T> instanceType, String instanceUri,
			String endpoint) {
		// TODO Auto-generated method stub
		return null;
	}

	public OntModel executeConstructSparqlQuery(InputStream queryStream,
			String endpoint) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public OntModel executeConstructSparqlQuery(String query, String endpoint) {
		// TODO Auto-generated method stub
		return null;
	}

	public OntModel executeDescribeSparqlQuery(InputStream queryStream,
			String endpoint) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public OntModel executeDescribeSparqlQuery(String query, String endpoint) {
		// TODO Auto-generated method stub
		return null;
	}

}
