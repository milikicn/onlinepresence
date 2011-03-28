package net.onlinepresence.opos.core.spring;

import org.springframework.context.ApplicationContext;

public interface ApplicationContextProvider {

	ApplicationContext getContext();
	
	ApplicationContext createContext();
	
	String[] getContextLocations();
}
