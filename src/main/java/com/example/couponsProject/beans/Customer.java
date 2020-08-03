package com.example.couponsProject.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "purchases", joinColumns = @JoinColumn(referencedColumnName = "id", name = "customer_id"), inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "coupon_id"))
	private Set<Coupon> coupons = new HashSet<Coupon>();

	public Customer() {

	}

//	CTOR used for initializing customer objects with no coupons (testing tool)
	public Customer(String firstName, String lastName, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
	}

//	The "actual" customer CTOR that will exist in the next stage of the project.
	public Customer(String firstName, String lastName, String email, String password, Set<Coupon> coupons) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setCoupons(coupons);
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}

	public String toString() {
		return "Customer ID #" + id + ": " + firstName + " " + lastName + ". Email: " + email + ", Password: "
				+ password + "\n";
	}

}
