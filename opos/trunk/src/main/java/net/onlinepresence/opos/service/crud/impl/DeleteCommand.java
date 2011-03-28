package net.onlinepresence.opos.service.crud.impl;

import net.onlinepresence.opos.core.persistance.PersistenceManager;
import net.onlinepresence.opos.service.crud.Command;

import org.hibernate.Session;



public class DeleteCommand<Type> implements Command<Boolean> {
	
	
	private PersistenceManager<Session> manager;
	private Type toDelete;	

	public PersistenceManager<Session> getManager() {
		return manager;
	}

	public void setManager(PersistenceManager<Session> manager) {
		this.manager = manager;
	}

	public DeleteCommand(Type input) {
		this.toDelete = input;
	}

	public DeleteCommand() {
	}

	
	public Boolean execute(){
		manager.delete(toDelete);
		return true;
	}

	public Type getForgotten() {
		return toDelete;
	}

	public void setToDelete(Type toDelete) {
		this.toDelete = toDelete;
	}
	
	

}
