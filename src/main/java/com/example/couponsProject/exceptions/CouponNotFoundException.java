package com.example.couponsProject.exceptions;

public class CouponNotFoundException extends Exception {

	private static final long serialVersionUID = 3;

	public CouponNotFoundException() {
		super("Coupon not found");
	}
}
