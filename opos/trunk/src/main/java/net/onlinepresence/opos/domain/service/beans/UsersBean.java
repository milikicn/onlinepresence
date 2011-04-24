package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.Users;
import net.onlinepresence.opos.service.crud.impl.DeleteCommand;
import net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;
import net.onlinepresence.opos.service.crud.impl.UpdateCommand;
import net.onlinepresence.opos.service.crud.impl.WriteCommand;

import org.apache.tapestry5.ioc.annotations.Inject;

public class UsersBean implements Users {
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ReadCommand")
	private ReadCommand<User> reader;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.UpdateCommand")
	private UpdateCommand<User> updater;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.WriteCommand")
	private WriteCommand<User> writer;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<User> delete;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<Membership> membershipDeleter;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand")
	private ExampleSearchCommand<User> searcher;
	
	
	//private LinkedList<Person> persons = new LinkedList<Person>();
	
	public UsersBean() {
	}

	/* (non-Javadoc)
	 * @see opos.service.Users#getAll()
	 */
	public List<User> getUsers() {
		reader.setClazz(User.class);
		return reader.execute();
	}
	
	public void addUser(User u){
		writer.setToSave(u);
		writer.execute();
	}

	public void removeUser(User u){
		delete.setToDelete(u);
		delete.execute();
	}

	/* (non-Javadoc)
	 * @see opos.service.Persons#getPerson(java.lang.String)
	 */
	public User findUser(String usernameOrEmail) {
		
		List<User> users = getUsers();
		
		if(usernameOrEmail.contains("@")){
			for (User u : users) {
				if(u.getEmail().equals(usernameOrEmail))
					return u;						
			}
		}
		else{
			for (User u : users) {
				if(u.getUsername().equals(usernameOrEmail))
					return u;						
			}
		}
		
		return null;
	}
	
	public void update(User u){
		updater.setToUpdate(u);
		updater.execute();
	}
	
	public void deleteApplicationMemberhsip(Membership mem){
		membershipDeleter.setToDelete(mem);
		membershipDeleter.execute();
	}
	
	public boolean existsUser(String username){
		User u = findUser(username);
		if(u != null)
			return true;
		return false;
	}
	
	/*
	 * for a given username and app of membership, finds its user and list all his memberships
	 */
	@SuppressWarnings("unchecked")
	public List<Membership> getAllMemberships(String username, String app){
		return (List<Membership>) reader.getEagerMemberships("from MembershipBean where user=(select user.username from MembershipBean where application='" + 
				app +"'" + "and username='" + username +"')");
	}

	
	public ReadCommand<User> getReader() {
		return reader;
	}

	public void setReader(ReadCommand<User> reader) {
		this.reader = reader;
	}

	public UpdateCommand<User> getUpdater() {
		return updater;
	}

	public void setUpdater(UpdateCommand<User> updater) {
		this.updater = updater;
	}

	public WriteCommand<User> getWriter() {
		return writer;
	}

	public void setWriter(WriteCommand<User> writer) {
		this.writer = writer;
	}

	public DeleteCommand<User> getDelete() {
		return delete;
	}

	public void setDelete(DeleteCommand<User> delete) {
		this.delete = delete;
	}

	public ExampleSearchCommand<User> getSearcher() {
		return searcher;
	}

	public void setSearcher(ExampleSearchCommand<User> searcher) {
		this.searcher = searcher;
	}

	public DeleteCommand<Membership> getMembershipDeleter() {
		return membershipDeleter;
	}

	public void setMembershipDeleter(DeleteCommand<Membership> membershipDeleter) {
		this.membershipDeleter = membershipDeleter;
	}


}
