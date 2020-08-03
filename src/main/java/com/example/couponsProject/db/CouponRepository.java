package com.example.couponsProject.db;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.couponsProject.beans.Category;
import com.example.couponsProject.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	@Query(value = "SELECT coupons.* FROM (coupons INNER JOIN purchases on coupons.id = purchases.coupon_id), customers WHERE customers.id = purchases.customer_id AND customers.id = :customerId", nativeQuery = true)
	List<Coupon> findCustomerCoupons(int customerId);

	@Query(value = "SELECT coupons.* FROM (coupons INNER JOIN purchases on coupons.id = purchases.coupon_id), customers WHERE customers.id = purchases.customer_id AND customers.id = :customerId AND coupons.category = :category", nativeQuery = true)
	List<Coupon> findCustomerCoupons(int customerId, int category);

	@Query(value = "SELECT * FROM (coupons INNER JOIN purchases on coupons.id = purchases.coupon_id), customers WHERE customers.id = purchases.customer_id AND customers.id = :customerId AND coupons.price <= :maxPrice", nativeQuery = true)
	List<Coupon> findCustomerCoupons(int customerId, double maxPrice);

	@Modifying
	@Query(value = "DELETE FROM purchases WHERE coupon_id = :couponId", nativeQuery = true)
	void deletePurchasesByCouponId(int couponId);

	@Modifying
	@Query(value = "DELETE FROM purchases WHERE customer_id = :customerId", nativeQuery = true)
	void deletePurcahseByCustomerId(int customerId);

	List<Coupon> findByEndDateBefore(Date now);

	List<Coupon> findByCategory(Category category);

	List<Coupon> findByPriceLessThanEqual(double maxPrice);
}
