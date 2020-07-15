package com.mindtree.findmyroom.flatdto;


public class FlatDetails {

	private int id;
	private String flatName;
	private String flatCity;
	private String flatLocation;
	private double flatPrice;
	private String faltType;
	private String flatRooms;
	private int flatPostalCode;
	private String flatArea;
	private String flatDescription;
	private String ownerName;
	private long ownerPhone;
	private byte[] pic1;
	private byte[] pic2;
	private byte[] pic3;
	private byte[] pic4;
	private byte[] pic5;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public long getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(long ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	

	public FlatDetails(int id, String flatName, String flatCity, String flatLocation, double flatPrice, String faltType,
			String flatRooms, int flatPostalCode, String flatArea, String flatDescription, String ownerName,
			long ownerPhone, byte[] pic1, byte[] pic2, byte[] pic3, byte[] pic4, byte[] pic5) {
		super();
		this.id = id;
		this.flatName = flatName;
		this.flatCity = flatCity;
		this.flatLocation = flatLocation;
		this.flatPrice = flatPrice;
		this.faltType = faltType;
		this.flatRooms = flatRooms;
		this.flatPostalCode = flatPostalCode;
		this.flatArea = flatArea;
		this.flatDescription = flatDescription;
		this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
	}

	public FlatDetails() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getFlatRooms() {
		return flatRooms;
	}

	public void setFlatRooms(String flatRooms) {
		this.flatRooms = flatRooms;
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

	

}
