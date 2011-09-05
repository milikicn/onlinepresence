package net.onlinepresence.opos.core.spring;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApplicationContextProviderSingletonTest extends
		ApplicationContextProviderTest {

	private ApplicationContextProviderSingleton providerSingletonUnderTest;

	@Override
	protected ApplicationContextProviderSingleton createUnderTest() {
		return new ApplicationContextProviderSingleton() {
			
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
		};
	}

	@BeforeMethod
	public void setUp() {
		super.setUp();
		providerSingletonUnderTest = createUnderTest();
	}

	@Test
	public void testGetContextLocations() {
		assertEquals(6, providerSingletonUnderTest.getContextLocations().length);
	}
}
