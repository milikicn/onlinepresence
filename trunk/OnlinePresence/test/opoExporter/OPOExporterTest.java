/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 12, 2008
* @version: 0.1
*/
package opoExporter;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import agent.Agent;

import presence.OnlinePresence;
import presenceComponents.Findability;
import presenceComponents.Notifiability;
import presenceComponents.OnlineStatus;
import statusComponents.Activity;
import statusComponents.Contactability;
import statusComponents.Disturbability;
import statusComponents.Visibility;

/**
 * @author Nikola Milikic
 *
 */
public class OPOExporterTest{

	OPOExporter opo;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Agent a = new Agent("http://nekiURIZaAgenta.com");
		a.addComponent("name", "nikola milikic");
		a.addComponent("img", URI.create("http://mojaslika.com/slika.jpg"));
		
		OnlineStatus os = new OnlineStatus();
		os.addComponent(Disturbability.AVAILABLE);
		os.addComponent(Contactability.FREELY_CONTACTABLE);
		os.addComponent(Activity.ACTIVE);
		os.addComponent(Visibility.INVISIBLE);

		OnlinePresence op = new OnlinePresence(null, URI.create("http://nekiURIZaOnlinePresence.com"));
		op.setOnlineStatus(os);

		op.addComponent(Findability.PUBLICLY_FINDABLE);
		op.addComponent(Notifiability.NOTIFICATIONS_CONSTRAINED);

		op.setAvatar(URI.create("http://nikola.em3.rs/images/photo.jpg"));

		op.setCustomMessage("watching a game, having a bud");
		
		op.setAgent(a);
		
		opo = new OPOExporter(op);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		opo = null;
	}

	/**
	 * Test method for {@link opoExporter.OPOExporter#makeModel()}.
	 */
	@Test
	public void testMakeModel() {
	}

	/**
	 * Test method for {@link opoExporter.OPOExporter#serializeToXMLRDF(java.lang.String)}.
	 */
	@Test
	public void testSerializeToXMLRDF() {
	}

	/**
	 * Test method for {@link opoExporter.OPOExporter#serializeToRDFNTripple(java.lang.String)}.
	 */
	@Test
	public void testSerializeToRDFNTripple() {
	}
}
