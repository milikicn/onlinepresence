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
package net.onlinepresence.services.datacentral;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.onlinepresence.util.Constants;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class FileDataProvider implements DataProvider {

	private String dataSourceUrl;
	private String rdfLang;

	/**
	 * @param dataSourceUrl
	 * @param rdfLang
	 */
	public FileDataProvider(String dataSourceUrl, String rdfLang) {
		super();
		this.dataSourceUrl = dataSourceUrl;
		this.rdfLang = rdfLang;
	}

	@Override
	public Model getData() {

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("opo", Constants.OPO_NS);
		model.setNsPrefix("opo-actions", Constants.OPO_ACTIONS_NS);
		model.setNsPrefix("foaf", Constants.FOAF_NS);
		model.setNsPrefix("rdfs", Constants.RDFS_NS);
		model.setNsPrefix("sioc", Constants.SIOC_NS);
		model.setNsPrefix("dc", Constants.DC_NS);
		model.setNsPrefix("xsd", Constants.XSD_NS);
		model.setNsPrefix("wgs84", Constants.WSG84_POS_NS);
		model.setNsPrefix("event", Constants.EVENT_NS);
		model.setNsPrefix("swc", Constants.SWC_NS);
		model.setNsPrefix("jenabean", Constants.JENABEAN_NS);
		model.setNsPrefix("doap", Constants.DOAP_NS);
		
		File dataFile = new File(dataSourceUrl);
		
		if(dataFile.exists()){
			try {
				model.read(new FileInputStream(dataSourceUrl), Constants.OPO_NS, rdfLang);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return model;

	}

	@Override
	public void updateDataSource(Model m) {
		try {
			m.write(new FileOutputStream(dataSourceUrl), rdfLang, Constants.OPO_NS);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
