package com.example.couponsProject.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@OneToMany(mappedBy = "companyId")
	private List<Coupon> coupons;

	public Company() {

	}
	
	
	public Company(String name, String email, String password) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	public Company(String name, String email, String password, List<Coupon> coupons) {
		setName(name);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public String toString() {
		return "Company #" + id + ": " + name + ", Email: " + email + ", Password: " + password + "\n";
	}

}
