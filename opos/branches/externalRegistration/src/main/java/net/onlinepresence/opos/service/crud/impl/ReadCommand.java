package net.onlinepresence.opos.service.crud.impl;

import java.util.List;

import net.onlinepresence.opos.core.persistance.PersistenceManager;
import net.onlinepresence.opos.service.crud.Command;

import org.hibernate.Session;

												
public class ReadCommand<Type> implements Command<List<Type>>{
	
	private Class<Type> clazz;
	private PersistenceManager<Session> manager;

	
	public List<Type> execute() {
        return manager.get(clazz);
	}
	
	@SuppressWarnings("rawtypes")
	public List executeQuery(String query){
		return manager.runSQLQuery(query);
	}
	
//	@SuppressWarnings("rawtypes")
//	public List getEagerMemberships(String query){
//		return manager.getEagerMemberships(query);
//	}

	public Class<Type> getClazz() {
		return clazz;
	}

	public void setClazz(Class<Type> clazz) {
		this.clazz = clazz;
	}

	public PersistenceManager<Session> getManager() {
		return manager;
	}

	public void setManager(PersistenceManager<Session> manager) {
		this.manager = manager;
	}

}
