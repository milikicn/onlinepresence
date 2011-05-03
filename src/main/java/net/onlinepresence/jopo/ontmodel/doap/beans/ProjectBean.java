/**
 *  Java OPO library
 *  Copyright (C) 2010  Filip Radulovic, Nikola Milikic
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/.
 *  
 *  You any further questions regarding usage of this software you can 
 *  find appropriate contacts on the OPO Prject website 
 *  http://online-presence.net.
 */
package net.onlinepresence.jopo.ontmodel.doap.beans;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.doap.Project;
import net.onlinepresence.jopo.ontmodel.general.ResourceBean;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.DOAP_NS)
@RdfType("Project")
public class ProjectBean extends ResourceBean implements Project {

	private static final long serialVersionUID = -8108929907073854002L;
	private URI homepage;
	private String name;
	private String dateCreated;
	private String shortDescription;
	
	@Deprecated
	public ProjectBean() {
		super();
	}

	@Deprecated
	public ProjectBean(String uri) {
		super(uri);
	}

	@RdfProperty(Constants.DOAP_NS + "homepage")
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
	
	@RdfProperty(Constants.DOAP_NS + "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	@RdfProperty(Constants.DOAP_NS + "created")
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		if(dateCreated != null)
			this.dateCreated = dateCreated;
	}

	@RdfProperty(Constants.DOAP_NS + "shortdesc")
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		if(shortDescription != null)
			this.shortDescription = shortDescription;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof ProjectBean))
			return false;
		
		ProjectBean project = (ProjectBean)(o);
			
		return
			EqualsUtil.areEqual(getHomepage(), project.getHomepage()) &&
			EqualsUtil.areEqual(getName(), project.getName()) &&
			EqualsUtil.areEqual(getDateCreated(), project.getDateCreated()) &&
			EqualsUtil.areEqual(getShortDescription(), project.getShortDescription());
	}
}
