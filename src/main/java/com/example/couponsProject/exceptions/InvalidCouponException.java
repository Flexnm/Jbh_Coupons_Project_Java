package com.example.couponsProject.exceptions;

public class InvalidCouponException extends Exception {

	private static final long serialVersionUID = 8;

	public InvalidCouponException(boolean has, boolean out, boolean expired) {
		super("An invalid coupon was detected for the following reason(s):\n"
				+ "Customer attemped to purchase a duplicate coupon: " + has
				+ "\nCustomer attemped to purchase a coupon that is sold out: " + out
				+ "\nCustomer attempted to purchase an expired coupon: " + expired);
	}

}
