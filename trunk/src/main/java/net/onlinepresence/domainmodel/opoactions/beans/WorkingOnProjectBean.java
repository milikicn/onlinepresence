/**
 *  Java OPO library
 *  Copyright (C) 2010  Filip Radulovic, Nikola Milikic
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/.
 *  
 *  You any further questions regarding usage of this software you can 
 *  find appropriate contacts on the OPO Prject website 
 *  http://online-presence.net.
 */
package net.onlinepresence.domainmodel.opoactions.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.doap.Project;
import net.onlinepresence.domainmodel.opoactions.WorkingOnProject;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("WorkingOnProject")
public class WorkingOnProjectBean extends WorkingBean implements WorkingOnProject {

	private Project project;

	public WorkingOnProjectBean() {
		super();
	}
	
	public WorkingOnProjectBean(String uri) {
		super(uri);
	}
	
	public WorkingOnProjectBean(Project project) {
		super();
		setProject(project);
	}
	
	public WorkingOnProjectBean(String uri, Project project) {
		super(uri);
		setProject(project);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#project")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		if(project != null)
			this.project = project;
	}
	
}
