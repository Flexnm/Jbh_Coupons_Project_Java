package com.example.couponsProject.web.aspect;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.couponsProject.facade.AdminFacade;
import com.example.couponsProject.facade.CompanyFacade;
import com.example.couponsProject.facade.CustomerFacade;
import com.example.couponsProject.loginManager.Session;

@Component
@Aspect
@SuppressWarnings("unused") // the facade casting is used to throw a ClassCastException but is not used.
public class ControllersAspect {

	@Autowired
	private Map<String, Session> sessionMap;

	@Around("execution(* com.example.couponsProject.web.AdminController.*(..))")
	public Object validateAdmin(ProceedingJoinPoint pjp) {
		Session session = sessionMap.get(pjp.getArgs()[0]);
		if (session != null) {
			try {
				AdminFacade admin = (AdminFacade) session.getClientFacade();
				if (System.currentTimeMillis() - session.getLastLoginTime() < 1000 * 60 * 30) {
					session.setLastLoginTime(System.currentTimeMillis());
					Object response = pjp.proceed();
					return response; // is it doing it twice?
				} else {
					return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Your session timed out due to inactivity.");
				}
			} catch (Throwable e) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission not granted.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user detected.");
		}
	}

	@Around("execution(* com.example.couponsProject.web.CompanyController.*(..))")
	public Object validateCompany(ProceedingJoinPoint pjp) {
		Session session = sessionMap.get(pjp.getArgs()[0]);
		if (session != null) {
			try {
				CompanyFacade company = (CompanyFacade) session.getClientFacade();
				if (System.currentTimeMillis() - session.getLastLoginTime() < 1000 * 60 * 30) {
					session.setLastLoginTime(System.currentTimeMillis());
					return pjp.proceed(); // is it doing it twice?
				} else {
					return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Your session timed out due to inactivity.");
				}
			} catch (Throwable e) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission not granted.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user detected.");
		}
	}

	@Around("execution(* com.example.couponsProject.web.CustomerController.*(..))")
	public Object validateCustomer(ProceedingJoinPoint pjp) {
		Session session = sessionMap.get(pjp.getArgs()[0]);
		if (session != null) {
			try {
				CustomerFacade customer = (CustomerFacade) session.getClientFacade();
				if (System.currentTimeMillis() - session.getLastLoginTime() < 1000 * 60 * 30) {
					session.setLastLoginTime(System.currentTimeMillis());
					return pjp.proceed(); // is it doing it twice?
				} else {
					return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Your session timed out due to inactivity.");
				}
			} catch (Throwable e) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permission not granted.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user detected.");
		}
	}

}
