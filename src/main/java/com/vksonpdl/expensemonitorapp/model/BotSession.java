package com.vksonpdl.expensemonitorapp.model;

import java.io.Serializable;
import java.util.Date;

public class BotSession implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String action;
	private Long createdTimeStamp;
	private boolean expired;
	private boolean actionCompleted;
	
	
	public BotSession() {
		this.createdTimeStamp = new Date().getTime();
	}
}
