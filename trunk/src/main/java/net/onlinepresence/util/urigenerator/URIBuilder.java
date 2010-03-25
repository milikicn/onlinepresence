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

import net.onlinepresence.util.MiscConstants;
import net.onlinepresence.util.PropertiesManager;

public class URIBuilder implements MiscConstants {

	private UriGenerator uriGenerator;

	private static URIBuilder instance;

	public static URIBuilder instance() {
		if (instance == null)
			instance = new URIBuilder();
		return instance;
	}

	private URIBuilder() {
		uriGenerator = new UUIDBasedUriGenerator();
	}
	public String generateURI(Object instance, String namespace) {
		return uriGenerator.generateInstanceUri(instance, namespace);
	}

	public String generateURI(Object instance) {
		return generateURI(instance, PropertiesManager.instance().getProperty(
				TRIPLE_STORE_NAMESPACE));
	}

}
