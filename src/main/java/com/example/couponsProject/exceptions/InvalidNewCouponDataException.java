package com.example.couponsProject.exceptions;

public class InvalidNewCouponDataException extends Exception{
	
	private static final long serialVersionUID = 1;
	
	public InvalidNewCouponDataException(String error) {
		super("The new coupon entry contains data that is not allowed in the database: " + error);
	}

}
