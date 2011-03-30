package net.onlinepresence.opos.mediators.mediators.twitter.util;

import twitter4j.auth.AccessToken;
import net.onlinepresence.opos.domain.Membership;

public class Util {

	public static AccessToken loadAccessToken(Membership membership) {
		String token = membership.getAccessToken();
		String tokenSecret = membership.getAccessToken();
		return new AccessToken(token, tokenSecret);
	}
}
