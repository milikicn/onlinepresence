package net.onlinepresence.domainmodel.opo.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.opo.interfaces.presencecomponents.OnlineStatusBean;

/**
 * The component of the OnlineStatus representing one of its dimensions.
 *
 */
public interface OnlineStatusComponentBean extends ResourceBean {

	public void setComponent(OnlineStatusBean component);
	public OnlineStatusBean getComponent();
}
