/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Mar 15, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;

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
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ReadCommand")
	private ReadCommand<Membership> membershipReader;
	
	public ReadCommand<Membership> getMembershipReader() {
		return membershipReader;
	}

	public void setMembershipReader(ReadCommand<Membership> reader) {
		this.membershipReader = reader;
	}

	@SuppressWarnings("unchecked")
	public List<Membership> getAllApplicationMemberships(String appName) {
		return (List<Membership>) membershipReader.executeQuery(
				"select * " +
				"from MEMEBERSHIP " +
				"where APPLICATION.name='" + appName +"'");
	}

}
