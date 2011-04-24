package net.onlinepresence.opos.service.crud.impl;

import net.onlinepresence.opos.core.persistance.PersistenceManager;
import net.onlinepresence.opos.service.crud.Command;

import org.hibernate.Session;

public class UpdateCommand<Type> implements Command<Boolean> {
	
	private Type toUpdate;

	public Type getToUpdate() {
		return toUpdate;
	}

	public void setToUpdate(Type toUpdate) {
		this.toUpdate = toUpdate;
	}

	private PersistenceManager<Session> manager;
	
	public PersistenceManager<Session> getManager() {
		return manager;
	}

	public void setManager(PersistenceManager<Session> manager) {
		this.manager = manager;
	}

	
	public Boolean execute(){
		manager.update(toUpdate);
		return true;
	}
}
