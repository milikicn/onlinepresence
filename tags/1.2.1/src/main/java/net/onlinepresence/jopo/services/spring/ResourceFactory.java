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
package net.onlinepresence.jopo.services.spring;

import net.onlinepresence.jopo.ontmodel.general.Resource;
import net.onlinepresence.jopo.util.urigenerator.URIBuilder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceFactory {

	private static ApplicationContext context;
	private String namespace = "http://online-presence.net/triplestore#";
	
	public ResourceFactory(){
		context = getContext();
	}
	
	public ResourceFactory(String ns){
		if(!ns.endsWith("/") && !ns.endsWith("#")){
			ns = ns + "#";
		}
		context = getContext();
		namespace = ns;
	}

	protected ApplicationContext createContext() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				getContextLocations());
    	context.registerShutdownHook();
    	return context;
	}

	protected String[] getContextLocations() {
		String[] mappings = new String[]{
			"META-INF/net/onlinepresence/domainmodel/doap.xml", 
			"META-INF/net/onlinepresence/domainmodel/foaf.xml", 
			"META-INF/net/onlinepresence/domainmodel/general.xml", 
			"META-INF/net/onlinepresence/domainmodel/geo.xml", 
			"META-INF/net/onlinepresence/domainmodel/opo-presencecomponents.xml", 
			"META-INF/net/onlinepresence/domainmodel/opo-statuscomponents.xml", 
			"META-INF/net/onlinepresence/domainmodel/opo.xml", 
			"META-INF/net/onlinepresence/domainmodel/opoactions.xml", 
			"META-INF/net/onlinepresence/domainmodel/purl.xml", 
			"META-INF/net/onlinepresence/domainmodel/semweb.xml", 
			"META-INF/net/onlinepresence/domainmodel/sioc.xml" 
		};
		return mappings;
	}

	public ApplicationContext getContext() {
		if (context == null) context = createContext();
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Resource> T createResource(Class<T> clazz ){
		Resource res = (Resource) context.getBean(clazz.getName());
		res.setUri(URIBuilder.instance().generateURI(clazz, namespace));
		return (T) res;
	}

	@SuppressWarnings("rawtypes")
	public Class getBeanImplementationClass(Class clazz) {
		return context.getBean(clazz.getName()).getClass();
	}
}
