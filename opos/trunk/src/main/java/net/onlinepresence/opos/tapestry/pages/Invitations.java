package net.onlinepresence.opos.tapestry.pages;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Key;
import net.onlinepresence.opos.domain.beans.LoggedAdminBean;
import net.onlinepresence.opos.domain.service.KeyManager;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Invitations {
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.KeyManager")
	private KeyManager invitations;
	
	@SuppressWarnings("unused")
	@SessionState
	private LoggedAdminBean loggedAdmin;
	private boolean loggedAdminExists;
	
	@Property
	private Key invitation;

	Object onActivate() {
		if (!loggedAdminExists)
			return Index.class;		
		return null;
	}
	
	public List<Key> getInvitations(){
		List<Key> k = invitations.getKeys();
		List<Key> list = new LinkedList<Key>();
		for (Key key : k) {
			if(key.getKey() == null)
				list.add(key);
		}
		return list;
	}
	
	public String getEmail() {
        return invitation.getEmail();
    }
	
	@OnEvent(component="sendInvite")
	public Object sendInvite(String email){
		Key key = invitations.getKey(email);
		String code = UUID.randomUUID().toString();
		key.setKey(code.substring(0, 20));
		invitations.updateKey(key);
		
		//TODO send invitation mail to 'email' with access code
		
		return null;
	}
	
	@OnEvent(component = "signout")
    public Object signout(){
    	loggedAdmin = null;
    	return Index.class;
    }
}
