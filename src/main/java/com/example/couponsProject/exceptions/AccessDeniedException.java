package com.example.couponsProject.exceptions;

public class AccessDeniedException extends Exception {

	private static final long serialVersionUID = 1;

	public AccessDeniedException() {
		super("Incorrect email or password");
	}

}
