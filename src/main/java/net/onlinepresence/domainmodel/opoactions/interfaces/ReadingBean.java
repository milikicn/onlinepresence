package net.onlinepresence.domainmodel.opoactions.interfaces;

import net.onlinepresence.domainmodel.opo.interfaces.ActionBean;

/**
 * An action of reading (e.g., reading a book).
 *
 * @param <T>
 */
public interface ReadingBean<T> extends ActionBean {

	T getReadingMaterial();
	void setReadingMaterial(T readingMaterial);
}
