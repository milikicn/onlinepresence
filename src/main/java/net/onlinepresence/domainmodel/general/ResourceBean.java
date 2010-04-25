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
package net.onlinepresence.domainmodel.general;

import java.io.Serializable;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import thewebsemantic.Uri;

@Namespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#")
@RdfType("Resource")
public class ResourceBean
implements Resource, Serializable{

	private static final long serialVersionUID = -2234520192109835808L;
	protected String uri;
	
	@Deprecated
	public ResourceBean() {	}
	
	@Deprecated
	public ResourceBean(String uri) {
		this.uri = uri;
	}
	
	@Uri
	public String getUri() {
		return uri;
	}
	
	public void setURI(String uri){
		this.uri = uri;
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
