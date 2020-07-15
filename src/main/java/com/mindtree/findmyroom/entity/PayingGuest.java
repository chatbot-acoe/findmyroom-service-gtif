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
 * @author M1049123
 *
 */
@Entity
@Table(name = "PG")
public class PayingGuest {
	
	@Id
	@GeneratedValue
	@Column(name = "pg_id")
	private int pgID;				//Unique Id for specific PG generated automatically

	@Column(name = "pg_name")
	private String pgName;			//Name of PG

	@Column(name = "pg_city")
	private String pgCity;			//City in which PG is located

	@Column(name = "pg_location")
	private String Location;		//Location in which is located

	@Column(name = "pg_description")
	private String Description;		//Description about that PG

	@Column(name = "pg_price")
	private double pgPrice;			//Price of the PG

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;				//Many to one mapping for which many PGs can be owned by a single

	@Column(name = "pg_postal_code")
	private int pgPostalCode;		//postal code of PG location

	@Column(name = "pg_type")
	private String pgType;			//Type of PG whether it is 1Sharing or 2Sharing for filtering

	@Column(name = "one_sharing_bed_count")
	private int oneSharingBeds;		//Count of one_sharing_beds in the PG

	@Column(name = "two_sharing_bed_count")
	private int twoSharingBeds;		//Count of two_sharing_beds in the PG


	@Lob
	private byte[] image1;
	
	@Lob
	private byte[] image2;
	
	@Lob
	private byte[] image3;
	
	@Lob
	private byte[] image4;
	
	@Lob
	private byte[] image5;

	public PayingGuest(int pgID, String pgName, String pgCity, String location, String description, double pgPrice,
			User user, int pgPostalCode, String pgType, int oneSharingBeds, int twoSharingBeds, byte[] image1,
			byte[] image2, byte[] image3, byte[] image4, byte[] image5) {
		
		this.pgID = pgID;
		this.pgName = pgName;
		this.pgCity = pgCity;
		Location = location;
		Description = description;
		this.pgPrice = pgPrice;
		this.user = user;
		this.pgPostalCode = pgPostalCode;
		this.pgType = pgType;
		this.oneSharingBeds = oneSharingBeds;
		this.twoSharingBeds = twoSharingBeds;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.image4 = image4;
		this.image5 = image5;
	}

	public PayingGuest() {
		
	}

	public int getPgID() {
		return pgID;
	}

	public void setPgID(int pgID) {
		this.pgID = pgID;
	}

	public String getPgName() {
		return pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	public String getPgCity() {
		return pgCity;
	}

	public void setPgCity(String pgCity) {
		this.pgCity = pgCity;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPgPrice() {
		return pgPrice;
	}

	public void setPgPrice(double pgPrice) {
		this.pgPrice = pgPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPgPostalCode() {
		return pgPostalCode;
	}

	public void setPgPostalCode(int pgPostalCode) {
		this.pgPostalCode = pgPostalCode;
	}

	public String getPgType() {
		return pgType;
	}

	public void setPgType(String pgType) {
		this.pgType = pgType;
	}

	public int getOneSharingBeds() {
		return oneSharingBeds;
	}

	public void setOneSharingBeds(int oneSharingBeds) {
		this.oneSharingBeds = oneSharingBeds;
	}

	public int getTwoSharingBeds() {
		return twoSharingBeds;
	}

	public void setTwoSharingBeds(int twoSharingBeds) {
		this.twoSharingBeds = twoSharingBeds;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public byte[] getImage4() {
		return image4;
	}

	public void setImage4(byte[] image4) {
		this.image4 = image4;
	}

	public byte[] getImage5() {
		return image5;
	}

	public void setImage5(byte[] image5) {
		this.image5 = image5;
	}

	@Override
	public String toString() {
		return "PayingGuest [pgID=" + pgID + ", pgName=" + pgName + ", pgCity=" + pgCity + ", Location=" + Location
				+ ", Description=" + Description + ", pgPrice=" + pgPrice + ", user=" + user + ", pgPostalCode="
				+ pgPostalCode + ", pgType=" + pgType + ", oneSharingBeds=" + oneSharingBeds + ", twoSharingBeds="
				+ twoSharingBeds + "]";
	}
	
	
	


}
