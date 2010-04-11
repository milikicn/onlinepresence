package net.onlinepresence.services.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OPOResourceFactory {

	private static ApplicationContext context;
	public static String namespace;
	
	public OPOResourceFactory(String namespace){
		context = createContext();
	}

	public ApplicationContext createContext() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				getContextLocations());
    	context.registerShutdownHook();
    	return context;
	}

	public String[] getContextLocations() {
		String[] contextLocations = {
			"META-INF/net/onlinepresence/services/spring/context.xml"
		};
		return contextLocations;
	}

	public ApplicationContext getContext() {
		if (context == null) context = createContext();
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public Object getResource(Class clazz ){
		return context.getBean(clazz.getName());
	}
}
