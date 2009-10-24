/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: 24.10.2009.
* @version: 0.1
*/
package presenceComponents;

import java.net.URI;

import presence.PresenceClass;
import presenceProperties.PresenceProperty;
import presenceProperties.StringProperty;
import presenceProperties.URIProperty;
import presenceProperties.specificProperties.StatusMessageProperty;

/**
 * @author Nikola Milikic
 *
 */
public class StatusMessage extends PresenceClass{
	 
	/**
	 * Plain constructor.
	 */
	public StatusMessage(){}
	
	/**
	 * Constructor that receives URI of the custom message.
	 * @param uri
	 */
	public StatusMessage(URI uri) {
		setURI(uri);
	}
	
	public StatusMessage(String stringURI){
		this(URI.create(stringURI));
	}
	
	/**
	 * Adds new component to custom message's property list with the name 'name' and content 'content'.
	 * @param name
	 * @param content
	 */
	public void addComponent(String name, String content) {
		addProperty(new StatusMessageProperty(name, content));
	}
	
	/**
	 * Adds new component to custom message's property list with the name 'name' and the 'uri' as the URI of the property.
	 * @param name
	 * @param uri
	 */
	public void addComponent(String name, URI uri) {
		addComponent(new URIProperty(name, uri));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void addComponent(T component) {
		PresenceProperty pp = (PresenceProperty) component;
		addProperty(pp);
	}
}
