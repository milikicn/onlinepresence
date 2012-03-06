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
package net.onlinepresence.jopo.ontmodel.opoactions;

import net.onlinepresence.jopo.ontmodel.opo.Action;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace(Constants.OPO_ACTIONS_NS)
@RdfType("Listening")
public class Listening<T> extends Action {

	private static final long serialVersionUID = -1119964731771453365L;
	private T listeningTo;

	public Listening() {
		super();
	}
	
	public Listening(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_ACTIONS_NS + "listeningTo")
	public T getListeningTo() {
		return listeningTo;
	}

	public void setListeningTo(T listeningTo) {
		if(listeningTo != null)
			this.listeningTo = listeningTo;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof Listening))
			return false;

		Listening listen = (Listening) (o);
			
		return
			EqualsUtil.areEqual(getListeningTo(), listen.getListeningTo()) &&
			super.equals(listen);
	}
}
