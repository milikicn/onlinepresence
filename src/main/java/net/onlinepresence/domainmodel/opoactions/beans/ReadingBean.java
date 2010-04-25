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
package net.onlinepresence.domainmodel.opoactions.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.beans.ActionBean;
import net.onlinepresence.domainmodel.opoactions.Reading;

@Namespace("http://online-presence.net/opo-actions/ns#")
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
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#readingMaterial")
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
		if (o instanceof ReadingBean){

			ReadingBean read = (ReadingBean) (o);
	
			if (getReadingMaterial().equals(read.getReadingMaterial()))
	
				return true;
			else
				return false;
		}
		return false;
	}
}
