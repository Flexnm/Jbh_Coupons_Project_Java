package com.example.couponsProject.facade;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.couponsProject.db.CompanyRepository;
import com.example.couponsProject.db.CouponRepository;
import com.example.couponsProject.db.CustomerRepository;
import com.example.couponsProject.exceptions.LoginFailedException;


@Service
@Transactional
@Scope("prototype")
public abstract class ClientFacade {

	@Autowired
	protected CompanyRepository compDB;
	@Autowired
	protected CustomerRepository custDB;
	@Autowired
	protected CouponRepository coupDB;

	public ClientFacade() {

	}
	

	public abstract boolean login(String email, String password) throws LoginFailedException;

}
