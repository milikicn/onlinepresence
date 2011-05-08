/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Mar 13, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.service;

import java.util.Set;

import net.onlinepresence.opos.core.spring.AbstractSpringTest;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.ApplicationBean;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.beans.UserBean;
import net.onlinepresence.opos.domain.service.beans.UserManagerBean;

import org.testng.annotations.Test;

/**
 * @author Nikola Milikic
 *
 */
public class PersonsBeanTest extends AbstractSpringTest{

	private UserManagerBean personsBeanUnderTest;
	
	
	
	@Test
	public void testGetAll() {
		assertNotNull(personsBeanUnderTest.getUsers());
	}
	
	public void onSetUpInTransaction() throws Exception{
//		super.onSetUpInTransaction();
		personsBeanUnderTest= createUnderTest();
		
	}
	
	@Override
	protected UserManagerBean createUnderTest() {
		return (UserManagerBean) getApplicationContext().getBean(UserManager.class.getName());
	}
	
	@Test
	public void testGetPerson(){
		User person = (User) personsBeanUnderTest.findUser("test");
		assertEquals("test", person.getName());		
	}
	
	@Test
	public void addPerson(){
		User p = new UserBean();
		p.setUsername("testUsername");
		p.setPassword("testPass");
		p.setName("testFirstName");
		p.setEmail("testLastName");
		
		Application app = new ApplicationBean();
		app.setName(ApplicationNames.TWITTER);
		app.setWebAddress("http://www.twitter.com.test");
		
		Membership m = new MembershipBean();
		m.setApplication(app);
		m.setUser(p);
		m.setUsername("testMembUser");
		m.setSendTo(true);
		m.setReceiveFrom(false);
		
		p.addApplicationMembership(m);

		personsBeanUnderTest.addUser(p);
		
		flush();
		assertNotNull(p.getUsername());
		

		
		User personResult = personsBeanUnderTest.findUser(p.getUsername());
		
		assertEquals("testUsername", personResult.getUsername());
		assertEquals("testFirstName", personResult.getName());
		
		//pets
		
		Set<Membership> memberships = personResult.getApplicationMemberships();
		assertEquals(1, memberships.size());
		
	}
	
	@Test
	public void update(){
		
		User person = (User) personsBeanUnderTest.findUser("filiprd");
		person.setName("changedFirstName");
		personsBeanUnderTest.update(person);
		flush();
		User updatedPerson = (User) personsBeanUnderTest.findUser("filiprd");
		assertEquals("changedcontact", updatedPerson.getName());
		
	}
	
	@Test	
	public void removePerson(){
		User toDelete = (User) personsBeanUnderTest.findUser("nikolakv");
		personsBeanUnderTest.removeUser(toDelete);
		flush();
		
		assertNull((User) personsBeanUnderTest.findUser("nikolakv"));
	}
	
	@Test
	public void testExist(){		
	}
	
	
}