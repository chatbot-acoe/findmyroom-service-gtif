package com.mindtree.findmyroom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author M1049287
 *
 */
@Entity
@Table(name = "Flat")
public class Flat {

	@Id
	@GeneratedValue
	@Column(name="flatid")
	private int flatId; 	// Id of flat Generated automatically

	@Column(name="flatname")
	private String flatName; // specifies the flatname

	@Column(name="flatcity")
	private String flatCity; // specifies the city in which the flat is present

	@Column(name="flatlocation")
	private String flatLocation; // specifies the flat location

	@Column(name="flatprice")
	private double flatPrice; // specifies the price of flat for Buy or Rent

	@Column(name="falttype")
	private String faltType; // specifies whether the flat is furnished or semifurnishedo

	@Column(name="faltcategory")
	private String faltCategory; // specifies whether the flat is for to Rent or to Buy

	@Column(name="flatrooms")
	private String flatRooms; // specifies whether flat is 1BHK,2BHK



	@Column(name="isavailable")
	private boolean isAvailable; // specifies whether the falt is available for rent or buy

	@ManyToOne
	@JoinColumn(name="flatownerid")
	@JsonIgnore
	private User user; // maps the flat to the user id;

	@Column(name="flatpostalcode")
	private int flatPostalCode;

	@Column(name="flatarea")
	private String flatArea; // specifies the square feet area of flats

	@Column(name="flatdescription")
	private String flatDescription; // Description about the flat
	
	@Lob
	byte[] pic1;
	
	@Lob
	byte[] pic2;
	
	@Lob
	byte[] pic3;
	
	@Lob
	byte[] pic4;
	
	@Lob
	byte[] pic5;

	public Flat() {
		super();

	}

	

	public byte[] getPic1() {
		return pic1;
	}



	public void setPic1(byte[] pic1) {
		this.pic1 = pic1;
	}



	public byte[] getPic2() {
		return pic2;
	}



	public void setPic2(byte[] pic2) {
		this.pic2 = pic2;
	}



	public byte[] getPic3() {
		return pic3;
	}



	public void setPic3(byte[] pic3) {
		this.pic3 = pic3;
	}



	public byte[] getPic4() {
		return pic4;
	}



	public void setPic4(byte[] pic4) {
		this.pic4 = pic4;
	}



	public byte[] getPic5() {
		return pic5;
	}



	public void setPic5(byte[] pic5) {
		this.pic5 = pic5;
	}



	public Flat( String flatName, String flatCity, String flatLocation, double flatPrice, String faltType,
			String faltCategory, String flatRooms, boolean isAvailable, int flatPostalCode, String flatArea,
			String flatDescription, byte[] pic1, byte[] pic2, byte[] pic3, byte[] pic4, byte[] pic5) {
		super();
		
		this.flatName = flatName;
		this.flatCity = flatCity;
		this.flatLocation = flatLocation;
		this.flatPrice = flatPrice;
		this.faltType = faltType;
		this.faltCategory = faltCategory;
		this.flatRooms = flatRooms;
		this.isAvailable = isAvailable;
		
		this.flatPostalCode = flatPostalCode;
		this.flatArea = flatArea;
		this.flatDescription = flatDescription;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
	}



	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int flatId) {
		this.flatId = flatId;
	}

	public String getFlatName() {
		return flatName;
	}

	public void setFlatName(String flatName) {
		this.flatName = flatName;
	}

	public String getFlatCity() {
		return flatCity;
	}

	public void setFlatCity(String flatCity) {
		this.flatCity = flatCity;
	}

	public String getFlatLocation() {
		return flatLocation;
	}

	public void setFlatLocation(String flatLocation) {
		this.flatLocation = flatLocation;
	}

	public double getFlatPrice() {
		return flatPrice;
	}

	public void setFlatPrice(double flatPrice) {
		this.flatPrice = flatPrice;
	}

	public String getFaltType() {
		return faltType;
	}

	public void setFaltType(String faltType) {
		this.faltType = faltType;
	}

	public String getFaltCategory() {
		return faltCategory;
	}

	public void setFaltCategory(String faltCategory) {
		this.faltCategory = faltCategory;
	}

	public String getFlatRooms() {
		return flatRooms;
	}

	public void setFlatRooms(String flatRooms) {
		this.flatRooms = flatRooms;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFlatPostalCode() {
		return flatPostalCode;
	}

	public void setFlatPostalCode(int flatPostalCode) {
		this.flatPostalCode = flatPostalCode;
	}

	public String getFlatArea() {
		return flatArea;
	}

	public void setFlatArea(String flatArea) {
		this.flatArea = flatArea;
	}

	
	public String getFlatDescription() {
		return flatDescription;
	}

	public void setFlatDescription(String flatDescription) {
		this.flatDescription = flatDescription;
	}

	

}
