package net.onlinepresence.opos.mediatorManagement.mediators.spark.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.core.util.TransferObject;
import net.onlinepresence.opos.mediatorManagement.mediators.spark.SparkMediator;

import java.net.Socket;

public class ReceivingThread extends Thread{

	private OnlinePresence onlinePresence;
	
	private SparkMediator mediator;
	
	public Socket socket;
	
	public ObjectOutputStream out;
	
	public  ObjectInputStream in;
	
	public ReceivingThread(Socket socket, SparkMediator mediator){
		this.socket = socket;
		this.mediator = mediator;
		
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			setName((String)in.readObject());
			mediator.add(this);
			
			start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		
		try {
			
			while (true){
				TransferObject to = (TransferObject) in.readObject();
				
				System.out.println("received OP");
				
				if(to.getCommand().equals("DISCONNECT")){
					System.out.println("Spark disonnected");
					mediator.remove(this);
					return;
				}
				
				this.onlinePresence = to.getOnlinePresence();
				
				if(onlinePresence != null){
					
//					FileOPOService foe = new FileOPOService("exportedTURTLE_received.rdf", "TURTLE");
//					foe.saveResourceToFile(onlinePresence);
					
					mediator.propagateOnlinePresence(onlinePresence);
					System.out.println("SPARK: prosledio OP");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} 
	}
	
	public void sendOnlinePresence(OnlinePresence op){
		TransferObject to = new TransferObject(null, op);
		try {
			out.writeObject(to);
		} catch (IOException e) {
			System.out.println("error sending opo");
		}
	}
}
