package net.onlinepresence.domainmodel.opo.pojos.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.statuscomponents.VisibilityBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlineStatusComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Visibility")
public class Visibility extends OnlineStatusComponent implements VisibilityBean{

	/**
	 * The Agent's OnlineStatus is visible to other Agents.
	 */
	public static Visibility VISIBLE = new Visibility("http://online-presence.net/opo/ns#Visible");

	/**
	 * The Agent's OnlineStatus is not visible to other Agents.
	 */
	public static Visibility INVISIBLE = new Visibility("http://online-presence.net/opo/ns#Invisible");
	
	public Visibility(){
		super();
	}
	
	public Visibility(String uri) {
		super(uri);
	}
}
