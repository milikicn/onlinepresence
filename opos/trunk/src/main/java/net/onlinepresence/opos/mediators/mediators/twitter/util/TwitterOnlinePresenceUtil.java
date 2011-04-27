package net.onlinepresence.opos.mediators.mediators.twitter.util;

import java.net.URI;

import twitter4j.auth.AccessToken;

import net.onlinepresence.ontmodel.foaf.Agent;
import net.onlinepresence.ontmodel.foaf.Image;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.ontmodel.sioc.Item;
import net.onlinepresence.ontmodel.sioc.Post;
import net.onlinepresence.opos.domain.Membership;

public class TwitterOnlinePresenceUtil {

	public static OnlinePresence updateWithTwitterRelatedData(
			OnlinePresence oldOP, OnlinePresence newOP) {
		OnlinePresence updatedOP = oldOP;

		Agent newAgent = newOP.getAgent();
		if (newAgent != null) {
			System.out.println("Setting setHomepage from " + updatedOP.getAgent().getHomepage() + " to " + newAgent.getHomepage());
			updatedOP.getAgent().setHomepage(newAgent.getHomepage());
			System.out.println("Setting setName from " + updatedOP.getAgent().getName() + " to " + newAgent.getName());
			updatedOP.getAgent().setName(newAgent.getName());
		}

		Image newAvatar = newOP.getAvatar();
		if (newAvatar != null){
			System.out.println("Setting setAvatar from " + updatedOP.getAvatar() + " to " + newAvatar);
			updatedOP.setAvatar(newAvatar);
		}

		SpatialThing newLocation = newOP.getLocation();
		if (newLocation != null){
			System.out.println("Setting setLocation from " + updatedOP.getLocation() + " to " + newLocation);
			updatedOP.setLocation(newLocation);
		}

		Item newStatusMessage = newOP.getStatusMessage();
		if (newStatusMessage != null) {
			if (newStatusMessage instanceof Post) {
				System.out.println("newStatusMessage instanceof Post");
				Post newPost = (Post) newStatusMessage;

				System.out.println(1);
				Post newInReplyOfPost = newPost.getReplyOf();
				if (newInReplyOfPost != null){
					System.out.println("Setting setReplyOf from " + ((Post) updatedOP.getStatusMessage()).getReplyOf() + " to " + newInReplyOfPost);
					((Post) updatedOP.getStatusMessage()).setReplyOf(newInReplyOfPost);
				}

				System.out.println(2);
				URI newPrimaryTopicOf = newPost.getPrimaryTopicOf();
				if (newPrimaryTopicOf != null){
					System.out.println("Setting setPrimaryTopicOf from " + ((Post) updatedOP.getStatusMessage()).getPrimaryTopicOf() + " to " + newPrimaryTopicOf);
					((Post) updatedOP.getStatusMessage()).setPrimaryTopicOf(newPrimaryTopicOf);
				}
			}

			System.out.println("Setting setContent from " + updatedOP.getStatusMessage().getContent() + " to " + newStatusMessage.getContent());
			updatedOP.getStatusMessage().setContent(newStatusMessage.getContent());
		}

		return updatedOP;
	}

