package net.onlinepresence.opos.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProviderSingleton implements
		ApplicationContextProvider {

	private static ApplicationContext context;

	public ApplicationContext createContext() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				getContextLocations());
    	context.registerShutdownHook();
    	return context;
	}

	public String[] getContextLocations() {
		String[] contextLocations = {
				"META-INF/net/onlinepresence/opos/core/spring/context.xml",
				"META-INF/net/onlinepresence/opos/core/mysqldb/context.xml",
				"META-INF/net/onlinepresence/opos/core/hibernate/context.xml",
				"META-INF/net/onlinepresence/opos/core/tapestry/context.xml",
				"META-INF/net/onlinepresence/opos/crud/context.xml",
				"META-INF/net/onlinepresence/opos/domain/context.xml",
				"META-INF/net/onlinepresence/opos/service/context.xml"
			};
		return contextLocations;
	}

	public ApplicationContext getContext() {
		if (context == null) 
			context = createContext();
		return context;
	}

}
