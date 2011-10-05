package net.onlinepresence.opos.core;

import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.DummyQueryService;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class KeepDatabaseAliveThread extends Thread {
	
	private Logger logger = Logger.getLogger(KeepDatabaseAliveThread.class);

	@SuppressWarnings("rawtypes")
	private ReadCommand reader;
	
	@SuppressWarnings("rawtypes")
	public KeepDatabaseAliveThread() {
		ApplicationContext context = new ApplicationContextProviderSingleton().getContext();
		reader = (ReadCommand) context.getBean(ReadCommand.class.getName());
	}
	
	@Override
	public void run() {
		while (true) {
			reader.executeQuery("from Application");
			try {
				DummyQueryService.getInstance().performDummyQuery();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			logger.debug("Executing dummy query for keeping the database connection alive.");
			try {
				sleep(4*60*60*1000);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
