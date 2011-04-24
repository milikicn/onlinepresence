package net.onlinepresence.opos.domain.service;

import java.util.List;

import org.testng.annotations.Test;

import net.onlinepresence.opos.core.spring.AbstractSpringTest;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.service.beans.ApplicationManagerBean;

public class ApplicationsBeanTest extends AbstractSpringTest{

	private ApplicationManagerBean applicationsBeanUnderTest;
	
	public void onSetUpInTransaction() throws Exception{
//		super.onSetUpInTransaction();
		applicationsBeanUnderTest= createUnderTest();
		
	}
	
	@Override
	protected ApplicationManagerBean createUnderTest() {
		return (ApplicationManagerBean) getApplicationContext().getBean(ApplicationManager.class.getName());
	}
	
	@Test
	public void testGetApplication(){
		Application app = applicationsBeanUnderTest.getApplication(ApplicationNames.TWITTER);
		assertEquals(ApplicationNames.TWITTER, app.getName());
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
