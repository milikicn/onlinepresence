package net.onlinepresence.opos.api;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import net.onlinepresence.opos.domain.User;

import java.util.List;

@Path("/user")
public class UserManagementResource {
//	private PersistenceService persistenceService;

	public UserManagementResource() {
//		this.persistenceService = persistenceService;
	}

	@GET
	@Produces("application/json")
	public List<User> getAllDomains() {
		return null;
//		return (persistenceService.getInstances(User.class));
	}

	@POST
	@Consumes("application/json")
	public Response post(User domainObject) {
//		persistenceService.save(domainObject);
		return Response.ok().build();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public User getDomainObject(@PathParam("id") Long id) {
//		User domainObject = persistenceService.getInstance(User.class, id);
//		if (domainObject == null) {
//			throw new WebApplicationException(Response.Status.NOT_FOUND);
//		}
		return null;
	}
}
