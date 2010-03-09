package net.onlinepresence.domainmodel.sioc.pojos;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.sioc.interfaces.PostBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("Post")
public class Post extends Item implements PostBean {

	private PostBean replyOf;
	private URI primaryTopicOf;
	
	public Post() {
		super();
	}

	public Post(String uri) {
		super(uri);
	}

	@RdfProperty("http://rdfs.org/sioc/ns#reply_of")
	public PostBean getReplyOf() {
		return replyOf;
	}

	public void setReplyOf(PostBean replyOf) {
		if(replyOf != null){
			replyOf.setURI(replyOf.getUri().replaceFirst("Post", "ReplyPost"));
			this.replyOf = replyOf;
		}
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/isPrimaryTopicOf")
	public URI getPrimaryTopicOf() {
		return primaryTopicOf;
	}

	public void setPrimaryTopicOf(URI primaryTopicOf) {
		this.primaryTopicOf = primaryTopicOf;
	}
	
	public void setPrimaryTopicOf(String primaryTopicOf) {
		if(primaryTopicOf != null)
			try {
				setPrimaryTopicOf(new URI(primaryTopicOf));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

}
