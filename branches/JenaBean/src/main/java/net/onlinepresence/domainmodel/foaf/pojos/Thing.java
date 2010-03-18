package net.onlinepresence.domainmodel.foaf.pojos;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.foaf.interfaces.ThingBean;
import net.onlinepresence.domainmodel.general.Resource;

@Namespace("http://www.w3.org/2002/07/owl")
@RdfType("Thing")
public class Thing extends Resource implements ThingBean {

	private String name;
	private URI homepage;
	private URI isPrimaryTopicOf;
	private URI seeAlso;
	
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
	public URI getHomepage() {
		return homepage;
	}

	public void setHomepage(URI homepage) {
		if(homepage != null)
			this.homepage = homepage;
	}
	
	public void setHomepage(String homepage) {
		if(homepage != null)
			try {
				setHomepage(new URI(homepage));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/isPrimaryTopicOf")
	public URI getIsPrimaryTopicOf() {
		return isPrimaryTopicOf;
	}

	public void setIsPrimaryTopicOf(URI isPrimaryTopicOf) {
		if(isPrimaryTopicOf != null)
			this.isPrimaryTopicOf = isPrimaryTopicOf;
	}
	
	
	
	public void setIsPrimaryTopicOf(String isPrimaryTopicOf) {
		if(isPrimaryTopicOf != null)
			try {
				setIsPrimaryTopicOf(new URI(isPrimaryTopicOf));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	public URI getSeeAlso() {
		return seeAlso;
	}

	public void setSeeAlso(URI seeAlso) {
		if (seeAlso != null)
			this.seeAlso = seeAlso;
	}
	
	public void setSeeAlso(String seeAlso) {
		if (seeAlso != null)
			try {
				setSeeAlso(new URI(seeAlso));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}
}
