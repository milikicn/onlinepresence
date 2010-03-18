package net.onlinepresence.domainmodel.opo.interfaces;

import java.util.Collection;

import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.foaf.interfaces.ImageBean;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;
import net.onlinepresence.domainmodel.sioc.interfaces.UserBean;

/**
 * OnlinePresence, described in termes of various OnlinePresenceComponents to 
 * represent the attitude of an Agent towards interaction with other Agents and 
 * Applications.
 *
 */
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
	
	public void setDuration(String duration);
	public String getDuration();
	
	public void setStartTime(String startTime);
	public String getStartTime();
}
