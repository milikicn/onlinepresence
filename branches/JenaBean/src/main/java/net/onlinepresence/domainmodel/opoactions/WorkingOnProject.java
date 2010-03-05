package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.doap.interfaces.ProjectBean;
import net.onlinepresence.domainmodel.opoactions.interfaces.WorkingOnProjectBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("WorkingOnProject")
public class WorkingOnProject extends Working implements WorkingOnProjectBean {

	private ProjectBean project;

	@RdfProperty("http://online-presence.net/opo-actions/ns#project")
	public ProjectBean getProject() {
		return project;
	}

	public void setProject(ProjectBean project) {
		if(project != null)
			this.project = project;
	}
	
}
