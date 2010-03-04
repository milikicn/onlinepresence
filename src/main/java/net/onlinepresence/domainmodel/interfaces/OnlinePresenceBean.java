package net.onlinepresence.domainmodel.interfaces;

import java.util.Collection;

import net.onlinepresence.domainmodel.foaf.interfaces.ImageBean;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.interfaces.actions.ActionBean;
import net.onlinepresence.domainmodel.interfaces.sharingspace.SharingSpaceBean;
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;
import net.onlinepresence.domainmodel.sioc.interfaces.UserBean;

public interface OnlinePresenceBean extends ResourceBean{

	public void setAgent(AgentBean agent);
	public AgentBean getAgent();
	
	public void setAvatar(ImageBean avatar);
	public ImageBean getAvatar();
	
	public void setAction(ActionBean action);
	public ActionBean getAction();
	
	public void setLocation(SpatialThingBean location);
	public SpatialThingBean getLocation();
	
	public void setStatusMessage(ItemBean statusMessage);
	public ItemBean getStatusMessage();
	
	public void setUser(UserBean user);
	public UserBean getUser();
	
	public void setPresenceComponents(Collection<OnlinePresenceComponentBean> presenceComponents);
	public Collection<OnlinePresenceComponentBean> getPresenceComponents();
	public void addPresenceComponent(OnlinePresenceComponentBean presenceComponent);
	
	public void setIntendentFor(SharingSpaceBean intendentFor);
	public SharingSpaceBean getIntendentFor();
	
	public void setSource(SourceOfPublishingBean source);
	public SourceOfPublishingBean getSource();
	
	public void setDuration(int duration);
	public int getDuration();
	
	public void setStartTime(String startTime);
	public String getStartTime();
}
