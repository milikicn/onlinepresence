package net.onlinepresence.opos.service.crud.impl;


import net.onlinepresence.opos.core.persistance.PersistenceManager;
import net.onlinepresence.opos.service.crud.Command;

import org.hibernate.Session;


public class WriteCommand<Type> implements Command<Boolean> {
	
	private Type toSave;
	private PersistenceManager<Session> manager;

	public WriteCommand(Type toSave) {
		this.toSave = toSave;
	}

	public WriteCommand() {
	}

	
	public Boolean execute() {
	
       manager.write(toSave);
       return true;
	}

	public Type getToSave() {
		return toSave;
	}

	public void setToSave(Type toSave) {
		this.toSave = toSave;
	}

	public PersistenceManager<Session> getManager() {
		return manager;
	}

	public void setManager(PersistenceManager<Session> manager) {
		this.manager = manager;
	}

}
