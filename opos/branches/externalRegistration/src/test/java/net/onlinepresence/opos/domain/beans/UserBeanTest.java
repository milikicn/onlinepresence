package net.onlinepresence.opos.domain.beans;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertSame;

import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.UserBean;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;


public class UserBeanTest {
	
	private User personUnderTest;
	
	@BeforeMethod
	public void setUp() {
		personUnderTest = new UserBean();
	}
	
	@Test
	public void testFirstName() {
		assertNull(personUnderTest.getName());
		String firstname = "Filip";
		personUnderTest.setName(firstname);
		assertSame(firstname, personUnderTest.getName());
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testFirstNameIfNull() {
		personUnderTest.setName(null);
	}
	
	@Test
	public void testLastName(){
		assertNull(personUnderTest.getEmail());
		String lastname = "Radulovic";
		personUnderTest.setEmail(lastname);
		assertSame(lastname, personUnderTest.getEmail());
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testLastNameIfNull() {
		personUnderTest.setEmail(null);
	}
	
	@Test
	public void testUsername() {
		assertNull(personUnderTest.getUsername());
		String username = "filiprd";
		personUnderTest.setUsername(username);
		assertSame(username, personUnderTest.getUsername());
	}
	
	@Test
	public void testPassword(){
		assertNull(personUnderTest.getPassword());
		String password = "somepass";
		personUnderTest.setPassword(password);
		assertSame(password, personUnderTest.getPassword());
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testPasswordIfNull() {
		personUnderTest.setPassword(null);
	}
	
	@Test
	public void testApplicationAssociations() {
		assertNotNull(personUnderTest.getApplicationMemberships());
	}
	
	@DataProvider(name = "usernames")
	public Object[][] createUsernames() {
	 return new Object[][] {
	   { null },
	   { "123"}
	 };
	}
	
	@Test(expectedExceptions = {IllegalArgumentException.class}, dataProvider = "usernames")
	public void testUsernameIllegalArgument(String name) {
		personUnderTest.setUsername(name);
	}
}
