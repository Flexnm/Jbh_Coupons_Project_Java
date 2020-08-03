package com.example.couponsProject.exceptions;

public class CompanyExistsException extends Exception {
	
	
	private static final long serialVersionUID = 1L;

	public CompanyExistsException() {
		super("Company name already exists in the database.");
	}

}
