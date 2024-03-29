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
package net.onlinepresence.jopo.ontmodel.opoactions.beans;

import net.onlinepresence.jopo.ontmodel.opo.beans.ActionBean;
import net.onlinepresence.jopo.ontmodel.opoactions.Watching;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace(Constants.OPO_ACTIONS_NS)
@RdfType("Watching")
public class WatchingBean<T> extends ActionBean implements Watching<T> {

	private static final long serialVersionUID = -5896058348004113174L;
	private T watching;

	@Deprecated
	public WatchingBean() {
		super();
	}
	
	@Deprecated
	public WatchingBean(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_ACTIONS_NS + "watching")
	public T getWatching() {
		return watching;
	}

	public void setWatching(T watching) {
		if(watching != null)
			this.watching = watching;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof WatchingBean))
			return false;

		WatchingBean watc = (WatchingBean) (o);
			
		return
			EqualsUtil.areEqual(getWatching(), watc.getWatching()) &&
			super.equals(watc);
	}
}
