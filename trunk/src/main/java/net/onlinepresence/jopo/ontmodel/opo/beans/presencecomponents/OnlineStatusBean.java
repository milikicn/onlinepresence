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
package net.onlinepresence.jopo.ontmodel.opo.beans.presencecomponents;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.opo.OnlineStatusComponent;
import net.onlinepresence.jopo.ontmodel.opo.beans.OnlinePresenceComponentBean;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Activity;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Disturbability;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Visibility;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.OPO_NS)
@RdfType("OnlineStatus")
public class OnlineStatusBean extends OnlinePresenceComponentBean implements OnlineStatus{

	private static final long serialVersionUID = -7394557327354492826L;
	private Collection<OnlineStatusComponent> statusComponents = new LinkedList<OnlineStatusComponent>();
	private String name;

	@Deprecated
	public OnlineStatusBean() {
		super();
	}
	
	@Deprecated
	public OnlineStatusBean(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_NS + "hasStatusComponent")
	public Collection<OnlineStatusComponent> getStatusComponents() {
		return statusComponents;
	}

	public void setStatusComponents(
			Collection<OnlineStatusComponent> statusComponents) {
		if(statusComponents != null)
			this.statusComponents = statusComponents;
	}
	
	public void addStatusComponent(OnlineStatusComponent statusComponent){
		
		if(statusComponent != null){
			OnlineStatusComponent pc = findStatusComponent(statusComponent.getClass());
			
			if(pc != null)
				getStatusComponents().remove(pc);
			
			getStatusComponents().add(statusComponent);
		}
	}

	@RdfProperty(Constants.OPO_NS + "onlineStatusName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}

	public Visibility retrieveVisibility() {
		OnlineStatusComponent vis = findStatusComponent(Visibility.class);
		
		if(vis != null)
			return (Visibility) vis;
		
		return null;
	}

	public void setVisibility(Visibility visibility) {
		addStatusComponent(visibility);
	}

	public Disturbability retrieveDisturbability() {
		OnlineStatusComponent dist = findStatusComponent(Disturbability.class);
		
		if(dist != null)
			return (Disturbability) dist;
		
		return null;
	}

	public void setDisturbability(Disturbability disturbability) {
		addStatusComponent(disturbability);
	}

	public Activity retrieveActivity() {
		OnlineStatusComponent act = findStatusComponent(Activity.class);
		
		if(act != null)
			return (Activity) act;
		
		return null;
	}

	public void setActivity(Activity activity) {
		addStatusComponent(activity);
	}

	public Contactability retrieveContactability() {
		OnlineStatusComponent conct = findStatusComponent(Contactability.class);
		
		if(conct != null)
			return (Contactability) conct;
		
		return null;
	}

	public void setContactability(Contactability contactability) {
		addStatusComponent(contactability);
	}
	
	private OnlineStatusComponent findStatusComponent(
			Class<? extends OnlineStatusComponent> clazz) {

		Iterator<OnlineStatusComponent> iterator = statusComponents.iterator();
		
		while (iterator.hasNext()) {
			OnlineStatusComponent onlineStatusComponent = (OnlineStatusComponent) iterator.next();
			
			try {
				// check if it is a subclass
				onlineStatusComponent.getClass().asSubclass(clazz);
				return onlineStatusComponent;
			} catch (Exception e) {
				
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof OnlineStatusBean))
			return false;

		OnlineStatusBean onlineStatus = (OnlineStatusBean) (o);
			
		return
			EqualsUtil.areEqual(getStatusComponents(), onlineStatus.getStatusComponents()) &&
			EqualsUtil.areEqual(getName(), onlineStatus.getName()) &&
			super.equals(onlineStatus);
	}
}
