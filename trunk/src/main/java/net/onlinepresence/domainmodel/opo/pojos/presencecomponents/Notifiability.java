package net.onlinepresence.domainmodel.opo.pojos.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.presencecomponents.NotifiabilityBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresenceComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Notifiability")
public class Notifiability extends OnlinePresenceComponent implements NotifiabilityBean{

	/**
	 * Agent can receive notifications from applications.
	 */
	public static Notifiability ALL_NOTIFICATIONS_PASS = new Notifiability("http://online-presence.net/opo/ns#AllNotificationsPass");

	/**
	 * The acceptance of notifications from applications is somehow constrained
	 * (by using some rules or policies).
	 */
	public static Notifiability NOTIFICATIONS_CONSTRAINED = new Notifiability("http://online-presence.net/opo/ns#NotificationsConstrained");

	/**
	 * Agent cannot receive any notification from applications.
	 */
	public static Notifiability NOTIFICATIONS_PROHIBITED = new Notifiability("http://online-presence.net/opo/ns#NotificationsProhibited");

	public Notifiability() {
		super();
	}
	
	public Notifiability(String uri) {
		super(uri);
	}
	
}
