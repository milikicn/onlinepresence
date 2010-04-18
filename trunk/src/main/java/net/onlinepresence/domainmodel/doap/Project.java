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
package net.onlinepresence.domainmodel.doap;

import java.net.URI;

import net.onlinepresence.domainmodel.general.Resource;

public interface Project extends Resource {
	
	URI getHomepage();
	void setHomepage(URI homepage);
	void setHomepage(String homepage);

	String getName();
	void setName(String name);

	String getDateCreated();
	void setDateCreated(String dateCreated);

	String getShortDescription();
	void setShortDescription(String shortDescription);
	
}
