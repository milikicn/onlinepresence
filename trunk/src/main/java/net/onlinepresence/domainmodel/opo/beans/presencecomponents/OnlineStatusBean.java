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
package net.onlinepresence.domainmodel.opo.beans.presencecomponents;

import java.util.Collection;
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.OnlineStatusComponent;
import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.opo.statuscomponents.Activity;
import net.onlinepresence.domainmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.domainmodel.opo.statuscomponents.Disturbability;
import net.onlinepresence.domainmodel.opo.statuscomponents.Visibility;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlineStatus")
public class OnlineStatusBean extends OnlinePresenceComponentBean implements OnlineStatus{

	private static final long serialVersionUID = -7394557327354492826L;
	private Collection<OnlineStatusComponent> statusComponents = new LinkedList<OnlineStatusComponent>();
	private String name;
	
	protected Visibility visibility;
	protected Disturbability disturbability;
	protected Activity activity;
	protected Contactability contactability;

	@Deprecated
	public OnlineStatusBean() {
		super();
	}
	
	@Deprecated
	public OnlineStatusBean(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#hasStatusComponent")
	public Collection<OnlineStatusComponent> getOnlineStatusComponents() {
		return statusComponents;
	}

	public void setOnlineStatusComponents(
			Collection<OnlineStatusComponent> statusComponents) {
		if(statusComponents != null)
			this.statusComponents = statusComponents;
	}
	
	public void addStatusComponent(OnlineStatusComponent statusComponent){
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

	public Collection<OnlineStatusComponent> getStatusComponents() {
		return statusComponents;
	}

	public void setStatusComponents(
			Collection<OnlineStatusComponent> statusComponents) {
		this.statusComponents = statusComponents;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
		getOnlineStatusComponents().add(visibility);
	}

	public Disturbability getDisturbability() {
		return disturbability;
	}

	public void setDisturbability(Disturbability disturbability) {
		this.disturbability = disturbability;
		getOnlineStatusComponents().add(disturbability);
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
		getOnlineStatusComponents().add(activity);
	}

	public Contactability getContactability() {
		return contactability;
	}

	public void setContactability(Contactability contactability) {
		this.contactability = contactability;
		getOnlineStatusComponents().add(contactability);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof OnlineStatusBean){

			OnlineStatusBean onlineStatus = (OnlineStatusBean) (o);
	
			if (getStatusComponents().equals(onlineStatus.getStatusComponents()) &&
					getName().equals(onlineStatus.getName()) &&
					getVisibility().equals(onlineStatus.getVisibility()) &&
					getDisturbability().equals(onlineStatus.getDisturbability()) &&
					getActivity().equals(onlineStatus.getActivity()) &&
					getContactability().equals(onlineStatus.getContactability()))
	
				return true;
			else
				return false;
		}
		return false;
	}
}
