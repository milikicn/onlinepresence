package net.onlinepresence.domainmodel.opoactions.interfaces;

import net.onlinepresence.domainmodel.opo.interfaces.ActionBean;

/**
 * An action of watching something (e.g., a movie).
 *
 * @param <T>
 */
public interface WatchingBean<T> extends ActionBean {

	T getWatching();
	void setWatching(T watching);
}
