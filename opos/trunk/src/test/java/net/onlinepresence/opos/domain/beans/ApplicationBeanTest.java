package net.onlinepresence.opos.domain.beans;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertSame;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.beans.ApplicationBean;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ApplicationBeanTest {
	
	private Application applicationUnderTest;
	
	@BeforeMethod
	public void setUp() {
		applicationUnderTest = new ApplicationBean();
	}
	
	@Test
	public void testName() {
		assertNull(applicationUnderTest.getName());
		String appName = "Twitter";
		applicationUnderTest.setName(appName);
		assertSame(appName, applicationUnderTest.getName());
	}
	
	@Test
	public void testWebAddress(){
		assertNull(applicationUnderTest.getWebAddress());
		String url = "http://www.twitter.com";
		applicationUnderTest.setWebAddress(url);
		assertSame(url, applicationUnderTest.getWebAddress());
	}
	
	@Test
	public void testPersonAssociations() {
		assertNotNull(applicationUnderTest.getUserMemberships());
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testNameIfNull() {
		applicationUnderTest.setName(null);
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testWebAdressIfNull() {
		applicationUnderTest.setWebAddress(null);
	}
}
