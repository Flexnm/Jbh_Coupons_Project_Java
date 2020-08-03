package com.example.couponsProject.exceptions;

public class ClientTimeoutException extends Exception {

	
	private static final long serialVersionUID = 1;
	
	public ClientTimeoutException() {
		super("Timeout");
	}

}
