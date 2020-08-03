package com.example.couponsProject.exceptions;

public class LoginFailedException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LoginFailedException() {
		super("Login failed");
	}

}
