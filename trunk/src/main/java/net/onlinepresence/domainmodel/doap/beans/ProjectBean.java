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
package net.onlinepresence.domainmodel.doap.beans;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.doap.Project;
import net.onlinepresence.domainmodel.general.ResourceBean;

@Namespace("http://usefulinc.com/ns/doap#")
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
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ProjectBean))
			throw new RuntimeException("Equals exception");
		
			ProjectBean pr = (ProjectBean)(o);
			
			if(getHomepage().equals(pr.getHomepage()) && 
					getName().equals(pr.getName()) &&
					getDateCreated().equals(pr.getDateCreated()) &&
					getShortDescription().equals(pr.getShortDescription()))
			
				return true;
			else 
				return false;
	}
}
