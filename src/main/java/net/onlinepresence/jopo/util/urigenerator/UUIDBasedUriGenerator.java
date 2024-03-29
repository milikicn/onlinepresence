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
package net.onlinepresence.jopo.util.urigenerator;

import java.util.UUID;

import net.onlinepresence.jopo.ontmodel.general.Resource;

/**
 * This generator is based on a immutable universally unique identifier (UUID). 
 * A UUID represents a 128-bit value. URI is created by appending randomly generated 
 * UUID to the given namespace. 
 *
 */
public class UUIDBasedUriGenerator extends UriGenerator {

	@Override
	public String generateInstanceUri(Class<? extends Resource> clazz, String namespace) {
		return namespace +
				clazz.getSimpleName() +
				"/" +
				UUID.randomUUID();
	}

}
