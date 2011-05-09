package net.onlinepresence.opos.mediatorManagement.util;

import java.net.URI;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.foaf.Agent;
import net.onlinepresence.jopo.ontmodel.foaf.Image;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.Findability;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.Notifiability;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Activity;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Disturbability;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Visibility;
import net.onlinepresence.jopo.ontmodel.sioc.Item;

public class OnlinePresenceEquality {
	
	private static Logger logger = Logger.getLogger(OnlinePresenceEquality.class);

	public static boolean equalOnlinePresenceData(OnlinePresence oldOP,
			OnlinePresence newOP) {

		boolean toReturn =  
			equalAgents(oldOP.getAgent(), newOP.getAgent()) &&
			equalAvatars(oldOP.getAvatar(), newOP.getAvatar()) &&
			equalLocation(oldOP.getLocation(), newOP.getLocation()) &&
			equalStatusMessages(oldOP.getStatusMessage(), newOP.getStatusMessage()) &&
			equalNotifiability(oldOP.retrieveNotifiability(), newOP.retrieveNotifiability()) &&
			// saving findability
			equalFindability(oldOP.retrieveFindability(), newOP.retrieveFindability()) &&
			// saving onlineStatus
			equalOnlineStatus(oldOP.retrieveOnlineStatus(), newOP.retrieveOnlineStatus());
		
		logger.debug("equalOnlinePresenceData returning "+toReturn);
		return toReturn;
	}

	private static boolean equalAgents(Agent oldAgent, Agent newAgent) {
		logger.debug("oldAgent " + oldAgent);
		logger.debug("newAvatar " + newAgent);
		
		if (oldAgent == null && newAgent == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldAgent == null || newAgent == null)
			return false;
		
		return equalHomepages(oldAgent.getHomepage(), newAgent.getHomepage())
			&& equalNames(oldAgent.getName(), newAgent.getName());
	}

	private static boolean equalHomepages(URI oldHomepage, URI newHomepage) {
		logger.debug("oldAgent.getHomepage " + oldHomepage);
		logger.debug("newAvatar.getHomepage " + newHomepage);
		
		if (oldHomepage == null && newHomepage == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldHomepage == null || newHomepage == null)
			return false;
		
		if (oldHomepage != null && newHomepage != null )
			if (!oldHomepage.equals(newHomepage)) {
				return false;
		}
		return true;
	}

	private static boolean equalNames(String oldName, String newName) {
		logger.debug("oldAgent.getName " + oldName);
		logger.debug("newAvatar.getName " + newName);
		
		return equalStringValues(oldName, newName);
	}

	private static boolean equalAvatars(Image oldAvatar, Image newAvatar) {
		logger.debug("oldAvatar: " + oldAvatar);
		logger.debug("newAvatar: " + newAvatar);
		
		if (oldAvatar == null && newAvatar == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldAvatar == null || newAvatar == null)
			return false;
		
		if (!oldAvatar.equals(newAvatar))
			return false;

		return true;
	}

	private static boolean equalLocation(SpatialThing oldLocation, SpatialThing newLocation) {
		logger.debug("oldLocation: " + oldLocation);
		logger.debug("newLocation: " + newLocation);
		
		if (oldLocation == null && newLocation == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldLocation == null || newLocation == null)
			return false;
		
		if (!oldLocation.equals(newLocation)){
			String oldLatitude = oldLocation.getLatitude();
			String newLatitude = newLocation.getLatitude();
			
			logger.debug("oldLatitude: " + oldLatitude);
			logger.debug("newLatitude: " + newLatitude);
			
			String oldLongitude = oldLocation.getLongitude();
			String newLongitude = newLocation.getLongitude();
			
			logger.debug("oldLongitude: " + oldLongitude);
			logger.debug("newLongitude: " + newLongitude);
			
			return equalStringValues(oldLatitude, newLatitude)
				&& equalStringValues(oldLongitude, newLongitude);
		}

		return true;
	}

	private static boolean equalStringValues(String oldLatitude,
			String newLatitude) {
		if (oldLatitude == null && newLatitude == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldLatitude == null || newLatitude == null)
			return false;
		
		if (!oldLatitude.equals(newLatitude))
			return false;
		
		return true;
	}

