package com.example.couponsProject.exceptions;

public class CustomerExistsException extends Exception {

	private static final long serialVersionUID = 5;

	public CustomerExistsException() {
		super("Customer data already exists in the database.");
	}

}
