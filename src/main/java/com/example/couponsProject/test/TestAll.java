package com.example.couponsProject.test;

//import java.sql.Date;
//import java.util.Calendar;
//
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.example.couponsProject.beans.Category;
//import com.example.couponsProject.beans.Company;
//import com.example.couponsProject.beans.Coupon;
//import com.example.couponsProject.beans.Customer;
//import com.example.couponsProject.exceptions.CompanyExistsException;
//import com.example.couponsProject.exceptions.CompanyNotFoundException;
//import com.example.couponsProject.exceptions.CouponNotFoundException;
//import com.example.couponsProject.exceptions.CustomerExistsException;
//import com.example.couponsProject.exceptions.CustomerNotFoundException;
//import com.example.couponsProject.exceptions.IllegalDataChangeException;
//import com.example.couponsProject.exceptions.InvalidCouponException;
//import com.example.couponsProject.exceptions.InvalidNewCouponDataException;
import com.example.couponsProject.exceptions.LoginFailedException;
//import com.example.couponsProject.facade.AdminFacade;
//import com.example.couponsProject.facade.CompanyFacade;
//import com.example.couponsProject.facade.CustomerFacade;
//import com.example.couponsProject.loginManager.ClientType;
//import com.example.couponsProject.loginManager.LoginManager;

