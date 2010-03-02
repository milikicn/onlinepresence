package presenceComponents;

import java.net.URI;

import presence.PresenceClass;
import presenceProperties.ObjectProperty;
import presenceProperties.PresenceProperty;
import presenceProperties.URIProperty;
import presenceProperties.specificProperties.StatusMessageProperty;

public class Post extends PresenceClass{
	{
		setNameSpace("http://rdfs.org/sioc/ns#");
	}
	public static String SIOC_CONTENT = "content";
	public static String FOAF_IS_PRIMARY_TOPIC_OF = "isPrimaryTopicOf";
	
	/**
	 * Plain constructor.
	 */
	public Post(){}
	
	/**
	 * Constructor that receives URI of the custom message.
	 * @param uri
	 */
	public Post(URI uri) {
		setURI(uri);
	}
	
	public Post(String stringURI){
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
	 * Adds new component to custom message's property list with the name 'name' and content 'content' with namespace given.
	 * @param name
	 * @param content
	 */
	public void addComponent(String name, String content, String namespace) {
		addProperty(new StatusMessageProperty(name, content), namespace);
	}
	
	/**
	 * Adds new component to custom message's property list with the name 'name' and the 'uri' as the URI of the property.
	 * @param name
	 * @param uri
	 */
	public void addComponent(String name, URI uri) {
		addProperty(new URIProperty(name, uri));
	}
	
	/**
	 * Adds new component to custom message's property list with the name 'name' and the 'uri' as the URI of the property with namespace given.
	 * @param name
	 * @param uri
	 */
	public void addComponent(String name, URI uri, String namespace) {
		addProperty(new URIProperty(name, uri), namespace);
	}
	
	public void setReplyPost(Post replyPost) {
		addProperty(new ObjectProperty("reply_of", replyPost), "http://rdfs.org/sioc/ns#");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> void addComponent(T component) {
		PresenceProperty pp = (PresenceProperty) component;
		addProperty(pp);
	}
}

