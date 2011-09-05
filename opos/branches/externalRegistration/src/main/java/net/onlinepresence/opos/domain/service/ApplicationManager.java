/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Mar 15, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.service;

import java.util.List;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;

/**
 * @author Nikola Milikic
 *
 */
public interface ApplicationManager {

	public boolean save(Application app);
	
	Application getApplication(ApplicationNames appName);
	
	List<Application> getAllApplications();

	List<Membership> getAllApplicationMemberships(ApplicationNames appName);
}
