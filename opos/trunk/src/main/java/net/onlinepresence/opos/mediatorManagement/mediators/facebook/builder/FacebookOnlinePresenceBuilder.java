package net.onlinepresence.opos.mediatorManagement.mediators.facebook.builder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.ActivityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.ContactabilityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.DisturbabilityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.VisibilityBean;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.services.spring.ResourceFactory;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.OnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.model.FacebookPost;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.model.FacebookUser;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.model.fql.FqlUser;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.model.fql.MultiqueryResults;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

public class FacebookOnlinePresenceBuilder implements OnlinePresenceBuilder {
	
	private Logger logger = Logger.getLogger(FacebookOnlinePresenceBuilder.class);
	
	private Membership userMembership;
	private FacebookClient facebookClient;

	public FacebookOnlinePresenceBuilder(Membership userMembership, FacebookClient facebookClient) {
		this.userMembership = userMembership;
		this.facebookClient = facebookClient;
	}

	public OnlinePresence build() throws OPOSException {
		logger.error("Building OnlinePresence instance on Facebook for user "+userMembership.getUsername());

		FacebookUser readUser = getUserDetails(facebookClient);
		
		ResourceFactory factory = new ResourceFactory();
		
		OnlinePresence facebookOnlinePresence = factory.createResource(OnlinePresence.class);
		
		// TODO: location
		// facebookOnlinePresence.setLocation(location);
		
		// TODO: agent - retrieve it from the repository
		// facebookOnlinePresence.setAgent(agent);
		
		//retrieving sioc:UserAccount instance
		OnlinePresenceService opService = new OnlinePresenceService();
		UserAccount userAccount = opService.getUserAccount(userMembership);
		facebookOnlinePresence.setUserAccount(userAccount);
		
		// status
		// TODO: should be persisted
		FacebookStatusBuilder fbStatusBuilder = new FacebookStatusBuilder(readUser.getLastPost());
		facebookOnlinePresence.setStatusMessage(fbStatusBuilder.buildStatus());
		
		// startTime
		facebookOnlinePresence.setStartTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
		
		// chat status
		// TODO: should be persisted
		OnlineStatus onlineStatus = factory.createResource(OnlineStatus.class);
		
		if (readUser.isOnline()) {
			onlineStatus.addStatusComponent(VisibilityBean.VISIBLE);
			onlineStatus.addStatusComponent(ActivityBean.ACTIVE);
			onlineStatus.addStatusComponent(DisturbabilityBean.AVAILABLE);
			onlineStatus.addStatusComponent(ContactabilityBean.FREELY_CONTACTABLE);
		}
		
		return facebookOnlinePresence;
	}
	
	public FacebookUser getUserDetails(FacebookClient facebookClient) {
		try {
			FacebookUser fbUser = new FacebookUser();
			String userId = facebookClient.fetchObject("me", User.class).getId();
			
			Map<String, String> queries = new HashMap<String, String>();
			queries.put("users", "SELECT uid, name, pic_big, online_presence, status, current_location FROM user WHERE uid="+userId);

			MultiqueryResults multiqueryResults =
				  facebookClient.executeMultiquery(queries, MultiqueryResults.class);
			
			if (multiqueryResults.users != null && !multiqueryResults.users.isEmpty()) {
				FqlUser fqlUserTmp = multiqueryResults.users.iterator().next();
				
				fbUser.setName(fqlUserTmp.getName());
				fbUser.setLocation(fqlUserTmp.getLocation());
				fbUser.setAvatarURL(fqlUserTmp.getAvatarURL());
				
				String onlinePresence = fqlUserTmp.getOnlinePresence();
				
				if (onlinePresence != null) {
					if (onlinePresence.equals("active"))
						fbUser.setOnline(true);
					else
						fbUser.setOnline(false);
				}
				
				// TODO: try to put this in multipart query to avoid spending one more call
				Connection<Post> posts = facebookClient.fetchConnection(
						userId+"/feed", 
						Post.class, 
						Parameter.with("limit", "1"));
				
				if (posts != null && !posts.getData().isEmpty()) {
					Post lastFbPost = posts.getData().get(0);
					fbUser.setLastPost(new FacebookPost(lastFbPost.getMessage(), lastFbPost.getUpdatedTime()));
				}
	
				return fbUser;
			} else
				// TODO: introduce custom exception
				throw new RuntimeException();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
