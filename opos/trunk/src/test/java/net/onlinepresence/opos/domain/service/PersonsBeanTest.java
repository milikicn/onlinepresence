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
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.ApplicationBean;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.beans.UserBean;
import net.onlinepresence.opos.domain.service.beans.UsersBean;

import org.testng.annotations.Test;

/**
 * @author Nikola Milikic
 *
 */
public class PersonsBeanTest extends AbstractSpringTest{

	private UsersBean personsBeanUnderTest;
	
	
	
	@Test
	public void testGetAll() {
		assertNotNull(personsBeanUnderTest.getUsers());
	}
	
	public void onSetUpInTransaction() throws Exception{
//		super.onSetUpInTransaction();
		personsBeanUnderTest= createUnderTest();
		
	}
	
	@Override
	protected UsersBean createUnderTest() {
		return (UsersBean) getApplicationContext().getBean(Users.class.getName());
	}
	
	@Test
	public void testGetPerson(){
		User person = (User) personsBeanUnderTest.getUser("nikolakv");
		assertEquals("Nikola", person.getName());		
	}
	
	@Test
	public void addPerson(){
		User p = new UserBean();
		p.setUsername("testUsername");
		p.setPassword("testPass");
		p.setName("testFirstName");
		p.setEmail("testLastName");
		
		Application app = new ApplicationBean();
		app.setName("Twitter.test");
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
		

		
		User personResult = personsBeanUnderTest.getUser(p.getUsername());
		
		assertEquals("testUsername", personResult.getUsername());
		assertEquals("testFirstName", personResult.getName());
		
		//pets
		
		Set<Membership> memberships = personResult.getApplicationMemberships();
		assertEquals(1, memberships.size());
		
	}
	
	@Test
	public void update(){
		
		User person = (User) personsBeanUnderTest.getUser("filiprd");
		person.setName("changedFirstName");
		personsBeanUnderTest.update(person);
		flush();
		User updatedPerson = (User) personsBeanUnderTest.getUser("filiprd");
		assertEquals("changedcontact", updatedPerson.getName());
		
	}
	
	@Test	
	public void removePerson(){
		User toDelete = (User) personsBeanUnderTest.getUser("nikolakv");
		personsBeanUnderTest.removeUser(toDelete);
		flush();
		
		assertNull((User) personsBeanUnderTest.getUser("nikolakv"));
	}
	
	@Test
	public void testExist(){		
	}
	
	
}