@Component
public class TestAll {
	/*

	@Autowired
	private LoginManager log;

	public void test() throws CustomerNotFoundException, CompanyExistsException, CustomerExistsException,
			CompanyNotFoundException, IllegalDataChangeException, InvalidCouponException, CouponNotFoundException, LoginFailedException, InvalidNewCouponDataException {

		AdminFacade admin = (AdminFacade) log.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		for (Company item : admin.getAllCompanies()) {
			admin.deleteCompany(item.getId());
		}
		
		for (Customer item : admin.getAllCustomers()) {
			admin.deleteCustomer(item.getId());
		}
		Company compy1 = new Company("acomp1", "a@a.com", "aa1234");
		Company compy2 = new Company("bcomp2", "b@b.com", "bb1234");
		Company compy3 = new Company("ccomp3", "c@c.com", "cc1234");
		admin.addCompany(compy1);
		admin.addCompany(compy2);
		admin.addCompany(compy3);
		Customer custy1 = new Customer("aaaafirst", "aaalast", "aaemail", "a1234");
		Customer custy2 = new Customer("bbbbfirst", "bbblast", "bbemail", "b1234");
		Customer custy3 = new Customer("ccccfirst", "ccclast", "ccemail", "c1234");
		admin.addCustomer(custy1);
		admin.addCustomer(custy2);
		admin.addCustomer(custy3);
		CompanyFacade comp1 = (CompanyFacade) log.login("a@a.com", "aa1234", ClientType.COMPANY);
		CompanyFacade comp2 = (CompanyFacade) log.login("b@b.com", "bb1234", ClientType.COMPANY);
		CompanyFacade comp3 = (CompanyFacade) log.login("c@c.com", "cc1234", ClientType.COMPANY);
		CustomerFacade cust1 = (CustomerFacade) log.login("aaemail", "a1234", ClientType.CUSTOMER);
		CustomerFacade cust2 = (CustomerFacade) log.login("bbemail", "b1234", ClientType.CUSTOMER);
		CustomerFacade cust3 = (CustomerFacade) log.login("ccemail", "c1234", ClientType.CUSTOMER);
		compy1 = comp1.getCompanyDetails();
		compy2 = comp2.getCompanyDetails();
		compy3 = comp3.getCompanyDetails();

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(2020, 0, 1);
		end.set(2021, 0, 1);
		Coupon coupya1 = new Coupon(compy1.getId(), Category.CLOTHES, "titlea", "description", new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()), 10, 100, "image");
		start.set(2021, 6, 11);
		end.set(2023, 0, 1);
		Coupon coupya2 = new Coupon(compy1.getId(), Category.CONTAINERS, "titlea2", "description2",
				new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), 5, 12, "image");
		start.set(2020, 7, 22);
		end.set(2021, 5, 7);
		Coupon coupya3 = new Coupon(compy1.getId(), Category.RESTAURANT, "titlea3", "description3",
				new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), 5435, 5643, "image");
		start.set(2021, 10, 12);
		end.set(2022, 0, 1);
		Coupon coupya4 = new Coupon(compy1.getId(), Category.FOOD, "titlea4", "description4", new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()), 6, 423, "image");
		
		
		
		Coupon coupyb1 = new Coupon(compy2.getId(), Category.DRINK, "titleb", "description", new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()), 10, 100, "image");
		start.set(2021, 6, 11);
		end.set(2023, 0, 1);
		Coupon coupyb2 = new Coupon(compy2.getId(), Category.SPA, "titleb2", "description2",
				new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), 200, 12, "image");
		start.set(2020, 7, 22);
		end.set(2021, 5, 7);
		Coupon coupyb3 = new Coupon(compy2.getId(), Category.RESTAURANT, "titleb3", "description3",
				new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), 5435, 5643, "image");
		start.set(2021, 10, 12);
		end.set(2022, 0, 1);
		Coupon coupyb4 = new Coupon(compy2.getId(), Category.SHOES, "titleb4", "description4", new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()), 6, 423, "image");
		
		
		
		
		Coupon coupyc1 = new Coupon(compy3.getId(), Category.FLIGHT, "titlec", "description", new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()), 10, 100, "image");
		start.set(2021, 6, 11);
		end.set(2023, 0, 1);
		Coupon coupyc2 = new Coupon(compy3.getId(), Category.FURNITURES, "titlec2", "description2",
				new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), 200, 12, "image");
		start.set(2020, 7, 22);
		end.set(2021, 5, 7);
		Coupon coupyc3 = new Coupon(compy3.getId(), Category.ELECTRONICS, "titlec3", "description3",
				new Date(start.getTimeInMillis()), new Date(end.getTimeInMillis()), 5435, 5643, "image");
		start.set(2021, 10, 12);
		end.set(2022, 0, 1);
		Coupon coupyc4 = new Coupon(compy3.getId(), Category.SHOES, "titlec4", "description4", new Date(start.getTimeInMillis()),
				new Date(end.getTimeInMillis()), 6, 423, "image");
		
		
		
		
		
		comp1.addCoupon(coupya1);
		comp1.addCoupon(coupya2);
		comp1.addCoupon(coupya3);
		comp1.addCoupon(coupya4);
		
		comp2.addCoupon(coupyb1);
		comp2.addCoupon(coupyb2);
		comp2.addCoupon(coupyb3);
		comp2.addCoupon(coupyb4);
		
		comp3.addCoupon(coupyc1);
		comp3.addCoupon(coupyc2);
		comp3.addCoupon(coupyc3);
		comp3.addCoupon(coupyc4);
		
		
		coupya1 = comp1.getCompanyCoupons().get(0);
		coupya2 = comp1.getCompanyCoupons().get(1);
		coupya3 = comp1.getCompanyCoupons().get(2);
		coupya4 = comp1.getCompanyCoupons().get(3);
		
		coupyb1 = comp2.getCompanyCoupons().get(0);
		coupyb2 = comp2.getCompanyCoupons().get(1);
		coupyb3 = comp2.getCompanyCoupons().get(2);
		coupyb4 = comp2.getCompanyCoupons().get(3);
		
		coupyc1 = comp3.getCompanyCoupons().get(0);
		coupyc2 = comp3.getCompanyCoupons().get(1);
		coupyc3 = comp3.getCompanyCoupons().get(2);
		coupyc4 = comp3.getCompanyCoupons().get(3);

		custy1 = cust1.getCustomerDetails();
		custy2 = cust2.getCustomerDetails();
		custy3 = cust3.getCustomerDetails();

		for (Coupon c : comp1.getCompanyCoupons()) {
			cust1.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp2.getCompanyCoupons()) {
			cust2.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp3.getCompanyCoupons()) {
			cust3.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp3.getCompanyCoupons()) {
			cust1.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp1.getCompanyCoupons()) {
			cust2.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp2.getCompanyCoupons()) {
			cust3.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp2.getCompanyCoupons()) {
			cust1.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp3.getCompanyCoupons()) {
			cust2.purchaseCoupon(c.getId());
		}
		
		for (Coupon c : comp1.getCompanyCoupons()) {
			cust3.purchaseCoupon(c.getId());
		}
		
		

		coupya1.setDescription("new disc");
		coupya1.setPrice(11115);
		coupya1.setTitle("new title thing");
		comp1.updateCoupon(coupya1);

		end.set(2020, 0, 1);
		coupya2.setEndDate(new Date(end.getTimeInMillis()));
		coupya4.setEndDate(new Date(end.getTimeInMillis()));
		comp1.updateCoupon(coupya2);
		comp1.updateCoupon(coupya4);
//		System.out.println("============Company==============");
//		System.out.println("1\n" + comp1.getOneCoupon(coupya1.getId()));
//		System.out.println("2\n" + comp1.getCompanyDetails());
//		System.out.println("3\n" + comp1.getCompanyCoupons());
//		System.out.println("4\n" + comp1.getCompanyCoupons(Category.FOOD));
//		System.out.println("5\n" + comp1.getCompanyCoupons(300));
//		System.out.println("===========Admin==========");
//		System.out.println("6\n" + admin.getAllCompanies());
//		System.out.println("7\n" + admin.getAllCustomers());
//
//		System.out.println("=========Customer==========");
//		System.out.println("8\n" + cust1.getCustomerCoupons());
//		System.out.println("9\n" + cust1.getCustomerCoupons(Category.RESTAURANT));
//		System.out.println("10\n" + cust1.getCustomerCoupons(5000));
//
//		compy1.setEmail("ohlookanewemail!!");
//		compy1.setPassword("lolderpnewpassword");
//		admin.updateCompany(compy1);
//		System.out.println("11\n" + admin.getOneCompany(compy1.getId()));
//
//		custy1.setEmail("aaaaaaaaaaaaaaaaaaaaaaa");
//		custy1.setLastName("hueuheuehueheuehu");
//		custy1.setPassword("1111111111111111111111");
//
//		admin.updateCustomer(custy1);
//		System.out.println("12\n" + admin.getOneCustomer(custy1.getId()));
 * 

	}
	 */

	public void testDelete() throws LoginFailedException {
		/*
		AdminFacade admin = (AdminFacade) log.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

//		for (Customer item : admin.getAllCustomers()) {
//			admin.deleteCustomer(item.getId());
//		}
//		for (Company item : admin.getAllCompanies()) {
//			admin.deleteCompany(item.getId());
//		}
		
		System.out.println(admin.getAllCoupons());
		*/
	}

}
