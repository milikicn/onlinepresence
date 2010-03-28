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
package net.onlinepresence.domainmodel.opo.presencecomponents;

import java.util.Collection;
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.OnlinePresenceComponent;
import net.onlinepresence.domainmodel.opo.beans.OnlineStatusComponentBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ActivityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ContactabilityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.DisturbabilityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.VisibilityBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlineStatus")
public class OnlineStatus extends OnlinePresenceComponent implements OnlineStatusBean{

	private Collection<OnlineStatusComponentBean> statusComponents = new LinkedList<OnlineStatusComponentBean>();
	private String name;
	
	protected VisibilityBean visibility;
	protected DisturbabilityBean disturbability;
	protected ActivityBean activity;
	protected ContactabilityBean contactability;

	public OnlineStatus() {
		super();
	}
	
	public OnlineStatus(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#hasStatusComponent")
	public Collection<OnlineStatusComponentBean> getOnlineStatusComponents() {
		return statusComponents;
	}

	public void setOnlineStatusComponents(
			Collection<OnlineStatusComponentBean> statusComponents) {
		if(statusComponents != null)
			this.statusComponents = statusComponents;
	}
	
	public void addStatusComponent(OnlineStatusComponentBean statusComponent){
		if(statusComponent != null)
			getOnlineStatusComponents().add(statusComponent);
	}

	@RdfProperty("http://online-presence.net/opo/ns#onlineStatusName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	public Collection<OnlineStatusComponentBean> getStatusComponents() {
		return statusComponents;
	}

	public void setStatusComponents(
			Collection<OnlineStatusComponentBean> statusComponents) {
		this.statusComponents = statusComponents;
	}

	public VisibilityBean getVisibility() {
		return visibility;
	}

	public void setVisibility(VisibilityBean visibility) {
		this.visibility = visibility;
		getOnlineStatusComponents().add(visibility);
	}

	public DisturbabilityBean getDisturbability() {
		return disturbability;
	}

	public void setDisturbability(DisturbabilityBean disturbability) {
		this.disturbability = disturbability;
		getOnlineStatusComponents().add(disturbability);
	}

	public ActivityBean getActivity() {
		return activity;
	}

	public void setActivity(ActivityBean activity) {
		this.activity = activity;
		getOnlineStatusComponents().add(activity);
	}

	public ContactabilityBean getContactability() {
		return contactability;
	}

	public void setContactability(ContactabilityBean contactability) {
		this.contactability = contactability;
		getOnlineStatusComponents().add(contactability);
	}
	
}
