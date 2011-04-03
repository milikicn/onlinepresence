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
package net.onlinepresence.ontmodel.foaf.beans;

import net.onlinepresence.ontmodel.foaf.Image;
import net.onlinepresence.ontmodel.foaf.Person;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace(Constants.FOAF_NS)
@RdfType("Person")
public class PersonBean extends AgentBean implements Person {

	private static final long serialVersionUID = 1862465439415539L;
	private Image img;

	@Deprecated
	public PersonBean() {
		super();
	}

	@Deprecated
	public PersonBean(String uri) {
		super(uri);
	}

	@RdfProperty(Constants.FOAF_NS + "img")
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		if (img != null)
			this.img = img;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof Person))
			return false;

		PersonBean per = (PersonBean) (o);
			
		return 
			EqualsUtil.areEqual(getImg(), per.getImg()) &&
			super.equals(per);
	}
}
