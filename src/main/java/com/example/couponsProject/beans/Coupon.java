package com.example.couponsProject.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	@Column(columnDefinition = "BLOB")
	private byte[] image;

	public Coupon() {

	}

	public Coupon(int companyId, Category category, String title, String description, Date startDate, Date endDate,
			int amount, double price, byte[] image) {
		setCompanyId(companyId);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}

	public int getId() {
		return id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public Category getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getAmount() {
		return amount;
	}

	public double getPrice() {
		return price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String toString() {
		return "Coupon #" + id + ": " + title + ", " + description + ". Company ID #" + companyId + ", Duraction: "
				+ startDate + " - " + endDate + ". Category: " + category + ", Amount: " + amount + ", Price: " + price
				+ "\n";
	}

}
