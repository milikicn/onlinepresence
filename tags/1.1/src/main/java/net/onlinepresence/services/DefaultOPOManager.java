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
import java.util.LinkedList;
import java.util.List;

import net.onlinepresence.ontmodel.general.Resource;
import net.onlinepresence.services.datacentral.DataProvider;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.rdf.model.Model;

public class DefaultOPOManager implements OPOManager {

	protected DataProvider dataProvider;
	protected Bean2RDF writer;
	protected RDF2Bean reader;
	protected Model dataSource;
	
	public void initialize(){
		this.dataSource = dataProvider.getData();
		this.writer = new Bean2RDF(dataSource);
		this.reader = new RDF2Bean(dataSource);
	}

	@Override
	public <T extends Resource> void deleteResource(Class<T> clazz,
			String resourceURI, boolean loadDeep) {

		LinkedList<T> resList = (LinkedList<T>) loadAllResources(clazz, loadDeep);
		
		Iterator<T> iterator = resList.iterator();
		
		while (iterator.hasNext()) {
			T t = (T) iterator.next();
			
			if(((Resource) t).getUri().equals(resourceURI)){
				deleteResource(t);
				break;
			}
		}
	}

	@Override
	public <T extends Resource> void deleteResource(T t) {
		writer.delete(t);
		
		dataProvider.updateDataSource(dataSource);
	}

	@Override
	public <T extends Resource> Collection<T> loadAllResources(Class<T> clazz,
			boolean loadDeep)  {
		
		if ( loadDeep )
			return reader.loadDeep(clazz);
		else
			return reader.load(clazz);
	}

	@Override
	public <T extends Resource> T loadResource(Class<T> clazz,
			String resourceUri, boolean loadDeep) {

		if ( loadDeep )
			return reader.loadDeep(clazz, resourceUri);
		else
			return reader.load(clazz, resourceUri);
	}

	@Override
	public <T extends Resource> Collection<T> loadResources(Class<T> clazz,
			Collection<String> resourceURIs, boolean loadDeep)  {

		List<T> resources = new LinkedList<T>();
		Iterator<String> resIter = resourceURIs.iterator();
		
		while (resIter.hasNext()) {
			resources.add(loadResource(clazz, resIter.next(), loadDeep));
		}
		
		return resources;
	}

	@Override
	public <T extends Resource> void saveResource(T t, boolean deep) {
		
		if(deep)
			writer.saveDeep(t);
		else 
			writer.save(t);
		
		dataProvider.updateDataSource(dataSource);
	}

	@Override
	public <T extends Resource> void updateResource(T t, boolean deep) {

		if(deep)
			writer.saveDeep(t);
		else 
			writer.save(t);
		
		dataProvider.updateDataSource(dataSource);
	}
}
