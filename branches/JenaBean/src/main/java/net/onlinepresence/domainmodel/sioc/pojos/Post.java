package net.onlinepresence.domainmodel.sioc.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.sioc.interfaces.PostBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("Post")
public class Post extends Item implements PostBean {

	private PostBean replyOf;
	private DocumentBean primaryTopicOf;
	
	public Post() {

	}

	public Post(String uri) {
		super(uri);
	}

	@RdfProperty("http://rdfs.org/sioc/ns#reply_of")
	public PostBean getReplyOf() {
		return replyOf;
	}

	public void setReplyOf(PostBean replyOf) {
		if(replyOf != null)
			this.replyOf = replyOf;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/isPrimaryTopicOf")
	public DocumentBean getPrimaryTopicOf() {
		return primaryTopicOf;
	}

	public void setPrimaryTopicOf(DocumentBean primaryTopicOf) {
		this.primaryTopicOf = primaryTopicOf;
	}

}
