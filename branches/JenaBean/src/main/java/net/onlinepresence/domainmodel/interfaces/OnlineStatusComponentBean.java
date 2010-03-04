package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.interfaces.presencecomponents.OnlineStatusBean;

public interface OnlineStatusComponentBean extends ResourceBean {

	public void setComponent(OnlineStatusBean component);
	public OnlineStatusBean getComponent();
}
