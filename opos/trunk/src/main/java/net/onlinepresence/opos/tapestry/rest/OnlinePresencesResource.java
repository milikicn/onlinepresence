package net.onlinepresence.opos.tapestry.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;
import net.onlinepresence.opos.tapestry.rest.util.JSONFormater;

@Path("/onlinePresences")
public class OnlinePresencesResource {

	@GET
	@Produces("text/plain")
	public String getAllDomains() {
		return "works";
	}

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
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		JSONFormater jsonFormater = new JSONFormater();
		return jsonFormater.exportToJSON(op);
	}

	@GET
	@Path("proba")
	@Produces("application/json")
	public Customer proba() {
		Customer c = new Customer();
		c.setId(42);
		c.setFirstName("Bill");
		c.setLastName("Burke");
		return c;
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(outputStream, c);
	}

	public class Customer {
		private int id;
		private String firstName;
		private String lastName;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	}
}
