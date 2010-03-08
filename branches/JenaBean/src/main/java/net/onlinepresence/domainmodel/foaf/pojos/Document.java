package net.onlinepresence.domainmodel.foaf.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Document")
public class Document extends Thing implements DocumentBean {

	public Document() {
		super();
	}
	
	public Document(String uri) {
		super(uri);
	}
	
}
