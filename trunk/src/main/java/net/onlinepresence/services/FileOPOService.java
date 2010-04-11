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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceBean;

public class FileOPOService extends DefaultOPOService {


	public void saveOnlinePresenceToFile(OnlinePresence onlinePresence,
			String fileName, String syntax) {

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("opo", "http://online-presence.net/opo/ns#");
		model.setNsPrefix("opo-actions",
				"http://online-presence.net/opo-actions/ns#");
		model.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		model.setNsPrefix("sioc", "http://rdfs.org/sioc/ns#");
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		model.setNsPrefix("wgs84", "http://www.w3.org/2003/01/geo/wgs84_pos#");
		model.setNsPrefix("event", "http://purl.org/NET/c4dm/event.owl#");
		model
				.setNsPrefix("swc",
						"http://data.semanticweb.org/ns/swc/ontology#");
		model.setNsPrefix("jenabean", "http://thewebsemantic.com/");
		model.setNsPrefix("doap", "http://usefulinc.com/ns/doap#");

		model = super.save(model, onlinePresence);
		
		try {
			model.write(new FileOutputStream(fileName), syntax);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public OnlinePresenceBean loadOnlinePresenceFromFile(String fileName, String syntax) {
		InputStream is;
		Model model = null;
		try {
			is = new BufferedInputStream(new FileInputStream(fileName));
			model = ModelFactory.createDefaultModel().read(
					new InputStreamReader(is),
					"http://online-presence.net/opo/ns#", syntax);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collection<OnlinePresenceBean> cop = super.load(model);
		
		Iterator<OnlinePresenceBean> iter = cop.iterator();
		return iter.next();
	}

}
