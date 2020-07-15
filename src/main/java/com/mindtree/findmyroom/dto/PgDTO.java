package com.mindtree.findmyroom.dto;

/**
 * @author M1049123
 *
 */
public class PgDTO // Creating a DTO class for PG for storing and sending specific details
{

	private int pgID; // Specific Id of PG
	private String pgName; // Name of the PG
	private String pgCity; // City in which PG is located
	private String pgLocation; // Location in which PG is located
	private double pgPrice; // Price of the PG
	private String pgDescription;// Description of PG
	private int pgPostalCode; // Postal code of PG in which it is located
	private String pgType; // Type of PG whether it 1Sharing or 2 Sharing
	private int oneSharingBeds; // Count of one sharing beds
	private int twoSharingBeds; // count of two sharing beds
	private String ownerName; // Name of the PG owner
	private long ownerPhoneNumber; // Mobile Number of the PG owner
	private byte[] pic1;
	private byte[] pic2;
	private byte[] pic3;
	private byte[] pic4;
	private byte[] pic5;
	public PgDTO(int pgID, String pgName, String pgCity, String pgLocation, double pgPrice, String pgDescription,
			int pgPostalCode, String pgType, int oneSharingBeds, int twoSharingBeds, String ownerName,
			long ownerPhoneNumber, byte[] pic1, byte[] pic2, byte[] pic3, byte[] pic4, byte[] pic5) {
		super();
		this.pgID = pgID;
		this.pgName = pgName;
		this.pgCity = pgCity;
		this.pgLocation = pgLocation;
		this.pgPrice = pgPrice;
		this.pgDescription = pgDescription;
		this.pgPostalCode = pgPostalCode;
		this.pgType = pgType;
		this.oneSharingBeds = oneSharingBeds;
		this.twoSharingBeds = twoSharingBeds;
		this.ownerName = ownerName;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
	}
	public PgDTO() {
		super();
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
	public String getPgLocation() {
		return pgLocation;
	}
	public void setPgLocation(String pgLocation) {
		this.pgLocation = pgLocation;
	}
	public double getPgPrice() {
		return pgPrice;
	}
	public void setPgPrice(double pgPrice) {
		this.pgPrice = pgPrice;
	}
	public String getPgDescription() {
		return pgDescription;
	}
	public void setPgDescription(String pgDescription) {
		this.pgDescription = pgDescription;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public long getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}
	public void setOwnerPhoneNumber(long ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
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
	@Override
	public String toString() {
		return "PgDTO [pgID=" + pgID + ", pgName=" + pgName + ", pgCity=" + pgCity + ", pgLocation=" + pgLocation
				+ ", pgPrice=" + pgPrice + ", pgDescription=" + pgDescription + ", pgPostalCode=" + pgPostalCode
				+ ", pgType=" + pgType + ", oneSharingBeds=" + oneSharingBeds + ", twoSharingBeds=" + twoSharingBeds
				+ ", ownerName=" + ownerName + ", ownerPhoneNumber=" + ownerPhoneNumber + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + pgID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PgDTO other = (PgDTO) obj;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (pgID != other.pgID)
			return false;
		return true;
	}
	
	
	
}
