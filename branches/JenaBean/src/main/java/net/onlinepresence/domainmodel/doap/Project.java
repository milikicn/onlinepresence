package net.onlinepresence.domainmodel.doap;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.doap.interfaces.ProjectBean;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.general.Resource;

@Namespace("http://usefulinc.com/ns/doap#")
@RdfType("Project")
public class Project extends Resource implements ProjectBean {

	private DocumentBean homepage;
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
	public DocumentBean getHomepage() {
		return homepage;
	}

	public void setHomepage(DocumentBean homepage) {
		if(homepage != null)
			this.homepage = homepage;
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
