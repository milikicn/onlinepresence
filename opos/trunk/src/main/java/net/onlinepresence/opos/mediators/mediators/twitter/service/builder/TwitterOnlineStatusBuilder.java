package net.onlinepresence.opos.mediators.mediators.twitter.service.builder;

import net.onlinepresence.ontmodel.sioc.Post;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.TwitterOPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.wrappers.Twitter4jStatusWrapper;
import net.onlinepresence.services.spring.ResourceFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterOnlineStatusBuilder {
	
	private Twitter twitter;
	private User user;
	
	public TwitterOnlineStatusBuilder(Twitter twitter, User user){
		this.twitter = twitter;
		this.user = user;
	}

	public Post buildStatus() throws TwitterOPOSException {
		System.out.println("TwitterOnlineStatusBuilder: building status");
		
		ResourceFactory factory = new ResourceFactory();
		
		Post post = (Post) factory.createResource(Post.class);
		
		Twitter4jStatusWrapper statusWrapper = null;
		
		if(user.getStatus() == null)
			return post;
		try {
			statusWrapper = new Twitter4jStatusWrapper(twitter.showStatus(user.getStatus().getId()));
		} catch (TwitterException e) {
			throw new TwitterOPOSException("Can not get an instance of Status.");
		}

		post.setContent(statusWrapper.getText());
		System.out.println("TwitterOnlineStatusBuilder: post.setContent " + post.getContent());
		post.setPrimaryTopicOf(statusWrapper.getStatusURL(user.getScreenName()).toString());
		System.out.println("TwitterOnlineStatusBuilder: post.setPrimaryTopicOf " + post.getPrimaryTopicOf());
		
		if(statusWrapper.getStatusReplyOfStatusUrl() != null){
			Post inReplyPost = (Post) factory.createResource(Post.class);

			inReplyPost.setPrimaryTopicOf(statusWrapper.getStatusReplyOfStatusUrl().toString());
			System.out.println("TwitterOnlineStatusBuilder: inReplyPost.setPrimaryTopicOf " + inReplyPost.getPrimaryTopicOf());
			
			post.setReplyOf(inReplyPost);
		}

		return post;
	}

}
