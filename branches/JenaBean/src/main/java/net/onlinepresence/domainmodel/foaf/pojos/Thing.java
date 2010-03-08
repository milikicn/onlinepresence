package net.onlinepresence.domainmodel.foaf.pojos;

import java.net.URL;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.foaf.interfaces.ThingBean;
import net.onlinepresence.domainmodel.general.Resource;

@Namespace("http://www.w3.org/2002/07/owl")
@RdfType("Thing")
public class Thing extends Resource implements ThingBean {

	private String name;
	private URL homepage;
	private DocumentBean isPrimaryTopicOf;
	
	public Thing() {
		super();
	}

	public Thing(String uri) {
		super(uri);
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/homepage")
	public URL getHomepage() {
		return homepage;
	}

	public void setHomepage(URL homepage) {
		if(homepage != null)
			this.homepage = homepage;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/isPrimaryTopicOf")
	public DocumentBean getIsPrimaryTopicOf() {
		return isPrimaryTopicOf;
	}

	public void setIsPrimaryTopicOf(DocumentBean isPrimaryTopicOf) {
		if(isPrimaryTopicOf != null)
			this.isPrimaryTopicOf = isPrimaryTopicOf;
	}
}
