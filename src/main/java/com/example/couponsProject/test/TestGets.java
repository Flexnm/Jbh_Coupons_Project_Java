package com.example.couponsProject.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.couponsProject.beans.Company;
import com.example.couponsProject.beans.Coupon;
import com.example.couponsProject.beans.Customer;
import com.example.couponsProject.exceptions.CompanyExistsException;
import com.example.couponsProject.exceptions.CompanyNotFoundException;
import com.example.couponsProject.exceptions.CouponNotFoundException;
import com.example.couponsProject.exceptions.CustomerExistsException;
import com.example.couponsProject.exceptions.CustomerNotFoundException;
import com.example.couponsProject.exceptions.IllegalDataChangeException;
import com.example.couponsProject.exceptions.InvalidCouponException;
import com.example.couponsProject.exceptions.LoginFailedException;
import com.example.couponsProject.facade.AdminFacade;
import com.example.couponsProject.facade.CompanyFacade;
import com.example.couponsProject.facade.CustomerFacade;
import com.example.couponsProject.loginManager.ClientType;
import com.example.couponsProject.loginManager.LoginManager;


@Component
public class TestGets {

	@Autowired
	private LoginManager log;

	public void testGet() throws CustomerNotFoundException, CompanyExistsException, CustomerExistsException,
			CompanyNotFoundException, IllegalDataChangeException, InvalidCouponException, CouponNotFoundException, LoginFailedException {

		AdminFacade admin = (AdminFacade) log.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		CompanyFacade comp1 = (CompanyFacade) log.login("a@a.com", "aa1234", ClientType.COMPANY);
		CompanyFacade comp2 = (CompanyFacade) log.login("b@b.com", "bb1234", ClientType.COMPANY);
		CompanyFacade comp3 = (CompanyFacade) log.login("c@c.com", "cc1234", ClientType.COMPANY);
		CustomerFacade cust1 = (CustomerFacade) log.login("aaemail", "a1234", ClientType.CUSTOMER);
		CustomerFacade cust2 = (CustomerFacade) log.login("bbemail", "b1234", ClientType.CUSTOMER);
		CustomerFacade cust3 = (CustomerFacade) log.login("ccemail", "c1234", ClientType.CUSTOMER);

		CompanyFacade[] comps = { comp1, comp2, comp3 };
		CustomerFacade[] custs = {cust1, cust2, cust3};

		System.out.println("\n\n\n\n===========Admin==========\n\n\n\n");
		System.out.println(admin.getAllCompanies() + "\n\n\n\n");
		System.out.println(admin.getAllCoupons() + "\n\n\n\n");
		System.out.println(admin.getAllCustomers() + "\n\n\n\n");
		for (Company comp : admin.getAllCompanies()) {
			System.out.println("\n\n" + admin.getOneCompany(comp.getId()) + "\n\n\n\n");
		}

		for (Customer cust : admin.getAllCustomers()) {
			System.out.println("\n\n" + admin.getOneCustomer(cust.getId()) + "\n\n\n\n");
		}

		System.out.println("\n\n\n\n============Company==============\n\n\n\n");

		for (CompanyFacade comp : comps) {
			System.out.println(comp.getCompanyDetails().getId());
//			for (Category category : Category.values()) {
//				System.out.println("\n\n" + comp.getCompanyCoupons(category) + "\n\n\n\n");
//			}
		System.out.println(comp.getCompanyCoupons() + "\n\n\n\n");
			for (Coupon coup : comp.getCompanyCoupons()) {
				System.out.println("\n\n" + comp.getOneCoupon(coup.getId()) + "\n\n\n\n");
			}
		}

		System.out.println("\n\n\n\n=========Customer==========\n\n\n\n");
		for (CustomerFacade cust : custs) {
//			System.out.println(cust.getCustomerCoupons());
			System.out.println("\n" + cust.getCustomerDetails().getId());
			System.out.println(cust.getCustomerCoupons() + "\n\n\n\n");
//			System.out.println(cust.getCustomerCoupons(5000000));
//			for (Category category : Category.values()) {
//				System.out.println(category);
//				System.out.println("\n\n" + cust.getCustomerCoupons(category) + "\n\n\n\n");
//			}
		}

	}
}
