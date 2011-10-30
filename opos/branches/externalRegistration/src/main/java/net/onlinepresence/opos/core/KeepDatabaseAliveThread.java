package net.onlinepresence.opos.core;

import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.DummyQueryService;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class KeepDatabaseAliveThread extends Thread {
	
	private Logger logger = Logger.getLogger(KeepDatabaseAliveThread.class);

	private ReadCommand<Application> reader;
	
	@SuppressWarnings("unchecked")
	public KeepDatabaseAliveThread() {
		ApplicationContext context = new ApplicationContextProviderSingleton().getContext();
		reader = (ReadCommand<Application>) context.getBean(ReadCommand.class.getName());
		reader.setClazz(Application.class);
	}
	
	@Override
	public void run() {
		while (true) {
			logger.debug("Executing dummy query for keeping the database connection alive.");
			
			reader.executeQuery("select * from APPLICATION LIMIT 1;");
			try {
				DummyQueryService.getInstance().performDummyQuery();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				sleep(4*60*60*1000);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
