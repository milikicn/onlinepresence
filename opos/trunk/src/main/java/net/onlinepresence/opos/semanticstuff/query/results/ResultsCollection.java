package net.onlinepresence.opos.semanticstuff.query.results;

import java.util.Collection;

public interface ResultsCollection {

	public Collection<QueryResult> getResultsCollection();

	public void setResultsCollection(Collection<QueryResult> results);

	public void addQueryResult(QueryResult result);

}
