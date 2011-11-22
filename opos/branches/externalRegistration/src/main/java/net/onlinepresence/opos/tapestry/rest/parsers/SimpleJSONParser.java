package net.onlinepresence.opos.tapestry.rest.parsers;

import java.util.Collection;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.general.Resource;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Activity;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Disturbability;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Visibility;
import net.onlinepresence.jopo.ontmodel.sioc.Item;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.opos.util.DateUtil;

public class SimpleJSONParser implements OnlinePresenceJSONParser {

	public JSONObject exportToJSON(OnlinePresence onlinePresence, Person person) throws JSONException {
		
		JSONObject onlinePresenceJSON = new JSONObject();
		
		onlinePresenceJSON.put("presenceuri", onlinePresence.getUri());
		
		if (onlinePresence.getStartTime() != null)
			onlinePresenceJSON.put("timepublished", onlinePresence.getStartTime());
		
		UserAccount userAccount = onlinePresence.getUserAccount();
		if (userAccount != null) {
			onlinePresenceJSON.put("application", userAccount.getAccountServiceHomepage());
			onlinePresenceJSON.put("accountname", userAccount.getAccountName());
		}
		
		if (onlinePresence.getAvatar() != null)
			onlinePresenceJSON.put("avatar", onlinePresence.getAvatar());
		
		if (onlinePresence.getLocation() != null) {
			SpatialThing location = onlinePresence.getLocation();
			
			// has lat and long set
			if (location.getLatitude() != null) {
				JSONObject locationJSON = new JSONObject();
				
				locationJSON.put("latitude", location.getLatitude());
				locationJSON.put("longitude", location.getLongitude());
				onlinePresenceJSON.put("location", locationJSON);
			} else
				onlinePresenceJSON.put("location", location);
		}
		
		Item statusMessage = onlinePresence.getStatusMessage();
		if (statusMessage != null) {
			JSONObject customMessageJSON = new JSONObject();
			customMessageJSON.put("messageuri", statusMessage.getUri());
			customMessageJSON.put("content", statusMessage.getContent());
			
			onlinePresenceJSON.put("customMessage", customMessageJSON);
		}
		
		OnlineStatus onlineStatus = onlinePresence.retrieveOnlineStatus();
			
		if (onlineStatus != null) {
			JSONObject onlineStatusJSON = new JSONObject();
			
			Activity activity = onlineStatus.retrieveActivity();
			if (activity != null)
				onlineStatusJSON.put("activity", cutOffAfterHashAndToLowerCase(activity));
			
			Contactability contactability = onlineStatus.retrieveContactability();
			if (contactability != null)
				onlineStatusJSON.put("contactability", cutOffAfterHashAndToLowerCase(contactability));
			
			Disturbability disturbability = onlineStatus.retrieveDisturbability();
			if (disturbability != null)
				onlineStatusJSON.put("disturbability", cutOffAfterHashAndToLowerCase(disturbability));
			
			Visibility visibility = onlineStatus.retrieveVisibility();
			if (visibility != null)
				onlineStatusJSON.put("visibility", cutOffAfterHashAndToLowerCase(visibility));
			
				
			onlinePresenceJSON.put("onlinestatus", onlineStatusJSON);
		}
	
		return onlinePresenceJSON;
	}
	
	private <T extends Resource> String cutOffAfterHashAndToLowerCase (T res) {
		String uri = res.getUri().toString();
		
		String afterHash = uri.substring(uri.indexOf("#") + 1);
		
		return afterHash.toLowerCase();
	}

	public String exportToJSON(Collection<OnlinePresence> onlinePresences, Person person) throws JSONException {
		JSONObject lastOnlinePresences = new JSONObject();
		
		lastOnlinePresences.put("user", person.getUri());
		lastOnlinePresences.put("timepublished", DateUtil.getPreetyDate(new Date(), DateUtil.TIME_DATE));
		
		JSONArray onlinePresencesJSON = new JSONArray();
		
		for (OnlinePresence onlinePresence : onlinePresences) {
			onlinePresencesJSON.put(exportToJSON(onlinePresence, person));
		}
		
		lastOnlinePresences.put("onlinepresences", onlinePresencesJSON);

		return lastOnlinePresences.toString(4);
	}

}