package com.example.couponsProject.exceptions;

public class CompanyNotFoundException extends Exception {

	private static final long serialVersionUID = 3;

	public CompanyNotFoundException() {
		super("Company was not found");
	}

}
