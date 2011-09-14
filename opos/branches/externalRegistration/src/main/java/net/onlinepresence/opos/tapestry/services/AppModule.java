package net.onlinepresence.opos.tapestry.services;

import net.onlinepresence.opos.core.spring.ApplicationContextObjectProvider;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.domain.service.beans.ApplicationManagerBean;
import net.onlinepresence.opos.domain.service.beans.UserManagerBean;

import org.apache.tapestry5.*;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ObjectProvider;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Startup;
import org.apache.tapestry5.services.RequestFilter;
import org.springframework.context.ApplicationContext;

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
	
	@Startup
	public static void initApplication() {
		// persisting into the database information about applications OPOS supports
		ApplicationContext context = new ApplicationContextProviderSingleton().getContext();
		ApplicationManager appManager = (ApplicationManager) context.getBean(ApplicationManager.class.getName());
		
		// Twitter application
		Application twitterApplication = new Application();
		twitterApplication.setWebAddress("http://twitter.com");
		twitterApplication.setName(ApplicationNames.TWITTER);
		appManager.save(twitterApplication);
		
		// Facebook application
		Application facebookApplication = new Application();
		facebookApplication.setWebAddress("http://www.facebook.com");
		facebookApplication.setName(ApplicationNames.FACEBOOK);
		appManager.save(facebookApplication);
		
		// Spark application
		Application sparkApplication = new Application();
		sparkApplication.setWebAddress("http://www.igniterealtime.org/projects/spark/");
		sparkApplication.setName(ApplicationNames.SPARK);
		appManager.save(sparkApplication);
		
		// Foursquare application
		Application foursquareApplication = new Application();
		foursquareApplication.setWebAddress("https://foursquare.com");
		foursquareApplication.setName(ApplicationNames.FOURSQUARE);
		appManager.save(foursquareApplication);
		
		// Moodle application
		Application moodleApplication = new Application();
		moodleApplication.setWebAddress("http://www.moodle.com");
		moodleApplication.setName(ApplicationNames.MOODLE);
		appManager.save(moodleApplication);
		
		
		// instantiation MediatorManager which will instantiate threads for checking 
		// profile information on supported applications
//		MediatorManager.getInstance().init();
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
	
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> filter,  
    		@Inject
			@SpringBean("org.apache.tapestry5.services.RequestFilter")
			RequestFilter openSessionInViewFilter) {
    		    filter.add("openSessionInViewFilter",openSessionInViewFilter);
    }
    
//    /**
//     * Needed for tapestry-resteasy in order to wind classes
//     * 
//     * @param configuration
//     */
//    public static void contributeResteasyPackageManager(Configuration<String> configuration) {
//		configuration.add("net.onlinepresence.opos.api");
//	}
    
    public static UserManager buildUserManager() {
    	return new UserManagerBean();
    }
    
    public static ApplicationManager buildApplicationManager() {
    	return new ApplicationManagerBean();
    }
    
    public static ApplicationContext buildApplicationContext() {
    	return new ApplicationContextProviderSingleton().getContext();
    }    
}
