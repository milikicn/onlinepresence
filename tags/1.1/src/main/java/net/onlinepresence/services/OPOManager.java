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

import net.onlinepresence.ontmodel.general.Resource;

public interface OPOManager {

	<T extends Resource> void saveResource(T t, boolean deep) throws Exception;

	<T extends Resource> void updateResource(T t, boolean deep)
			throws Exception;

	<T extends Resource> void deleteResource(Class<T> clazz,
			String resourceURI, boolean deep) throws Exception;

	<T extends Resource> void deleteResource(T t) throws Exception;

	<T extends Resource> Collection<T> loadAllResources(Class<T> clazz,
			boolean loadDeep) throws Exception;

	<T extends Resource> Collection<T> loadResources(Class<T> clazz,
			Collection<String> resourceURIs, boolean loadDeep) throws Exception;

	<T extends Resource> T loadResource(Class<T> clazz, String resourceURI,
			boolean loadDeep) throws Exception;

}
