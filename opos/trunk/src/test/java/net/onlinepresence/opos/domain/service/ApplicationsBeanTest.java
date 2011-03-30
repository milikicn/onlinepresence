package net.onlinepresence.opos.domain.service;

import java.util.List;

import org.testng.annotations.Test;

import net.onlinepresence.opos.core.spring.AbstractSpringTest;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.service.beans.ApplicationsBean;

public class ApplicationsBeanTest extends AbstractSpringTest{

	private ApplicationsBean applicationsBeanUnderTest;
	
	public void onSetUpInTransaction() throws Exception{
//		super.onSetUpInTransaction();
		applicationsBeanUnderTest= createUnderTest();
		
	}
	
	@Override
	protected ApplicationsBean createUnderTest() {
		return (ApplicationsBean) getApplicationContext().getBean(Applications.class.getName());
	}
	
	@Test
	public void testGetApplication(){
		Application app = applicationsBeanUnderTest.getApplication("http://www.twitter.com");
		assertEquals("TWITTER", app.getName());
	}
	
	@SuppressWarnings("rawtypes")
	public void testGetAllApplications(){
		List app = applicationsBeanUnderTest.getAllApplications();
		assertEquals(3, app.size());
	}
	
	@Test
	public void testExist(){		
	}
}
