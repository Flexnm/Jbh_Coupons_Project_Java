package com.example.couponsProject.jobs;

import java.sql.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.couponsProject.beans.Coupon;
import com.example.couponsProject.db.CouponRepository;


@Component
@Transactional
public class ExpiredCouponRemovalJob implements Runnable {

	@Autowired
	private CouponRepository coupDB;
	private boolean quit;

	public ExpiredCouponRemovalJob() {

	}

	public void stopJob() {
		quit = !(quit);
	}
	
	public void run() {
		while (true) {
			try {
				Date date = new Date(System.currentTimeMillis());
				for (Coupon coupon : coupDB.findByEndDateBefore(date)) {
					coupDB.deletePurchasesByCouponId(coupon.getId());
					coupDB.delete(coupon);
				}
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				this.stopJob();
				if (quit) {
					return;
				}
			}
		}
	}
	
}
