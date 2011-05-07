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
package net.onlinepresence.jopo.ontmodel.general;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

import net.onlinepresence.jopo.util.Constants;

import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace(Constants.RDF_NS)
@RdfType("Resource")
public class ResourceBean
implements Resource, Serializable{

	private static final long serialVersionUID = -2234520192109835808L;
	protected URI uri;
	
	/**
	 * corresponds to the dct:created property
	 */
	private Date dateCreated;
	
	@Deprecated
	public ResourceBean() {	}
	
	@Deprecated
	public ResourceBean(URI uri) {
		this.uri = uri;
	}
	
	@Deprecated
	public ResourceBean(String uri) {
		this.uri = URI.create(uri);
	}
	
	@Id
	public URI getUri() {
		return uri;
	}
	
	public void setUri(URI uri){
		this.uri = uri;
	}
	
	public void setUri(String uri){
		this.uri = URI.create(uri);
	}
	
	@RdfProperty(Constants.DC_TERMS_NS + "language")
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public boolean equals(Object o) {
		if ( !(o instanceof Resource) )
			return false;
		Resource r = (Resource)o;
		return r.getUri().equals(this.getUri());
	}
	
	public String toString() {
		return getUri().toString();
	}

}
