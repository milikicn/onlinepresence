package net.onlinepresence.opos.mediatorManagement.mediators;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.exceptions.OPOSException;

public interface OnlinePresenceBuilder {

	OnlinePresence build() throws OPOSException;
}
