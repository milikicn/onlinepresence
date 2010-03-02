package net.onlinepresence.domainmodel.general;

import net.onlinepresence.util.urigenerator.URIBuilder;
import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import thewebsemantic.Uri;


@Namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@RdfType("Resource")
public class ResourceCls
implements Resource{

	protected String uri;
	
	public ResourceCls() {
		uri = URIBuilder.instance().generateURI(this);
	}
	
	public ResourceCls(String uri) {
		this.uri = uri;
	}
	
	@Uri
	public String getUri() {
		return uri;
	}
	
	public boolean equals(Object o) {
		if ( !(o instanceof Resource) )
			return false;
		Resource r = (Resource)o;
		return r.getUri().equals(this.getUri());
	}
	
	public String toString() {
		return getUri();
	}

	

}
