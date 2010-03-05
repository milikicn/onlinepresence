package net.onlinepresence.domainmodel.opoactions.interfaces;

import net.onlinepresence.domainmodel.doap.interfaces.ProjectBean;

/**
 * The action of working on a project.
 *
 */
public interface WorkingOnProjectBean extends WorkingBean {

	ProjectBean getProject();
	void setProject(ProjectBean project);
}