	public static boolean equalOnlinePresenceData(OnlinePresence oldOP,
			OnlinePresence newOP) {

		Agent newAgent = newOP.getAgent();
		Agent oldAgent = oldOP.getAgent();
		if (newAgent != null) {
			System.out.println("oldAgent.getHomepage " + oldAgent.getHomepage());
			System.out.println("newAvatar.getHomepage " + newAgent.getHomepage());
			if (oldAgent.getHomepage() != null && 
					newAgent.getHomepage() != null && 
					!oldAgent.getHomepage().equals(newAgent.getHomepage())) {
				return false;
			}
			System.out.println("oldAgent.getName " + oldAgent.getName());
			System.out.println("newAvatar.getName " + newAgent.getName());
			if (oldAgent.getName() != null && 
					newAgent.getName() != null && 
					!oldAgent.getName().equals(newAgent.getName())) {
				return false;
			}
		} else
			return false;

		Image newAvatar = newOP.getAvatar();
		System.out.println("newAvatar: " + newAvatar);
		Image oldAvatar = oldOP.getAvatar();
		System.out.println("oldAvatar: " + oldAvatar);
		if (oldAvatar != null && newAvatar != null) {
			if (!oldAvatar.equals(newAvatar))
				return false;
		}

		SpatialThing newLocation = newOP.getLocation();
		System.out.println("newLocation: " + newLocation);
		SpatialThing oldLocation = oldOP.getLocation();
		System.out.println("oldLocation: " + oldLocation);
		if (oldLocation != null && newLocation != null) {
			if (!oldLocation.equals(newLocation))
				return false;
		}

		Item newStatusMessage = newOP.getStatusMessage();
		System.out.println("newStatusMessage: " + newStatusMessage);
		Item oldStatusMessage = oldOP.getStatusMessage();
		System.out.println("oldStatusMessage: " + oldStatusMessage);
		if (newStatusMessage != null) {
//			if (newStatusMessage instanceof Post) {
//				System.out.println("newStatusMessage je tipa Post");
//				Post newPost = (Post) newStatusMessage;
//				Post oldPost = (Post) oldStatusMessage;
//
//				Post newInReplyOfPost = newPost.getReplyOf();
//				System.out.println("newInReplyOfPost " + newInReplyOfPost);
//				Post oldInReplyOfPost = oldPost.getReplyOf();
//				System.out.println("oldInReplyOfPost " + oldInReplyOfPost);
//				if(newInReplyOfPost == null){
//					if(oldInReplyOfPost != null){
//						System.out.println("Returning false for newInReplyOfPost == null i oldInReplyOfPost != null");
//						return false;
//					}
//				}else{
//					if(oldInReplyOfPost != null){
//						if(oldInReplyOfPost.getContent() != null &&
//								newInReplyOfPost.getContent() != null &&
//								!oldInReplyOfPost.getContent().equals(newInReplyOfPost.getContent())){
//							System.out.println("returning false for oldStatusMessage.getContent().equals(newStatusMessage.getContent())");
//							return false;
//						}
//						
//						URI newInReplyOfPostPrimaryTopicOf = newInReplyOfPost.getPrimaryTopicOf();
//						System.out.println("newInReplyOfPostPrimaryTopicOf " + newInReplyOfPostPrimaryTopicOf);
//						URI oldInReplyOfPostPrimaryTopicOf = oldInReplyOfPost.getPrimaryTopicOf();
//						System.out.println("oldInReplyOfPostPrimaryTopicOf " + oldInReplyOfPostPrimaryTopicOf);
//						if(newInReplyOfPostPrimaryTopicOf == null){
//							if(oldInReplyOfPostPrimaryTopicOf != null){
//								System.out.println("Returning false for newInReplyOfPostPrimaryTopicOf == null i oldInReplyOfPostPrimaryTopicOf != null");
//								return false;
//							}
//						}else{
//							if(oldInReplyOfPostPrimaryTopicOf != null){
//								if(!oldInReplyOfPostPrimaryTopicOf.equals(newInReplyOfPostPrimaryTopicOf)){
//									System.out.println("Returning false for !oldInReplyOfPostPrimaryTopicOf.equals(newInReplyOfPostPrimaryTopicOf)");
//									return false;
//								}
//							}
//						}
//					}
//				}
//				
//				URI newPrimaryTopicOf = newPost.getPrimaryTopicOf();
//				System.out.println("newPrimaryTopicOf " + newPrimaryTopicOf);
//				URI oldPrimaryTopicOf = oldPost.getPrimaryTopicOf();
//				System.out.println("oldPrimaryTopicOf " + oldPrimaryTopicOf);
//				if(newPrimaryTopicOf == null){
//					if(oldPrimaryTopicOf != null){
//						System.out.println("Returning false for newPrimaryTopicOf i oldPrimaryTopicOf != null");
//						return false;
//					}
//				}else{
//					if(oldPrimaryTopicOf != null){
//						if(!oldPrimaryTopicOf.equals(newPrimaryTopicOf)){
//							System.out.println("Returning false for !oldPrimaryTopicOf.equals(newPrimaryTopicOf");
//							return false;
//						}
//					}
//				}
//			} 
			System.out.println("oldStatusMessage.getContent() " + oldStatusMessage.getContent());
			System.out.println("newStatusMessage.getContent() " + newStatusMessage.getContent());
			if(oldStatusMessage.getContent() != null &&
					newStatusMessage.getContent() != null &&
					!oldStatusMessage.getContent().equals(newStatusMessage.getContent())){
				System.out.println("returning false for oldStatusMessage.getContent().equals(newStatusMessage.getContent())");
				return false;
			}
		}
		System.out.println("equalOnlinePresenceData returning true");
		return true;
	}
}
