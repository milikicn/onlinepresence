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
package net.onlinepresence.util.urigenerator;

import java.util.UUID;

/**
 * This generator is based on a immutable universally unique identifier (UUID). 
 * A UUID represents a 128-bit value. URI is created by appending randomly generated 
 * UUID to the given namespace. 
 *
 */
public class UUIDBasedUriGenerator extends UriGenerator {

	@Override
	public String generateInstanceUri(Object instance, String namespace) {
		return namespace +
				instance.getClass().getSimpleName() +
				"/" +
				UUID.randomUUID();
	}

}