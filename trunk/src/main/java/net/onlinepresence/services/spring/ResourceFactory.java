package net.onlinepresence.services.spring;

import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.util.urigenerator.URIBuilder;

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
	public Object createResource(Class clazz ){
		Resource res = (Resource) context.getBean(clazz.getName());
		res.setUri(URIBuilder.instance().generateURI(res, namespace));
		return res;
	}

	@SuppressWarnings("unchecked")
	public Class getBeanImplementationClass(Class clazz) {
		return context.getBean(clazz.getName()).getClass();
	}
}
