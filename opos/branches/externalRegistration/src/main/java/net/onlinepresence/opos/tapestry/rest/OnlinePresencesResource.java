package net.onlinepresence.opos.tapestry.rest;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.domain.ApplicationName;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.mediatorManagement.LatestPresenceCache;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;
import net.onlinepresence.opos.tapestry.rest.parsers.OnlinePresenceJSONParser;
import net.onlinepresence.opos.tapestry.rest.parsers.ParserTypes;
import net.onlinepresence.opos.tapestry.rest.parsers.RDFJSONFormater;
import net.onlinepresence.opos.tapestry.rest.parsers.SimpleJSONParser;

import org.apache.log4j.Logger;

@Path("/onlinePresences")
public class OnlinePresencesResource {
	
	private Logger logger = Logger.getLogger(this.getClass());
	ApplicationContextProviderSingleton s = new ApplicationContextProviderSingleton();
	ApplicationManager applicationManager = (ApplicationManager) s.getContext().getBean(ApplicationManager.class.getName());

	
//	@GET
//	@Produces("text/plain")
//	public String getAllDomains() {
//		return "works";
//	}

	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public String getOnlinePresence(@PathParam("id") String uri) {
		// TODO: introduce interface for OnlinePresenceService and inject via
		// spring its implementation
		OnlinePresenceService ops = new OnlinePresenceService();
		OnlinePresence op = null;
		try {
			op = ops.getLastOnlinePresence(uri);
			System.out.println("op: " + op);
			if (op == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		RDFJSONFormater jsonFormater = new RDFJSONFormater();
		return jsonFormater.exportToJSON(op, null);
	}
	
	
	@GET
	@Produces("application/json")
	public String getOnlinePresence(
			@QueryParam("type") String type,
			@QueryParam("service") String serviceName,
			@QueryParam("username") String username) {
		
		if (type == null || serviceName == null || username == null)
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		
		OnlinePresenceService ops = new OnlinePresenceService();
		
		String applicationWebAddress = ApplicationName.getApplicationHomepage(serviceName.toUpperCase());
		
		// retieving Person instance having the account with given properties
		String userUri = ops.getUriOfUserHoldingAccount(applicationWebAddress, username);
		
		if (userUri == null)
			throw new WebApplicationException(Response.Status.NO_CONTENT);
		
		Collection<OnlinePresence> lastOnlinePresences = null;
		
		// check the cache
		Collection<OnlinePresence> opFromCache = LatestPresenceCache.getInstance().getLatestOnlinePresences(userUri);
		
		if (opFromCache != null) {
			lastOnlinePresences = opFromCache;
		} else {
			// retrieving OnlinePresence instances on all UserAccounts of a person
			try {
				lastOnlinePresences = ops.getLastOnlinePresencesOnUserAccounts(userUri);
				
				if (lastOnlinePresences == null) {
					throw new WebApplicationException(Response.Status.NOT_FOUND);
				} else if (lastOnlinePresences.isEmpty()) {
					throw new WebApplicationException(Response.Status.NO_CONTENT);
				}
				
				// put into cache
				LatestPresenceCache.getInstance().addOnlinePresences(lastOnlinePresences);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
		}
		
		// Based on the type parameter, chosing the parser to parse the OnlinePresences.
		// Default parser is RDFjJSONFormater.
		OnlinePresenceJSONParser jsonParser = null;
		ParserTypes parserType = ParserTypes.valueOf(type.toUpperCase());
		
		switch (parserType) {
			case RDF:
				jsonParser = new RDFJSONFormater();
				break;
			case SIMPLE:
				jsonParser = new SimpleJSONParser();
				break;
			default:
				jsonParser = new RDFJSONFormater();
				break;
		}
		
		String output;
		try {
			output = jsonParser.exportToJSON(lastOnlinePresences, userUri).toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return output;
	}

}
