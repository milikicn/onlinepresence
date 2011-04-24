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
import net.onlinepresence.opos.domain.service.Applications;
import net.onlinepresence.opos.service.crud.impl.DeleteCommand;
import net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;
import net.onlinepresence.opos.service.crud.impl.UpdateCommand;
import net.onlinepresence.opos.service.crud.impl.WriteCommand;

import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author Nikola Milikic
 *
 */
public class ApplicationsBean implements Applications{
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ReadCommand")
	private ReadCommand<Application> reader;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.UpdateCommand")
	private UpdateCommand<Application> updater;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.WriteCommand")
	private WriteCommand<Application> writer;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<Application> delete;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand")
	private ExampleSearchCommand<Application> searcher;

	//private LinkedList<Application> applications = new LinkedList<Application>();
	
	public ApplicationsBean(){	}

	/* (non-Javadoc)
	 * @see opos.service.Applications#getAllApplications()
	 */
	public List<Application> getAllApplications() {
		reader.setClazz(Application.class);
		return reader.execute();
	}

	/* (non-Javadoc)
	 * @see opos.service.Applications#getApplication(java.net.URL)
	 */
	public Application getApplication(String appUrl) {
		
		List<Application> applications = getAllApplications();
		for (Application app : applications) {
			if(app.getWebAddress().equals(appUrl)){
				return app;
			}
		}
		return null;
	}
	
	public boolean save(Application app) {
		writer.setToSave(app);
		writer.execute();
		return true;
	}

	public ReadCommand<Application> getReader() {
		return reader;
	}

	public void setReader(ReadCommand<Application> reader) {
		this.reader = reader;
	}

	public UpdateCommand<Application> getUpdater() {
		return updater;
	}

	public void setUpdater(UpdateCommand<Application> updater) {
		this.updater = updater;
	}

	public WriteCommand<Application> getWriter() {
		return writer;
	}

	public void setWriter(WriteCommand<Application> writer) {
		this.writer = writer;
	}

	public DeleteCommand<Application> getDelete() {
		return delete;
	}

	public void setDelete(DeleteCommand<Application> delete) {
		this.delete = delete;
	}

	public ExampleSearchCommand<Application> getSearcher() {
		return searcher;
	}

	public void setSearcher(ExampleSearchCommand<Application> searcher) {
		this.searcher = searcher;
	}

	@SuppressWarnings("unchecked")
	public List<Membership> getAllApplicationMemberships(String webAddress) {
		return (List<Membership>) reader.getEagerMemberships("from MembershipBean where application='" + 
				webAddress +"')");
	}

}
