package cardSet;

import card.Card;

public class NumberCard extends Card{
	private int number;
	private String picture;
	
	public NumberCard() {
	
	}

	public NumberCard(int number, String picture) {
		
		this.number = number;
		this.picture = picture;
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

	@Override
	public String toString() {
		return picture + " " + number;
	}
	
	
}
