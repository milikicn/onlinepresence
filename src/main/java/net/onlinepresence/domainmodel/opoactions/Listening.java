package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.pojos.Action;
import net.onlinepresence.domainmodel.opoactions.interfaces.ListeningBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Listening")
public class Listening<T> extends Action implements ListeningBean<T> {

	private T listeningTo;

	public Listening() {

	}
	
	public Listening(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#listeningTo")
	public T getListeningTo() {
		return listeningTo;
	}

	public void setListeningTo(T listeningTo) {
		if(listeningTo != null)
			this.listeningTo = listeningTo;
	}

}
