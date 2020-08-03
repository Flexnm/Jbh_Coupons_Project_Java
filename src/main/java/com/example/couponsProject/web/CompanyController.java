package com.example.couponsProject.web;

import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.couponsProject.beans.Category;
import com.example.couponsProject.beans.Coupon;
import com.example.couponsProject.exceptions.CompanyNotFoundException;
import com.example.couponsProject.exceptions.CouponNotFoundException;
import com.example.couponsProject.exceptions.IllegalDataChangeException;
import com.example.couponsProject.exceptions.InvalidNewCouponDataException;
import com.example.couponsProject.facade.CompanyFacade;
import com.example.couponsProject.loginManager.Session;

@RestController
@RequestMapping("company")
@CrossOrigin("http://localhost:4200")
public class CompanyController {

	@Autowired
	private Map<String, Session> sessionMap;

	@PostMapping("{token}/coupon")
	public ResponseEntity<?> addCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		try {
			company.addCoupon(coupon);
			return ResponseEntity.ok(coupon);
		} catch (IllegalDataChangeException | InvalidNewCouponDataException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("{token}/coupon")
	public ResponseEntity<?> updateCoupon(@PathVariable String token, @RequestBody Coupon coupon) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		try {
			company.updateCoupon(coupon);
			return ResponseEntity.ok(coupon);
		} catch (IllegalDataChangeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@DeleteMapping("{token}/coupon/{couponId}")
	public ResponseEntity<?> deleteCoupon(@PathVariable String token, @PathVariable int couponId) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		try {
			company.deleteCoupon(couponId);
			return ResponseEntity.ok("coup del");
		} catch (IllegalDataChangeException | EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@GetMapping("{token}/coupons")
	public ResponseEntity<?> getCompanyCoupons(@PathVariable String token) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(company.getCompanyCoupons());

	}

	@GetMapping("{token}/category/{category}")
	public ResponseEntity<?> getCompanyCoupons(@PathVariable String token, @PathVariable Category category) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(company.getCompanyCoupons(category));

	}

	@GetMapping("{token}/price/{maxPrice}")
	public ResponseEntity<?> getCompanyCoupons(@PathVariable String token, @PathVariable double maxPrice) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(company.getCompanyCoupons(maxPrice));
	}

	@GetMapping("{token}/coupon/{couponId}")
	public ResponseEntity<?> getOneCoupon(@PathVariable String token, @PathVariable int couponId) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		try {
			return ResponseEntity.ok(company.getOneCoupon(couponId));
		} catch (CouponNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@GetMapping("{token}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable String token) {
		CompanyFacade company = (CompanyFacade) sessionMap.get(token).getClientFacade();
		try {
			return ResponseEntity.ok(company.getCompanyDetails());
		} catch (CompanyNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
