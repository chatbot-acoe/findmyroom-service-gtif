package com.mindtree.findmyroom.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PGRent {

	@Id
	@GeneratedValue
	private int pgRentId;
	private LocalDate dateOfBooking;
	@ManyToOne
	private PayingGuest payinGuest;
	@ManyToOne
	private User customer;
	@ManyToOne
	private User partner;
	public int getPgRentId() {
		return pgRentId;
	}
	public void setPgRentId(int pgRentId) {
		this.pgRentId = pgRentId;
	}
	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public PayingGuest getPayinGuest() {
		return payinGuest;
	}
	public void setPayinGuest(PayingGuest payinGuest) {
		this.payinGuest = payinGuest;
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
	
}
