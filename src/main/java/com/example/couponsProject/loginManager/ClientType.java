package com.example.couponsProject.loginManager;

public enum ClientType {
	
	ADMINISTRATOR,
	COMPANY,
	CUSTOMER,
	NULL // NULL client type avoids an error caused by empty login inputs cases the url to have 'undefined' in the client type.

}
