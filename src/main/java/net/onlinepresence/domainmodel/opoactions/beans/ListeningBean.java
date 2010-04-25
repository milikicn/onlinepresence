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

import net.onlinepresence.domainmodel.opo.beans.ActionBean;
import net.onlinepresence.domainmodel.opoactions.Listening;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Listening")
public class ListeningBean<T> extends ActionBean implements Listening<T> {

	private static final long serialVersionUID = -1119964731771453365L;
	private T listeningTo;

	@Deprecated
	public ListeningBean() {
		super();
	}
	
	@Deprecated
	public ListeningBean(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#listeningTo")
	public T getListeningTo() {
		return listeningTo;
	}

	public void setListeningTo(T listeningTo) {
		if(listeningTo != null)
			this.listeningTo = listeningTo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof ListeningBean){

			ListeningBean listen = (ListeningBean) (o);
	
			if (getListeningTo().equals(listen.getListeningTo()))
	
				return true;
			else
				return false;
		}
		return false;
	}
}
