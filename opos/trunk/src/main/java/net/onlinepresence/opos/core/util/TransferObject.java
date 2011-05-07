package net.onlinepresence.opos.core.util;

import java.io.Serializable;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;

public class TransferObject implements Serializable{

	private static final long serialVersionUID = 5843170342224727517L;

	private String command;
	
	private OnlinePresence op;

	public TransferObject(String command, OnlinePresence op) {
		this.command = command;
		this.op = op;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public OnlinePresence getOnlinePresence() {
		return op;
	}

	public void setOnlinePresence(OnlinePresence op) {
		this.op = op;
	}
	
	
}
