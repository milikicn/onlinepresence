package net.onlinepresence.opos.core.tapestry;

import org.apache.tapestry5.ioc.RegistryBuilder;
import org.testng.annotations.BeforeMethod;

public class RegistryBuilderTest {

	@SuppressWarnings("unused")
	private RegistryBuilder registryBuilder;

	@BeforeMethod
	public void setUp() {
		registryBuilder = new RegistryBuilder();
	}

//	@Test
//	public void getRegistryBuilder() {
//		registryBuilder.add(AppModule.class);
//		Registry r = registryBuilder.build();
//		r.performRegistryStartup();
//		assertNotNull(r);
//	}
}
