package net.onlinepresence.domainmodel.opoactions.interfaces;

import net.onlinepresence.domainmodel.opo.interfaces.ActionBean;

/**
 * The action of listening to music.
 *
 * @param <T>
 */
public interface ListeningBean<T> extends ActionBean {

	T getListeningTo();
	void setListeningTo(T listeningTo);
}
