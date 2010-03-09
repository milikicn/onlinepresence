package net.onlinepresence.domainmodel.doap;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.doap.interfaces.ProjectBean;
import net.onlinepresence.domainmodel.general.Resource;

@Namespace("http://usefulinc.com/ns/doap#")
@RdfType("Project")
public class Project extends Resource implements ProjectBean {

	private URI homepage;
	private String name;
	private String dateCreated;
	private String shortDescription;
	
	public Project() {
		super();
	}

	public Project(String uri) {
		super(uri);
	}

	@RdfProperty("http://usefulinc.com/ns/doap#homepage")
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
	
	@RdfProperty("http://usefulinc.com/ns/doap#name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	@RdfProperty("http://usefulinc.com/ns/doap#created")
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		if(dateCreated != null)
			this.dateCreated = dateCreated;
	}

	@RdfProperty("http://usefulinc.com/ns/doap#shortdesc")
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		if(shortDescription != null)
			this.shortDescription = shortDescription;
	}
	
}
