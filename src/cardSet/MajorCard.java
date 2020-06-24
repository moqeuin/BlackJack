package cardSet;

import card.Card;

public class MajorCard extends Card {
	private int number;
	private String picture;
	private String major;
	
	public MajorCard() {
		
		
	}
	
	
	public MajorCard(int number, String picture, String major) {
		
		this.number = number;
		this.picture = picture;
		this.major = major;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}


	@Override
	public String toString() {
		return picture + " " + number + " "+major;
	}
	
	
	
}
