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

import net.onlinepresence.domainmodel.foaf.Image;
import net.onlinepresence.domainmodel.foaf.Person;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Person")
public class PersonBean extends AgentBean implements Person {

	private Image img;

	public PersonBean() {
		super();
	}

	public PersonBean(String uri) {
		super(uri);
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/img")
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		if(img != null)
			this.img = img;
	}

}
