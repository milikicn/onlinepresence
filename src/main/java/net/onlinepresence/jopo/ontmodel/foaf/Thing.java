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
package net.onlinepresence.jopo.ontmodel.foaf;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.jopo.ontmodel.general.Resource;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.OWL_NS)
@RdfType("Thing")
public class Thing extends Resource {

	private static final long serialVersionUID = -1945236390412793441L;
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

	@RdfProperty(Constants.FOAF_NS + "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	@RdfProperty(Constants.FOAF_NS + "homepage")
	public URI getHomepage() {
		return homepage;
	}

	public void setHomepage(URI homepage) {
		if (homepage != null)
			this.homepage = homepage;
	}

	public void setHomepage(String homepage) {
		if (homepage != null)
			try {
				setHomepage(new URI(homepage));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	@RdfProperty(Constants.FOAF_NS + "isPrimaryTopicOf")
	public URI getIsPrimaryTopicOf() {
		return isPrimaryTopicOf;
	}

	public void setIsPrimaryTopicOf(URI isPrimaryTopicOf) {
		if (isPrimaryTopicOf != null)
			this.isPrimaryTopicOf = isPrimaryTopicOf;
	}

	public void setIsPrimaryTopicOf(String isPrimaryTopicOf) {
		if (isPrimaryTopicOf != null)
			try {
				setIsPrimaryTopicOf(new URI(isPrimaryTopicOf));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	@RdfProperty(Constants.RDFS_NS + "seeAlso")
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

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
				
		if (!(o instanceof Thing))
			return false;
		
		Thing thing = (Thing) (o);
				
		return
			EqualsUtil.areEqual(getName(), thing.getName()) &&
			EqualsUtil.areEqual(getHomepage(), thing.getHomepage()) &&
			EqualsUtil.areEqual(getIsPrimaryTopicOf(), thing.getIsPrimaryTopicOf()) &&
			EqualsUtil.areEqual(getSeeAlso(), thing.getSeeAlso());
	}
}
