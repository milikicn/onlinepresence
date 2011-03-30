package net.onlinepresence.opos.semanticstuff.query.results;

import java.util.HashMap;
import java.util.Map;

public class QueryResult {

	/**
	 * The map keeps one result of a SELECT SPARQL query; each map's entry
	 * represents one variable-value pair: - key keeps the name of the variable;
	 * - value stores the actual value of the variable
	 */
	private Map<String, String> queryResult;

	public QueryResult() {
		queryResult = new HashMap<String, String>();
	}

	public Map<String, String> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(Map<String, String> queryResult) {
		this.queryResult = queryResult;
	}

	public void addVariableValuePair(String var, String value) {
		getQueryResult().put(var, value);
	}

	/**
	 * Returns the value for the given variable
	 * 
	 * @param variable
	 *            the name of a variable from the SPARQL SELECT query
	 * @return the value of the variable; null if there is no variable with the
	 *         given name
	 */
	public String getVariableValue(String variable) {
		if (queryResult.containsKey(variable)) {
			return queryResult.get(variable);
		} else {
			return null;
		}
	}

	public void addVariable(String variable) {
		if (variable != null && !queryResult.containsKey(variable))
			queryResult.put(variable, null);
	}

	public void addVariable(String variable, String value) {
		if (variable != null && value != null)
			queryResult.put(variable, value);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (String variable : queryResult.keySet()) {
			sb.append(variable).append(": ");
			sb.append(queryResult.get(variable)).append("\n");
		}

		return sb.toString();
	}

}
