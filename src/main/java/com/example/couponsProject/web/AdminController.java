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

import com.example.couponsProject.beans.Company;
import com.example.couponsProject.beans.Customer;
import com.example.couponsProject.exceptions.CompanyExistsException;
import com.example.couponsProject.exceptions.CompanyNotFoundException;
import com.example.couponsProject.exceptions.CustomerExistsException;
import com.example.couponsProject.exceptions.CustomerNotFoundException;
import com.example.couponsProject.exceptions.IllegalDataChangeException;
import com.example.couponsProject.facade.AdminFacade;
import com.example.couponsProject.loginManager.Session;

@RestController
@RequestMapping("admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {

	@Autowired
	private Map<String, Session> sessionMap;

	public AdminController() {
	}
	
	/**
	 * Method that pings the admin login to trigger aspect method on admin component load.
	 * @param token
	 * @return
	 */
	@PostMapping("{token}")
	public ResponseEntity<?> pingAdmin(@PathVariable String token){
		return ResponseEntity.ok(null);
	}
	

	@PostMapping("{token}/company")
	public ResponseEntity<?> addCompany(@PathVariable String token, @RequestBody Company company) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			admin.addCompany(company);
			return ResponseEntity.ok(company);
		} catch (CompanyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("{token}/customer")
	public ResponseEntity<?> addCustmer(@PathVariable String token, @RequestBody Customer customer) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			admin.addCustomer(customer);
			return ResponseEntity.ok(customer);
		} catch (CustomerExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{token}/company")
	public ResponseEntity<?> updateCompany(@PathVariable String token, @RequestBody Company company) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			admin.updateCompany(company);
			return ResponseEntity.ok(company);
		} catch (IllegalDataChangeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("{token}/customer")
	public ResponseEntity<?> updateCustomer(@PathVariable String token, @RequestBody Customer customer) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			admin.updateCustomer(customer);
		} catch (CustomerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping("{token}/company/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable String token, @PathVariable int companyId) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			admin.deleteCompany(companyId);
			return ResponseEntity.ok("comp del");
		} catch (IllegalArgumentException | EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company not found");
		}
	}

	@DeleteMapping("{token}/customer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String token, @PathVariable int customerId) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			admin.deleteCustomer(customerId);
			return ResponseEntity.ok("cust del");
		} catch (IllegalArgumentException | EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found");
		}
	}

	@GetMapping("{token}/company/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable String token, @PathVariable int companyId) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			return ResponseEntity.ok(admin.getOneCompany(companyId));
		} catch (CompanyNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("{token}/customer/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable String token, @PathVariable int customerId) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		try {
			return ResponseEntity.ok(admin.getOneCustomer(customerId));
		} catch (CustomerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("{token}/company")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(admin.getAllCompanies());
	}

	@GetMapping("{token}/customer")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(admin.getAllCustomers());

	}

	@GetMapping("{token}/coupons")
	public ResponseEntity<?> getAllCoupons(@PathVariable String token) {
		AdminFacade admin = (AdminFacade) sessionMap.get(token).getClientFacade();
		return ResponseEntity.ok(admin.getAllCoupons());
	}

}
