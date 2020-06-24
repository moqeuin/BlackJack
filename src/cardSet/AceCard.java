package cardSet;

import java.util.Scanner;

import card.Card;

public class AceCard extends Card {
	private int number;
	private String picture;
	Scanner sc = new Scanner(System.in);
	
	public AceCard() {
		
	}
	

	public AceCard(int number, String picture) {
		
		this.number = number;
		this.picture = picture;
	}
	
	public void transNum(String name) {
		if(this.number == 1) {
			System.out.println(name+" : "+"에이스를 11로 변환 시키겠습니까?(yes/no)");
			
			String str = sc.next();
			if(str.equals("yes"))
			this.number = 11;
			
		}
		else if(this.number == 11) {
			System.out.println(name+" : "+"에이스를 1로 변환 시키겠습니까?(yes/no)");	
			
			String str = sc.next();
			if(str.equals("yes"))
			this.number = 1;
					
		}
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
