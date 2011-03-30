package net.onlinepresence.opos.semanticstuff.query.results;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;

public class XMLSPARQLResultsHandler {

	@SuppressWarnings("unchecked")
	public static ResultsCollection processSparqlResult(Document doc) {

		ResultsCollection resultsList = new ResultsList();

		Element root = doc.getRootElement();

		Element resultsElement = root.element("results");

		if (resultsElement.elements() == null) {
			System.out.println("Empty result set!");
			return resultsList;
		}

		Iterator<Element> resIter = resultsElement.elementIterator("result");
		while (resIter.hasNext()) {
			Element resElement = (Element) resIter.next();
			QueryResult result = new QueryResult();

			Iterator<Element> bindingIter = resElement
					.elementIterator("binding");
			while (bindingIter.hasNext()) {
				Element bindingElement = (Element) bindingIter.next();
				String variable = bindingElement.attributeValue("name");
				String value = "";
				if (bindingElement.element("uri") != null)
					value = bindingElement.elementText("uri");
				else if (bindingElement.element("literal") != null)
					value = bindingElement.elementText("literal");
				result.addVariable(variable, value);
			}

			resultsList.addQueryResult(result);
		}

		return resultsList;

	}
}
