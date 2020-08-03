package com.example.couponsProject.facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.couponsProject.beans.Company;
import com.example.couponsProject.beans.Coupon;
import com.example.couponsProject.beans.Customer;
import com.example.couponsProject.exceptions.CompanyExistsException;
import com.example.couponsProject.exceptions.CompanyNotFoundException;
import com.example.couponsProject.exceptions.CustomerExistsException;
import com.example.couponsProject.exceptions.CustomerNotFoundException;
import com.example.couponsProject.exceptions.IllegalDataChangeException;


@Service
public class AdminFacade extends ClientFacade {

	public AdminFacade() {

	}
	
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws CompanyExistsException {
		if(compDB.findCompanyByNameOrEmail(company.getName(), company.getEmail()).isPresent()) { // new version. 
			throw new CompanyExistsException();
		}
		compDB.save(company);
	}

	public void updateCompany(Company company) throws IllegalDataChangeException {
		if (compDB.getOne((company.getId())).getName().equals(company.getName())) {
			compDB.save(company);
		} else {
			throw new IllegalDataChangeException("Company name cant be changed.");
		}

	}
	
	
//	repository.getOne(id) throws EntityNotFoundException similarly to IllegalArgumentException that is thrown by repository.findById(id) when the id is null.
	public void deleteCompany(int companyId) throws IllegalArgumentException, EntityNotFoundException {
		for (Coupon c : compDB.getOne(companyId).getCoupons()) {
			coupDB.deletePurchasesByCouponId(c.getId());
			coupDB.delete(c);
		}
		compDB.deleteById(companyId);

	}

	public ArrayList<Company> getAllCompanies() {
		return (ArrayList<Company>) compDB.findAll();
	}

	public Company getOneCompany(int companyId) throws CompanyNotFoundException {
		return compDB.findById(companyId).orElseThrow(() -> new CompanyNotFoundException());
	}

	public void addCustomer(Customer customer) throws CustomerExistsException {
		if (custDB.findByEmail((customer.getEmail())).isPresent()) {
			throw new CustomerExistsException();
		} else {
			custDB.save(customer);
		}
	}

	public void updateCustomer(Customer customer) throws CustomerNotFoundException {
		if (custDB.findById(customer.getId()).isPresent()) {
			custDB.save(customer);
		} else {
			throw new CustomerNotFoundException();
		}
	}
//	EntityNotFoundException is thrown by deletePurchaseByCustomerId. IllegalArgumentException is thrown by deleteById. default throws by repository methods. 
	public void deleteCustomer(int customerId) throws IllegalArgumentException, EntityNotFoundException {
		coupDB.deletePurcahseByCustomerId(customerId);
		custDB.deleteById(customerId);
	}

	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) custDB.findAll();
	}

	public Customer getOneCustomer(int customerId) throws CustomerNotFoundException {
		return custDB.findById(customerId).orElseThrow(CustomerNotFoundException::new);
	}

	public List<Coupon> getAllCoupons() {
		return coupDB.findAll();
	}

}
