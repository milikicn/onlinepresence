package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.pojos.Action;
import net.onlinepresence.domainmodel.opoactions.interfaces.WatchingBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Watching")
public class Watching<T> extends Action implements WatchingBean<T> {

	private T watching;

	@RdfProperty("http://online-presence.net/opo-actions/ns#watching")
	public T getWatching() {
		return watching;
	}

	public void setWatching(T watching) {
		if(watching != null)
			this.watching = watching;
	}
}
