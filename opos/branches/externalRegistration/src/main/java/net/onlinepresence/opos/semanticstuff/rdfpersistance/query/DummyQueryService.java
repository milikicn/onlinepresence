package net.onlinepresence.opos.semanticstuff.rdfpersistance.query;

import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryService;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryServiceImpl;
import net.onlinepresence.opos.semanticstuff.services.AbstractServiceImpl;
import net.onlinepresence.opos.semanticstuff.util.Constants;

public class DummyQueryService extends AbstractServiceImpl {
	
	private static DummyQueryService INSTANCE;
	
	private DummyQueryService() {}
	
	public static DummyQueryService getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DummyQueryService();
		
		return INSTANCE;
	}
	
	private OntModelQueryService queryService = new OntModelQueryServiceImpl();
	
	public void performDummyQuery() throws Exception {
		
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX foaf: <"+Constants.FOAF_NS+"> \n" + 
			"SELECT ?user \n" +
			"WHERE { \n" +
				"?user rdf:type foaf:Person . \n" +
			"} \n" +
			"LIMIT 1";
		
		 queryService.executeOneVariableSelectSparqlQuery(queryString, "user", getDataModel());

	}

}
