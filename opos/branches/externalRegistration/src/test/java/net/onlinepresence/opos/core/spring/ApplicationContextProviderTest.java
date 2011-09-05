package net.onlinepresence.opos.core.spring;

import org.testng.annotations.BeforeMethod;

public abstract class ApplicationContextProviderTest {

	@SuppressWarnings("unused")
	private ApplicationContextProvider providerUnderTest;
	
	protected abstract ApplicationContextProvider createUnderTest();
	
	@BeforeMethod
	public void setUp(){
		providerUnderTest = createUnderTest();
	}
	
//	@Test
//	public void testCreateContext(){
//		assertNotNull(providerUnderTest.createContext());
//	}
//	
//	@Test
//	public void testGetContext(){
//		RegistryBuilder registryBuilder = new RegistryBuilder();
//		registryBuilder.add(AppModule.class);
//		
//		Registry registry = registryBuilder.build();
//		registry.performRegistryStartup();
//		
//		ApplicationContext context = registry.getService(ApplicationContext.class);
//		assertNotNull(context);
//	}
//	
//	@Test
//	public void getPersonBean(){
//		RegistryBuilder registryBuilder = new RegistryBuilder();
//		registryBuilder.add(AppModule.class);
//		
//		Registry registry = registryBuilder.build();
//		registry.performRegistryStartup();
//		
//		ApplicationContext context = registry.getService(ApplicationContext.class);
//		PersonBean person = (PersonBean) context.getBean("PersonBean");
//		
//		assertNotNull(person);
//	}
//	
//	@Test
//	public void testObjectProvider(){
//		RegistryBuilder registryBuilder = new RegistryBuilder();
//		registryBuilder.add(AppModule.class);
//		
//		Registry registry = registryBuilder.build();
//		registry.performRegistryStartup();
//		
//		Person bean =  registry.getService("Person", Person.class);
//		
//		assertNotNull(bean);
//	}
}
