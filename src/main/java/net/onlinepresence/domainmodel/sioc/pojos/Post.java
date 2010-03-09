package net.onlinepresence.domainmodel.sioc.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.foaf.pojos.Document;
import net.onlinepresence.domainmodel.sioc.interfaces.PostBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("Post")
public class Post extends Item implements PostBean {

	private PostBean replyOf;
	private DocumentBean primaryTopicOf;
	
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
	public DocumentBean getPrimaryTopicOf() {
		return primaryTopicOf;
	}

	public void setPrimaryTopicOf(DocumentBean primaryTopicOf) {
		this.primaryTopicOf = primaryTopicOf;
	}
	
	public void setPrimaryTopicOf(String primaryTopicOf) {
		if(primaryTopicOf != null)
			setPrimaryTopicOf(new Document(primaryTopicOf));
	}

}
