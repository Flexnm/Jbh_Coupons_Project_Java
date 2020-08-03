package com.example.couponsProject.exceptions;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 6;

	public CustomerNotFoundException() {
		super("Customer was not found");
	}

}
