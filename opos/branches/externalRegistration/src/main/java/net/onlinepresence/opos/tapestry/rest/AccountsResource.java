package net.onlinepresence.opos.tapestry.rest;

import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.domain.service.UserManager;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/accounts")
public class AccountsResource {
	
	private Logger logger = Logger.getLogger(this.getClass());
	ApplicationContextProviderSingleton s = new ApplicationContextProviderSingleton();
	UserManager applicationManager = (UserManager) s.getContext().getBean(UserManager.class.getName());

	@GET
	@Produces("text/plain")
	public String getAllDomains() {
		return "works";
	}
	
	/**
	 * Service accepts input in format:
	 * [
	 * 		{
	 * 			service: "moodle",
	 * 			username: "username1"
	 * 		},
	 * 		{
	 * 			service: "twitter",
	 * 			username: "username2"
	 * 		},
	 * 		...
	 * 		{
	 * 			service: "moodle",
	 * 			username: "usernameN"
	 * 		},
	 * ]
	 * 
	 * where first parameter is a name of the service for which the caller knows an username.
	 * 
	 * @param json
	 * @return
	 */
	@POST
	@Produces("application/json")
	public String getOnlinePresence(@QueryParam("service") String serviceName, String json) {
		logger.debug("Requesting acount details on a service '"+serviceName+"' for users in '"+json+"'");
		
		JsonParser parser = new JsonParser();
		JsonArray usersArray = (JsonArray) parser.parse(json);
		
		Iterator<JsonElement> iterator = usersArray.iterator();
		
		JsonArray result = new JsonArray();
		
		while (iterator.hasNext()) {
			JsonObject userJson = (JsonObject) iterator.next();
			
			String knownAppName = userJson.get("service").getAsString();
			String knownUsername = userJson.get("username").getAsString();
			
			String username = applicationManager.getUsernameOnApplication(serviceName, knownAppName, knownUsername);
			
			JsonObject resultUserJson = new JsonObject();
			resultUserJson.addProperty("service", knownAppName);
			resultUserJson.addProperty("username", knownUsername);
			
			JsonObject resultAccountJson = new JsonObject();
			resultAccountJson.addProperty("service", serviceName);
			resultAccountJson.addProperty("username", username);
			
			resultUserJson.add("requestedAccount", resultAccountJson);

			result.add(resultUserJson);
		}
		
		logger.debug("Returning data '"+result.toString()+"'");
		return result.toString();
	}

}
