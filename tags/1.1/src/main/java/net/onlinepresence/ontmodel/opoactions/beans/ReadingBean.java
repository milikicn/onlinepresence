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
package net.onlinepresence.ontmodel.opoactions.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.ontmodel.opo.beans.ActionBean;
import net.onlinepresence.ontmodel.opoactions.Reading;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

@Namespace(Constants.OPO_ACTIONS_NS)
@RdfType("Reading")
public class ReadingBean<T> extends ActionBean implements Reading<T> {

	private static final long serialVersionUID = -5314424168742022037L;
	private T readingMaterial;

	@Deprecated
	public ReadingBean() {
		super();
	}
	
	@Deprecated
	public ReadingBean(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_ACTIONS_NS + "readingMaterial")
	public T getReadingMaterial() {
		return readingMaterial;
	}

	public void setReadingMaterial(T readingMaterial) {
		if(readingMaterial != null)
			this.readingMaterial = readingMaterial;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof ReadingBean))
			return false;

		ReadingBean read = (ReadingBean) (o);
			
		return
			EqualsUtil.areEqual(getReadingMaterial(), read.getReadingMaterial()) &&
			super.equals(read);
	}
}
