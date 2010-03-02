package net.onlinepresence.domainmodel.interfaces;

import java.util.Collection;

import net.onlinepresence.domainmodel.foaf.interfaces.Image;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.interfaces.actions.Action;
import net.onlinepresence.domainmodel.interfaces.geo.SpatialThing;
import net.onlinepresence.domainmodel.interfaces.sharingspace.SharingSpace;
import net.onlinepresence.domainmodel.sioc.interfaces.Item;
import net.onlinepresence.domainmodel.sioc.interfaces.User;

public interface OnlinePresence extends Resource{

	public void setAgent(Agent agent);
	public Agent getAgent();
	
	public void setAvatar(Image avatar);
	public Image getAvatar();
	
	public void setAction(Action action);
	public Action getAction();
	
	public void setLocation(SpatialThing location);
	public SpatialThing getLocation();
	
	public void setStatusMessage(Item statusMessage);
	public Item getStatusMessage();
	
	public void getUser(User user);
	public User getUser();
	
	public void setPresenceComponents(Collection<OnlinePresenceComponent> presenceComponents);
	public Collection<OnlinePresenceComponent> getPresenceComponents();
	public void addPresenceComponent(OnlinePresenceComponent presenceComponent);
	
	public void setIntendentFor(SharingSpace intendentFor);
	public SharingSpace getIntendentFor();
	
	public void setSource(SourceOfPublishing source);
	public SourceOfPublishing getSource();
	
	public void setDuration(int duration);
	public int getDuration();
	
	public void setStartTime(String startTime);
	public String getStartTime();
}
