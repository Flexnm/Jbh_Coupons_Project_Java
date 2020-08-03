package com.example.couponsProject.facade;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.couponsProject.beans.Category;
import com.example.couponsProject.beans.Coupon;
import com.example.couponsProject.beans.Customer;
import com.example.couponsProject.exceptions.InvalidCouponException;

@Service
@Scope("prototype") 
public class CustomerFacade extends ClientFacade {

	private int customerId;

	public CustomerFacade() {

	}
	public boolean login(String email, String password) {
		Optional<Customer> opt = custDB.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			customerId = opt.get().getId();
			return true;
		} 
		return false;
	}
	
//	repository.getOne(id) throws EntityNotFoundException
	public void purchaseCoupon(int couponId) throws InvalidCouponException, EntityNotFoundException{
		Coupon coup = coupDB.getOne(couponId);
		Customer cust = custDB.getOne(customerId);
		boolean hasCoupon = false;
		for (Coupon c : coupDB.findCustomerCoupons(customerId)) {
			if (c.getId() == couponId) {
				hasCoupon = true;
				break;
			}
		}
		boolean outOfDate = coup.getEndDate().before(new Date(System.currentTimeMillis()));
		boolean outOfStock = coup.getAmount() == 0;
		if (hasCoupon || outOfStock || outOfDate) {
			throw new InvalidCouponException(hasCoupon, outOfStock, outOfDate);
		} else {
			coup.setAmount(coup.getAmount() - 1);
			coupDB.save(coup); // TODO improve
			cust.getCoupons().add(coup);
			cust.setCoupons(cust.getCoupons());
			custDB.save(cust);
		}
	}
	

	public List<Coupon> getCustomerCoupons() {
		return coupDB.findCustomerCoupons(customerId);
	}

	public List<Coupon> getCustomerCoupons(Category category) {
		return coupDB.findCustomerCoupons(customerId, category.ordinal());
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		return coupDB.findCustomerCoupons(customerId, maxPrice);
	}

	public Customer getCustomerDetails() {
		return custDB.findById(customerId).get();
	}
	
	public List<Coupon> getAllCouponsForPurchase(){
		return coupDB.findAll().stream().filter(c -> !(getCustomerCoupons().contains(c))).collect(Collectors.toList());
	}
	
	public List<Coupon> getAllCouponsForPurchase(Category category){
		return coupDB.findByCategory(category).stream().filter(c -> !(getCustomerCoupons(category).contains(c))).collect(Collectors.toList());
	}

	public List<Coupon> getAllCouponsForPurchase(double maxPrice){
		return coupDB.findByPriceLessThanEqual(maxPrice).stream().filter(c -> !(getCustomerCoupons(maxPrice).contains(c))).collect(Collectors.toList());
	}
}




