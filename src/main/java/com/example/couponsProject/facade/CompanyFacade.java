package com.example.couponsProject.facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.couponsProject.beans.Category;
import com.example.couponsProject.beans.Company;
import com.example.couponsProject.beans.Coupon;
import com.example.couponsProject.exceptions.CompanyNotFoundException;
import com.example.couponsProject.exceptions.CouponNotFoundException;
import com.example.couponsProject.exceptions.IllegalDataChangeException;
import com.example.couponsProject.exceptions.InvalidNewCouponDataException;

@Service
@Scope("prototype")
public class CompanyFacade extends ClientFacade {

	private int companyId;

	public CompanyFacade() {
		
	}
	

	public boolean login(String email, String password) {
		Optional<Company> opt = compDB.findCompanyByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			companyId = opt.get().getId();
			return true;
		}
		return false;
	}

	public void addCoupon(Coupon coupon) throws IllegalDataChangeException, InvalidNewCouponDataException {
		if(coupon.getEndDate().before(Date.valueOf(new Date(System.currentTimeMillis()).toString()))) {
			throw new InvalidNewCouponDataException("The new coupon expiration date of " + coupon.getEndDate() + " has already expired.");
		}
		for (Coupon c : compDB.getOne(companyId).getCoupons()) {
			if (c.getTitle().equals(coupon.getTitle())) {
				throw new IllegalDataChangeException("Coupon title already exists in the database.");
			}
		}
		coupDB.save(coupon);
	}

	public void updateCoupon(Coupon coupon) throws IllegalDataChangeException {
		if(coupon.getCompanyId() != companyId) {
			throw new IllegalDataChangeException("This coupon is not of this company.");
		}
		if(coupon.getEndDate().before(new Date(System.currentTimeMillis()))) {
			throw new IllegalDataChangeException("The new coupon expiration date of " + coupon.getEndDate() + " has already expired.");
		}
		for (Coupon c : compDB.getOne(companyId).getCoupons()) {
			if (c.getTitle().equals(coupon.getTitle()) && c.getId() != coupon.getId()) {
				throw new IllegalDataChangeException("Coupon title already exists in the database.");
			}
		}
		coupDB.save(coupon);
	}
//	repository.getOne(id) throws EntityNotFoundException
	public void deleteCoupon(int couponId) throws IllegalDataChangeException, EntityNotFoundException {
		if (compDB.getOne(companyId).getCoupons().stream().filter(c -> c.getId() == couponId).findFirst().isPresent()) {
			coupDB.deletePurchasesByCouponId(couponId);
			coupDB.deleteById(couponId);
		} else {
			throw new IllegalDataChangeException("Cannot delete coupon that does not match to companys coupons list.");
		}
	}

	public List<Coupon> getCompanyCoupons() {
		return compDB.getOne(companyId).getCoupons();
	}

	public ArrayList<Coupon> getCompanyCoupons(Category category) {
		return (ArrayList<Coupon>) compDB.getOne(companyId).getCoupons().stream().filter(c -> c.getCategory().ordinal() == category.ordinal()).collect(Collectors.toList());
	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
		return (ArrayList<Coupon>) compDB.getOne(companyId).getCoupons().stream().filter(c -> c.getPrice() <= maxPrice).collect(Collectors.toList());
	}

	public Coupon getOneCoupon(int couponId) throws CouponNotFoundException {
		return compDB.getOne(companyId).getCoupons().stream().filter(c -> c.getId() == couponId).findFirst().get();
	}

	public Company getCompanyDetails() throws CompanyNotFoundException {
		return compDB.findById(companyId).orElseThrow(CompanyNotFoundException::new);
	}

}
