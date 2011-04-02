package net.onlinepresence.opos.semanticstuff.rdfpersistance.query.results;

import java.util.Collection;
import java.util.LinkedList;

public class ResultsList implements ResultsCollection {

	/**
	 * collection of all the results of a SELECT SPARQL query
	 */
	private Collection<QueryResult> resultsCollection;

	public ResultsList() {
		resultsCollection = new LinkedList<QueryResult>();
	}

	public Collection<QueryResult> getResultsCollection() {
		return resultsCollection;
	}

	public void setResultsCollection(Collection<QueryResult> resultsCollection) {
		this.resultsCollection = resultsCollection;
	}

	public void addQueryResult(QueryResult result) {
		resultsCollection.add(result);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (QueryResult queryResult : resultsCollection) {
			sb.append(queryResult.toString()).append("\n");
			sb.append("\\--------------------------------------/\n");
		}

		return sb.toString();
	}

}
