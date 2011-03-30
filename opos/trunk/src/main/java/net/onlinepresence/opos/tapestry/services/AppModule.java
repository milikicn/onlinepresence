package net.onlinepresence.opos.tapestry.services;


import net.onlinepresence.opos.core.spring.ApplicationContextObjectProvider;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.Applications;
import net.onlinepresence.opos.domain.service.Users;
import net.onlinepresence.opos.domain.service.beans.ApplicationsBean;
import net.onlinepresence.opos.domain.service.beans.UsersBean;
import net.onlinepresence.opos.mediators.MediatorManager;

import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ObjectProvider;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.RequestFilter;
import org.springframework.context.ApplicationContext;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * This module is automatically included as part of the Tapestry IoC Registry,
 * it's a good place to configure and extend Tapestry, or to place your own
 * service definitions.
 */
public class AppModule {
	public static void bind(ServiceBinder binder) {
		// binder.bind(MyServiceInterface.class, MyServiceImpl.class);

		// Make bind() calls on the binder object to define most IoC services.
		// Use service builder methods (example below) when the implementation
		// is provided inline, or requires more initialization than simply
		// invoking the constructor.
		
		binder.bind(ObjectProvider.class, 
        		ApplicationContextObjectProvider.class)
                .withId("ApplicationContextObjectProvider");
	}
	
	public static void contributeApplicationDefaults(
			MappedConfiguration<String, String> configuration) {

		configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");
		configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
		configuration.add(SymbolConstants.COMPRESS_WHITESPACE, "false");
	}
	
	public static void contributeMasterObjectProvider(
				@InjectService("ApplicationContextObjectProvider")
		ObjectProvider applicationContextObjectProvider,
		OrderedConfiguration<ObjectProvider> configuration) {
		configuration.add("Spring", applicationContextObjectProvider);
	}
	
	public static User buildPerson(@Inject @SpringBean("PersonBean") User bean){
		return bean;
	}
	
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> filter,  
    		@Inject
			@SpringBean("org.apache.tapestry5.services.RequestFilter")
			RequestFilter openSessionInViewFilter) {
    		    filter.add("openSessionInViewFilter",openSessionInViewFilter);
    }
    
    /**
     * Needed for tapestry-resteasy in order to wind classes
     * 
     * @param configuration
     */
    public static void contributeResteasyPackageManager(Configuration<String> configuration) {
		configuration.add("net.onlinepresence.opos.api");
	}
    
    
    public static Users buildPersons() {
    	return new UsersBean();
    }
    
    public static Applications buildApps() {
    	return new ApplicationsBean();
    }
    
    public static ApplicationContext buildApplicationContext() {
    	return new ApplicationContextProviderSingleton().getContext();
    }
    
    public static Twitter buildTwitter() {
//    	Twitter twitter = 
//		Properties appProp = Util.loadPropertyFile("/mediator_params.properties");
//	    twitter.setOAuthConsumer(appProp.getProperty("twitter-app-API-key"), appProp.getProperty("twitter-app-API-secret"));
	    return new TwitterFactory().getInstance();
    }
    
    public static MediatorManager buildMediatorManager() {
//    	System.out.println("----------------------------MediatorManager");
    	return new MediatorManager();
    }
}
