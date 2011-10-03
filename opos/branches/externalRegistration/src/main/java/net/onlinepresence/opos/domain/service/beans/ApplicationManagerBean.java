/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Mar 15, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;

/**
 * @author Nikola Milikic
 *
 */
public class ApplicationManagerBean 
	extends AbstractManagerBean<Application>
	implements ApplicationManager{
	
	public ApplicationManagerBean(){	}

	/* (non-Javadoc)
	 * @see opos.service.Applications#getAllApplications()
	 */
	public List<Application> getAllApplications() {
		getReader().setClazz(Application.class);
		return getReader().execute();
	}

	/* (non-Javadoc)
	 * @see opos.service.Applications#getApplication(java.net.URL)
	 */
	public Application getApplication(String appName) {
		
		List<Application> applications = getAllApplications();
		for (Application app : applications) {
			if(app.getName().equals(appName)){
				return app;
			}
		}
		return null;
	}
	
	public boolean save(Application app) {
		getWriter().setToSave(app);
		getWriter().execute();
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Membership> getAllApplicationMemberships(String appName) {
		return (List<Membership>) getReader().executeQuery("from Membership where application.name='" + 
				appName +"'");
	}

}
