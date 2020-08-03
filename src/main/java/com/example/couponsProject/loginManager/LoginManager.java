package com.example.couponsProject.loginManager;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.couponsProject.exceptions.LoginFailedException;
import com.example.couponsProject.facade.AdminFacade;
import com.example.couponsProject.facade.ClientFacade;
import com.example.couponsProject.facade.CompanyFacade;
import com.example.couponsProject.facade.CustomerFacade;


@Component
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;
	

	public ClientFacade login(String email, String password, ClientType clientType) throws LoginFailedException {
		switch (clientType) {
		case ADMINISTRATOR:
			AdminFacade admin = ctx.getBean(AdminFacade.class);
			if (admin.login(email, password)) {
				return admin;
			}
			break;
		case COMPANY:
			CompanyFacade company = ctx.getBean(CompanyFacade.class);
			if (company.login(email, password)) {
				return company;
			}
			break;
		case CUSTOMER:
			CustomerFacade customer = ctx.getBean(CustomerFacade.class);
			if (customer.login(email, password)) {
				return customer;
			}
			break;
		default:
			break;
		}
		
		throw new LoginFailedException();
	}
	

}
