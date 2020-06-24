package card;

public class Card {
	private int number;
	private String picture;
	
	public Card() {
		
	}
	
	public Card(int number, String picture) {
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
		return "Card [number=" + number + ", picture=" + picture + "]";
	}
	
	
	
}
