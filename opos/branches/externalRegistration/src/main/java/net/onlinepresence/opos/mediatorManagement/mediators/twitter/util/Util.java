package net.onlinepresence.opos.mediatorManagement.mediators.twitter.util;

import twitter4j.auth.AccessToken;
import net.onlinepresence.opos.domain.Membership;

public class Util {

	public static AccessToken loadTwitterAccessToken(Membership memb) {
		if (memb != null 
				&& memb.getAccessToken() != null 
				&& memb.getSecretToken() != null) {
			return new AccessToken(memb.getAccessToken(), memb.getSecretToken());
		}
		return null;
	}
}
