/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package statusComponents;

import java.net.URI;
import java.util.Date;

/**
 * @author Nikola Milikic
 *
 */
public class Activity extends OnlineStatusComponent {
	
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
	public static Activity PROLONGED_INACTIVE = new Activity();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		ACTIVE.setURI(URI.create("http://ggg.milanstankovic.org/opo/ns#Active"));
		INACTIVE.setURI(URI.create("http://ggg.milanstankovic.org/opo/ns#Inactive"));
		PROLONGED_INACTIVE.setURI(URI.create("http://ggg.milanstankovic.org/opo/ns#ProlongedInactive"));
	}
	
	public static Activity getInstance(String name){
		if(name.equalsIgnoreCase("active"))
			return ACTIVE;
		else if(name.equalsIgnoreCase("inactive"))
			return INACTIVE;
		else
			return PROLONGED_INACTIVE;
	}
}
