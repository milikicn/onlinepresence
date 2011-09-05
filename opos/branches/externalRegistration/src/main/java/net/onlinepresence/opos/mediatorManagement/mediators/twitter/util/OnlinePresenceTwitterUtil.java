package net.onlinepresence.opos.mediatorManagement.mediators.twitter.util;

import java.net.URI;

import net.onlinepresence.jopo.ontmodel.foaf.Agent;
import net.onlinepresence.jopo.ontmodel.foaf.Image;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.sioc.Item;
import net.onlinepresence.jopo.ontmodel.sioc.Post;

import org.apache.log4j.Logger;

public class OnlinePresenceTwitterUtil {
	
	private static Logger logger = Logger.getLogger(OnlinePresenceTwitterUtil.class);

	public static OnlinePresence updateWithTwitterRelatedData(
			OnlinePresence oldOP, OnlinePresence newOP) {
		OnlinePresence updatedOP = oldOP;

		Agent newAgent = newOP.getAgent();
		if (newAgent != null) {
			logger.debug("Setting setHomepage from " + updatedOP.getAgent().getHomepage() + " to " + newAgent.getHomepage());
			updatedOP.getAgent().setHomepage(newAgent.getHomepage());
			logger.debug("Setting setName from " + updatedOP.getAgent().getName() + " to " + newAgent.getName());
			updatedOP.getAgent().setName(newAgent.getName());
		}

		Image newAvatar = newOP.getAvatar();
		if (newAvatar != null){
			logger.debug("Setting setAvatar from " + updatedOP.getAvatar() + " to " + newAvatar);
			updatedOP.setAvatar(newAvatar);
		}

		SpatialThing newLocation = newOP.getLocation();
		if (newLocation != null){
			logger.debug("Setting setLocation from " + updatedOP.getLocation() + " to " + newLocation);
			updatedOP.setLocation(newLocation);
		}

		Item newStatusMessage = newOP.getStatusMessage();
		if (newStatusMessage != null) {
			if (newStatusMessage instanceof Post) {
				logger.debug("newStatusMessage instanceof Post");
				Post newPost = (Post) newStatusMessage;

				Post newInReplyOfPost = newPost.getReplyOf();
				if (newInReplyOfPost != null){
					logger.debug("Setting setReplyOf from " + ((Post) updatedOP.getStatusMessage()).getReplyOf() + " to " + newInReplyOfPost);
					((Post) updatedOP.getStatusMessage()).setReplyOf(newInReplyOfPost);
				}

				URI newPrimaryTopicOf = newPost.getPrimaryTopicOf();
				if (newPrimaryTopicOf != null){
					logger.debug("Setting setPrimaryTopicOf from " + ((Post) updatedOP.getStatusMessage()).getPrimaryTopicOf() + " to " + newPrimaryTopicOf);
					((Post) updatedOP.getStatusMessage()).setPrimaryTopicOf(newPrimaryTopicOf);
				}
			}

			logger.debug("Setting setContent from " + updatedOP.getStatusMessage().getContent() + " to " + newStatusMessage.getContent());
			updatedOP.getStatusMessage().setContent(newStatusMessage.getContent());
		}

		return updatedOP;
	}

}
