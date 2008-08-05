/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: Jul 26, 2008
 * @version: 0.1
 */
package agent;

import java.net.URI;
import java.net.URISyntaxException;

import presence.OnlinePresence;
import presence.OntologyConcept;
import presence.PresenceClass;

/**
 * @author Filip Radulovic
 * 
 */
public class Agent extends PresenceClass {

	protected String opoNS = "http://xmlns.com/foaf/0.1/Image";
	private OnlinePresence onlinePresence;

	public static Agent AGENT = new Agent();

	/**
	 * @return the onlinePresence
	 */
	public OnlinePresence getOnlinePresence() {
		return onlinePresence;
	}

	/**
	 * Sets Agent's OnlinePresence properties.
	 * 
	 * @param OnlinePresence
	 */
	public void setOnlinePresence(OnlinePresence OnlinePresence) {
		this.onlinePresence = OnlinePresence;
	}
}
