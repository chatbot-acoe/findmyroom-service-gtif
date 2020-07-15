package com.mindtree.findmyroom.entity;

import java.time.LocalDate;

public class PGRentDto {
	private String pgName;
	private String ownerName;
	private LocalDate buyedDate;
	private double price;
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public LocalDate getBuyedDate() {
		return buyedDate;
	}
	public void setBuyedDate(LocalDate buyedDate) {
		this.buyedDate = buyedDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "PGRentDto [pgName=" + pgName + ", ownerName=" + ownerName + ", buyedDate=" + buyedDate + ", price="
				+ price + "]";
	}
	
	
}
