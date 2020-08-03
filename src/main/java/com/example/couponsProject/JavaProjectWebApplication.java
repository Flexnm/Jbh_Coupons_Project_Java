package com.example.couponsProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.couponsProject.jobs.ExpiredCouponRemovalJob;

@SpringBootApplication
public class JavaProjectWebApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(JavaProjectWebApplication.class, args);
		Thread job = new Thread(ctx.getBean(ExpiredCouponRemovalJob.class));
		job.start();
	}
}