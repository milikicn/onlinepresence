/**
* @autor: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package statusComponents;

import java.net.URI;
import java.util.Date;

import presence.OntologyConcept;

/**
 * @author Nikola Milikic
 *
 */
public class Activity extends OntologyConcept implements OnlineStatusComponent {
	
	private Date inactivityPeriod;

	/**
	 * @return the inactivityPeriod
	 */
	public Date getInactivityPeriod() {
		return inactivityPeriod;
	}

	/**
	 * @param inactivityPeriod the inactivityPeriod to set
	 */
	public void setInactivityPeriod(Date inactivityPeriod) {
		this.inactivityPeriod = inactivityPeriod;
	}

	/**
	 * The Agent is active on the system.
	 */
	public static Activity ACTIVE = new Activity();
	
	/**
	 * The Agent is inactive for at least 5 min.
	 */
	public static Activity INACTIVE = new Activity();
	
	/**
	 * The Agent is inactive for at least 20 min.
	 */
	public static Activity PROLONGEDINACTIVE = new Activity();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		ACTIVE.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#Active"));
		INACTIVE.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#Inactive"));
		PROLONGEDINACTIVE.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#ProlongedInactive"));
	}
}
