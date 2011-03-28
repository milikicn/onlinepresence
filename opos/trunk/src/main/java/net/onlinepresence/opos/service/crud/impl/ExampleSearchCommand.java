package net.onlinepresence.opos.service.crud.impl;

import java.util.List;


import net.onlinepresence.opos.core.persistance.PersistenceManager;
import net.onlinepresence.opos.service.crud.Command;

import org.hibernate.Session;


public class ExampleSearchCommand<Type> implements Command<List<Type>> {
	
	private PersistenceManager<Session> manager;
	private Type t;
	private Class<Type> clazz;

	
	public List<Type> execute() {

		return manager.get(clazz, t);
	}

	public Class<Type> getClazz() {
		return clazz;
	}

	public void setClazz(Class<Type> clazz) {
		this.clazz = clazz;
	}

	
	public PersistenceManager<Session> getManager() {
		return manager;
	}

	
	public void setManager(PersistenceManager<Session> facade) {
		this.manager= facade;
		
	}

	public void setT(Type t) {
		this.t = t;
	}

	public Type getT() {
		return this.t;
	}

}
