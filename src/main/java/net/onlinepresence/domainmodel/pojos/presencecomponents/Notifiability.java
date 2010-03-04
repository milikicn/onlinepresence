package net.onlinepresence.domainmodel.pojos.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.interfaces.presencecomponents.NotifiabilityBean;
import net.onlinepresence.domainmodel.pojos.OnlinePresenceComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Notifiability")
public class Notifiability extends OnlinePresenceComponent implements NotifiabilityBean{

	/**
	 * Agent can receive notifications from applications.
	 */
	public static Notifiability ALL_NOTIFICATIONS_PASS = new Notifiability();

	/**
	 * The acceptance of notifications from applications is somehow constrained
	 * (by using some rules or policies).
	 */
	public static Notifiability NOTIFICATIONS_CONSTRAINED = new Notifiability();

	/**
	 * Agent cannot receive any notification from applications.
	 */
	public static Notifiability NOTIFICATIONS_PROHIBITED = new Notifiability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		ALL_NOTIFICATIONS_PASS.setURI("http://online-presence.net/opo/ns#AllNotificationsPass");
		NOTIFICATIONS_CONSTRAINED.setURI("http://online-presence.net/opo/ns#NotificationsConstrained");
		NOTIFICATIONS_PROHIBITED.setURI("http://online-presence.net/opo/ns#NotificationsProhibited");
	}

}
