package net.onlinepresence.opos.core.spring;


import net.onlinepresence.opos.core.persistance.PersistenceManager;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public abstract class AbstractSpringTest extends
AbstractTransactionalDataSourceSpringContextTests {

	private Object underTest;

	private PersistenceManager<?> persistenceManager;

	public PersistenceManager<?> getPersistenceManager() {
		return persistenceManager;
	}

	public void setPersistenceManager(PersistenceManager<?> persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	@Override
	protected String[] getConfigLocations() {
		//return new ApplicationContextProviderSingleton().getContextLocations();
		return (new ApplicationContextProviderSingleton() {
			
			@Override
			public String[] getContextLocations() {
				String[] contextLocations = {
						"META-INF/net/onlinepresence/opos/core/spring/context.xml",
						"META-INF/net/onlinepresence/opos/core/mysqldb/context.xml",
						"META-INF/net/onlinepresence/opos/core/hibernate/context.xml",
						"META-INF/net/onlinepresence/opos/crud/context.xml",
						"META-INF/net/onlinepresence/opos/domain/context.xml",
						"META-INF/net/onlinepresence/opos/service/context.xml"
					};
				return contextLocations;
			}
		}).getContextLocations();
	}

	protected abstract Object createUnderTest();

	@Override
	public void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		underTest = createUnderTest();
		executeSqlScript("classpath:test-data.sql", false);

	}
	
	@BeforeMethod
	public void beforeMethod() throws Exception {
		setUp();
		
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		tearDown();
	}

	@Test
	public void testExist() {
		assertNotNull(underTest);
	}

	protected void flush() {
		persistenceManager.flush();
	}
}
