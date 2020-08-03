package com.example.couponsProject.loginManager;

import com.example.couponsProject.facade.ClientFacade;

public class Session {
	
	private ClientFacade facade;
	private long lastLoginTime;
	
	public Session(ClientFacade facade, long lastLoginTime) {
		setClientFacade(facade);
		setLastLoginTime(lastLoginTime);
	}
	
	public ClientFacade getClientFacade() {
		return facade;
	}
	
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	
	public void setClientFacade(ClientFacade facade) {
		this.facade = facade;
	}
	
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
