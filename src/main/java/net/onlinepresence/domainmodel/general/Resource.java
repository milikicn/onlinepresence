package net.onlinepresence.domainmodel.general;

import net.onlinepresence.util.urigenerator.URIBuilder;
import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import thewebsemantic.Uri;


@Namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@RdfType("Resource")
public class Resource
implements ResourceBean{

	protected String uri;
	
	public Resource() {
		uri = URIBuilder.instance().generateURI(this);
	}
	
	public Resource(String uri) {
		this.uri = uri;
	}
	
	@Uri
	public String getUri() {
		return uri;
	}
	
	public boolean equals(Object o) {
		if ( !(o instanceof ResourceBean) )
			return false;
		ResourceBean r = (ResourceBean)o;
		return r.getUri().equals(this.getUri());
	}
	
	public String toString() {
		return getUri();
	}

	

}
