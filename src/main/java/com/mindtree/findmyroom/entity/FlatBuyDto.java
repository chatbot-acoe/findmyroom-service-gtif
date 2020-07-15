package com.mindtree.findmyroom.entity;

import java.time.LocalDate;

public class FlatBuyDto {

	private String flatName;
	private String ownerName;
	private LocalDate buyedDate;
	private double price;
	public String getFlatName() {
		return flatName;
	}
	public void setFlatName(String flatName) {
		this.flatName = flatName;
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
		return "FlatBuyDto [flatName=" + flatName + ", ownerName=" + ownerName + ", buyedDate=" + buyedDate + ", price="
				+ price + "]";
	}
	
	
}
