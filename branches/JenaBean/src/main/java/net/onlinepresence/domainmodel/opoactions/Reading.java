package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.pojos.Action;
import net.onlinepresence.domainmodel.opoactions.interfaces.ReadingBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Reading")
public class Reading<T> extends Action implements ReadingBean<T> {

	private T readingMaterial;

	public Reading() {
		super();
	}
	
	public Reading(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#readingMaterial")
	public T getReadingMaterial() {
		return readingMaterial;
	}

	public void setReadingMaterial(T readingMaterial) {
		if(readingMaterial != null)
			this.readingMaterial = readingMaterial;
	}
}
