package net.onlinepresence.opos.tapestry.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

@Path("/onlinePresences")
public class OnlinePresencesResource {
	
	@GET
	@Produces("text/plain")
	public String getAllDomains() {
		return "works";
	}

	@GET
	@Path("{id}")
//	@Produces("application/json")
	@Produces("text/plain")
	public String getOnlinePresence(@PathParam("id") String uri) {
		System.out.println("//////////////////////////////////// URI: "+uri);
		
		// TODO: introduce interface for OnlinePresenceService and inject via
		// spring its implementation
		OnlinePresenceService ops = new OnlinePresenceService();
		OnlinePresence op = null;
		try {
			op = ops.getLastOnlinePresence(uri);
			System.out.println("op: "+op);
			if (op == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return op.getUri().toString();
	}
}