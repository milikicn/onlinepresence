/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Apr 7, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.beans;

import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertSame;

import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.beans.UserBean;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Nikola Milikic
 *
 */
public class MembershipBeanTest {

	private Membership associationUnderTest;
	
	@BeforeMethod
	public void setUp() {
		associationUnderTest = new MembershipBean();
	}
	
//	@Test
//	public void testApplication() {
//		assertNull(associationUnderTest.getApplication());
//		Application app = null;
//		
//		try {
//			app = new ApplicationBean("Twitter", new URL("http://www.twitter.com"));
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		associationUnderTest.setApplication(app);
//		assertSame(app, associationUnderTest.getApplication());
//	}
	
	@Test
	public void testPerson() {
		assertNull(associationUnderTest.getUser());
		User person = new UserBean("Nikola", "Milikic", "nikolakv", "nikola0000", false);
		associationUnderTest.setUser(person);
		assertSame(person, associationUnderTest.getUser());
	}
	
	@Test
	public void testUsername() {
		assertNull(associationUnderTest.getUsername());
		String username = "nikolakv";
		associationUnderTest.setUsername(username);
		assertSame(username, associationUnderTest.getUsername());
	}
}
