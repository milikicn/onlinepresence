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
package net.onlinepresence.domainmodel.opo.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.StatusMessage;
import net.onlinepresence.domainmodel.sioc.beans.ItemBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("StatusMessage")
public class StatusMessageBean extends ItemBean implements StatusMessage {

	private static final long serialVersionUID = 3619444485077014132L;

	@Deprecated
	public StatusMessageBean() {
		super();
	}
	
	@Deprecated
	public StatusMessageBean(String uri) {
		super(uri);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StatusMessageBean))
			throw new RuntimeException("Equals exception");

//		StatusMessageBean statusMes = (StatusMessageBean) (o);

			return true;
//		else
//			return false;
	}
}
