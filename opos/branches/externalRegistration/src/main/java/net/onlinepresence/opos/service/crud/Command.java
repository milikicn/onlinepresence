package net.onlinepresence.opos.service.crud;


import net.onlinepresence.opos.core.persistance.PersistenceManager;

import org.hibernate.Session;



public interface Command <T>{
	
	 T execute();
	 
	 PersistenceManager<Session> getManager();
	 
	 void setManager(PersistenceManager<Session> facade);

}
