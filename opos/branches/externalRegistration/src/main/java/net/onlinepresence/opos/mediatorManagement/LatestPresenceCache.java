package net.onlinepresence.opos.mediatorManagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationName;

public class LatestPresenceCache {

	private Map<String, Map<String, OnlinePresence>> cache;

	private static LatestPresenceCache INSTANCE;
	
	private LatestPresenceCache () {
		cache = new HashMap<String, Map<String, OnlinePresence>>();
	}
	
	public static LatestPresenceCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LatestPresenceCache();
		}
		return INSTANCE;
	}
	
	public void addOnlinePresences(Collection<OnlinePresence> onlinePresences) {
		if (onlinePresences != null) {
			for (OnlinePresence onlinePresence : onlinePresences) {
				addNewOnlinePresence(onlinePresence);
			}
		}
	}
	
	public void addNewOnlinePresence(OnlinePresence onlinePresence) {
		String userUri = onlinePresence.getAgent().getUri().toString();
		String appHomepage = onlinePresence.getUserAccount().getAccountServiceHomepage().toString();
		
		addNewOnlinePresence(userUri, appHomepage, onlinePresence);
	}
	
	public void addNewOnlinePresence(String userUri, String appHomepage, OnlinePresence presence) {
		Map<String, OnlinePresence> userPresences;
		
		if (cache.containsKey(userUri)) {
			userPresences = cache.get(userUri);
		} else {
			userPresences = new HashMap<String, OnlinePresence>();
		}
		
		// add or override
		userPresences.put(appHomepage, presence);
		
		cache.put(userUri, userPresences);
	}
	
	public Collection<OnlinePresence> getLatestOnlinePresences(String userUri) {
		if (cache.containsKey(userUri)) {
			return new ArrayList<OnlinePresence>(cache.get(userUri).values());
		}
		return null;
	}
	public OnlinePresence getLatestOnlinePresence(String userUri, String appName) {
		if (cache.containsKey(userUri)) {
			Map<String, OnlinePresence> userPresenceCache = cache.get(userUri);
			
			if (userPresenceCache != null) {
				return userPresenceCache.get(ApplicationName.getApplicationHomepage(appName));
			}
		}
		return null;
	}
}
