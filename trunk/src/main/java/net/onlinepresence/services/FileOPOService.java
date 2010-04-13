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
package net.onlinepresence.services;

import java.util.Collection;
import java.util.Iterator;

import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.services.datacentral.FileDataProvider;
import net.onlinepresence.services.spring.OPOResourceFactory;

public class FileOPOService extends DefaultOPOManager {
	
	public FileOPOService(String fileName, String rdfLang){
		dataProvider = new FileDataProvider(fileName, rdfLang);
		initialize();
	}

	public void saveOnlinePresenceToFile(OnlinePresence onlinePresence) {
		
		saveResource(onlinePresence, true);
	}
	
	@SuppressWarnings("unchecked")
	public OnlinePresence loadOnlinePresenceFromFile() {
		
		OPOResourceFactory factory = new OPOResourceFactory();

		Collection<OnlinePresence> cop = (Collection<OnlinePresence>) loadAllResources(factory.getBeanImplementationClass(OnlinePresence.class), true);
		
		Iterator<OnlinePresence> iter = cop.iterator();
		
		return iter.next();
	}

}
