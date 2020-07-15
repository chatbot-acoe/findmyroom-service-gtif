package com.mindtree.findmyroom.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FlatBuy {
@Id
@GeneratedValue
private int flatBuyId;
@OneToOne
private Flat flat;
private LocalDate dateOfBooking;
@ManyToOne
private User customer;
@ManyToOne
private User partner;
private String type;
public int getFlatBuyId() {
	return flatBuyId;
}
public void setFlatBuyId(int flatBuyId) {
	this.flatBuyId = flatBuyId;
}
public Flat getFlat() {
	return flat;
}
public void setFlat(Flat flat) {
	this.flat = flat;
}
public LocalDate getDateOfBooking() {
	return dateOfBooking;
}
public void setDateOfBooking(LocalDate dateOfBooking) {
	this.dateOfBooking = dateOfBooking;
}
public User getCustomer() {
	return customer;
}
public void setCustomer(User customer) {
	this.customer = customer;
}
public User getPartner() {
	return partner;
}
public void setPartner(User partner) {
	this.partner = partner;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

}
