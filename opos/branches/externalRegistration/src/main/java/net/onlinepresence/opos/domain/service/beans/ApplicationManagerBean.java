/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Mar 15, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;

import org.apache.tapestry5.ioc.annotations.Inject;

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

	public Application getApplication(String appName) {
		List<Application> applications = getAllApplications();
		
		for (Application app : applications) {
			if(app.getName().equalsIgnoreCase(appName)){
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
		membershipReader.setClazz(Membership.class);
		return (List<Membership>) membershipReader.executeQuery(
				"SELECT * " +
				"FROM MEMBERSHIP AS MEMB " +
					"JOIN APPLICATION AS APP ON MEMB.APPLICATION = APP.WEBADDRESS " +
				"WHERE APP.name='" + appName +"'");
	}
	
	public String getApplicationName(String webAddress) {
		String query =
			"SELECT name " +
			"FROM Application app " +
			"WHERE app.webAddress = \'"+webAddress+"\'";
		
		@SuppressWarnings("unchecked")
		List<String> names = membershipReader.getManager().runQuery(query);
		
		if (names != null && !names.isEmpty()) {
			return names.iterator().next().toUpperCase();
		}
		return null;
	}
	
}
