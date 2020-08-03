package com.example.couponsProject.exceptions;

public class IllegalDataChangeException extends Exception {

	private static final long serialVersionUID = 7;

	public IllegalDataChangeException(String specifics) {
		super("An attempt to alter the database with invalid data was detected.\n" + specifics);
	}

}
