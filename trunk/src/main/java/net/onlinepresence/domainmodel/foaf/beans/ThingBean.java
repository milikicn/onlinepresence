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
package net.onlinepresence.domainmodel.foaf.beans;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.foaf.Thing;
import net.onlinepresence.domainmodel.general.ResourceBean;

@Namespace("http://www.w3.org/2002/07/owl")
@RdfType("Thing")
public class ThingBean extends ResourceBean implements Thing {

	private String name;
	private URI homepage;
	private URI isPrimaryTopicOf;
	private URI seeAlso;
	
	public ThingBean() {
		super();
	}

	public ThingBean(String uri) {
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

	@RdfProperty("http://www.w3.org/2000/01/rdf-schema#seeAlso")
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
