package net.onlinepresence.opos.mediators.mediators.spark;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.spark.threads.ReceivingThread;

public class SparkMediator extends Thread implements Mediator{

	public LinkedList<ReceivingThread> list;
	
	private MediatorManager mm;
	
	public SparkMediator(MediatorManager mm){
		this.list = new LinkedList<ReceivingThread>();
		this.mm = mm;
		start();
	}
	
	@Override
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(1213);
			
			while (true){
				Socket socket = ss.accept();
				new ReceivingThread(socket, this);
//				System.out.println("One Spark connected");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(ReceivingThread rc){
		list.add(rc);
//		System.out.println("added to list");
	}
	
	public void remove(ReceivingThread rc){
		list.remove(rc);
//		System.out.println("removed from list");
	}
	
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership){
		String username = membership.getUsername();
		
		for (ReceivingThread rc : list) {
			if(rc.getName().equals(username)){
//				System.out.println("sending to spark: " + username);
				rc.sendOnlinePresence(op);
				return;
			}
		}
	}
	

	public void propagateOnlinePresence(OnlinePresence onlinePresence){
		mm.propagateOnlinePresence(onlinePresence);
	}
	
	public ApplicationNames getMediatorName(){
		return ApplicationNames.SPARK;
	}

}
