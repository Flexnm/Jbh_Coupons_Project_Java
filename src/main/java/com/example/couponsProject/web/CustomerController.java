package com.example.couponsProject.web;

import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couponsProject.beans.Category;
import com.example.couponsProject.exceptions.InvalidCouponException;
import com.example.couponsProject.facade.CustomerFacade;
import com.example.couponsProject.loginManager.Session;

@RestController
@RequestMapping("customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {

	@Autowired
	private Map<String, Session> sessionMap;
	

	@PostMapping("{token}/purchase")
	public ResponseEntity<?> purchaseCoupon(@PathVariable String token, @RequestBody int couponId) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		try {
			customer.purchaseCoupon(couponId);
			return ResponseEntity.ok(couponId);
		} catch (InvalidCouponException | EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("{token}/purchase")
	public ResponseEntity<?> getAllPurchasableCoupons(@PathVariable String token) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return  ResponseEntity.ok(customer.getAllCouponsForPurchase());
	}
	
	@GetMapping("{token}/purchase/c-{category}")
	public ResponseEntity<?> getAllPurchasableCoupons(@PathVariable String token, @PathVariable Category category) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return  ResponseEntity.ok(customer.getAllCouponsForPurchase(category));
	}
	
	@GetMapping("{token}/purchase/p-{maxPrice}")
	public ResponseEntity<?> getAllPurchasableCoupons(@PathVariable String token, @PathVariable double maxPrice) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return  ResponseEntity.ok(customer.getAllCouponsForPurchase(maxPrice));
	}

	@GetMapping("{token}/coupons")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable String token) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return  ResponseEntity.ok(customer.getCustomerCoupons());
	}

	@GetMapping("{token}/category/{category}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable String token, @PathVariable Category category) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return  ResponseEntity.ok(customer.getCustomerCoupons(category));
	}

	@GetMapping("{token}/price/{maxPrice}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable String token, @PathVariable double maxPrice) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return  ResponseEntity.ok(customer.getCustomerCoupons(maxPrice));
	}

	@GetMapping("{token}")
	public ResponseEntity<?> getCustomerDetails(@PathVariable String token) {
		CustomerFacade customer = (CustomerFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(customer.getCustomerDetails());
	}

}