	private static boolean equalStatusMessages(Item oldStatusMessage, Item newStatusMessage) {
		logger.debug("oldStatusMessage: " + oldStatusMessage);
		logger.debug("newStatusMessage: " + newStatusMessage);
		
		if (oldStatusMessage == null && newStatusMessage == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldStatusMessage == null || newStatusMessage == null)
			return false;
		
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
		
		if (!oldStatusMessage.equals(newStatusMessage)) {
			String oldContent = oldStatusMessage.getContent();
			String newContent = newStatusMessage.getContent();
			
			logger.debug("oldStatusMessage.getContent() " + oldContent);
			logger.debug("newStatusMessage.getContent() " + newContent);
			
			return equalStringValues(oldContent, newContent);
		}
		
		return true;
	}

	private static boolean equalNotifiability(Notifiability oldNotifiability,
			Notifiability newNotifiability) {
		
		logger.debug("oldNotifiability: " + oldNotifiability);
		logger.debug("newNotifiability: " + newNotifiability);
		
		if (oldNotifiability == null && newNotifiability == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldNotifiability == null || newNotifiability == null)
			return false;
		
		if (!oldNotifiability.equals(newNotifiability))
			return false;
		
		return true;
	}

	private static boolean equalFindability(Findability oldFindability,
			Findability newFindability) {
		
		logger.debug("oldFindability: " + oldFindability);
		logger.debug("newFindability: " + newFindability);
		
		if (oldFindability == null && newFindability == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldFindability == null || newFindability == null)
			return false;
		
		if (!oldFindability.equals(newFindability))
			return false;
		
		return true;
	}

	private static boolean equalOnlineStatus(OnlineStatus oldOnlineStatus,
			OnlineStatus newOnlineStatus) {
		
		logger.debug("oldOnlineStatus: " + oldOnlineStatus);
		logger.debug("newOnlineStatus: " + newOnlineStatus);
		
		if (oldOnlineStatus == null && newOnlineStatus == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldOnlineStatus == null || newOnlineStatus == null)
			return false;
		
		if (!oldOnlineStatus.equals(newOnlineStatus))
			return false;
		
		return equalActivity(oldOnlineStatus.retrieveActivity(), newOnlineStatus.retrieveActivity())
			&& equalContactability(oldOnlineStatus.retrieveContactability(), newOnlineStatus.retrieveContactability())
			&& equalDisturbability(oldOnlineStatus.retrieveDisturbability(), newOnlineStatus.retrieveDisturbability())
			&& equalVisibility(oldOnlineStatus.retrieveVisibility(), newOnlineStatus.retrieveVisibility());
	}

	private static boolean equalActivity(Activity oldActivity, Activity newActivity) {
		logger.debug("oldActivity: " + oldActivity);
		logger.debug("newActivity: " + newActivity);
		
		if (oldActivity == null && newActivity == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldActivity == null || newActivity == null)
			return false;
		
		if (!oldActivity.equals(newActivity))
			return false;
		
		return true;
	}

	private static boolean equalContactability(Contactability oldContactability, Contactability newContactability) {
		logger.debug("oldContactability: " + oldContactability);
		logger.debug("newOnlineStatus: " + newContactability);
		
		if (oldContactability == null && newContactability == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldContactability == null || newContactability == null)
			return false;
		
		if (!oldContactability.equals(newContactability))
			return false;
		
		return true;
	}

	private static boolean equalDisturbability(Disturbability oldDisturbability, Disturbability newDisturbability) {
		logger.debug("oldDisturbability: " + oldDisturbability);
		logger.debug("newDisturbability: " + newDisturbability);
		
		if (oldDisturbability == null && newDisturbability == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldDisturbability == null || newDisturbability == null)
			return false;
		
		if (!oldDisturbability.equals(newDisturbability))
			return false;
		
		return true;
	}

	private static boolean equalVisibility(Visibility oldVisibility, Visibility newVisibility) {
		logger.debug("oldVisibility: " + oldVisibility);
		logger.debug("newVisibility: " + newVisibility);
		
		if (oldVisibility == null && newVisibility == null)
			return true;
		
		// if both are not null, then one of them must not be nut
		if (oldVisibility == null || newVisibility == null)
			return false;
		
		if (!oldVisibility.equals(newVisibility))
			return false;
		
		return true;
	}
}
