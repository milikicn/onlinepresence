package net.onlinepresence.opos.mediatorManagement.mediators.facebook.builder;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.sioc.Post;
import net.onlinepresence.jopo.services.spring.ResourceFactory;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.model.FacebookPost;

public class FacebookStatusBuilder {
	
	private Logger logger = Logger.getLogger(FacebookStatusBuilder.class);
	
	private FacebookPost facebookPost;
	
	public FacebookStatusBuilder(FacebookPost facebookPost){
		this.facebookPost = facebookPost;
	}

	public Post buildStatus() throws OPOSException {
		logger.debug("building status");
		
		ResourceFactory factory = new ResourceFactory();
		
		Post post = (Post) factory.createResource(Post.class);
		
		post.setContent(facebookPost.getTextPost());
		post.setDateCreated(facebookPost.getTimePost());
		
		return post;
	}

}
