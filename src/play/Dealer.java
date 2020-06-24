package play;

import java.util.ArrayList;

import card.Card;
import cardSet.AceCard;
import cardSet.MajorCard;
import cardSet.NumberCard;

public class Dealer {
	
	
	
	public Dealer() {}
	
	// 카드를 랜덤한 수를 이용해서 배분한다.
	public void share(ArrayList<Card> list, boolean cardDeck[],Player p1) {
		
		int number;
		int card_num = -1;
		
		for (int i = 0; i < cardDeck.length; i++) {
			number = (int)(Math.random()*52);
			if(cardDeck[number]==false) {
				cardDeck[number] = true;
				card_num = number;
				break;
			}	
		}		
		if(card_num == -1) {
			System.out.println("뽑을 카드가 없습니다.");
			return;
		}			
		cardTrans(list,card_num,p1);	
	}
	// 카드의 수에 따라 문양을 부여하고 11,12,13은 J,Q,K를 부여한다. 
	public void cardTrans(ArrayList<Card> list, int card_num,Player p1) {
		
		String suit = "";
		
		if(card_num/13 == 0) {
			suit = "♠";
		}
		else if(card_num/13 == 1){
			suit = "◆ ";
			card_num = card_num - 13;
		}
		else if(card_num/13 == 2){
			suit = "♥";
			card_num = card_num - 26;
		}
		else if(card_num/13 == 3){
			suit = "♣";
			card_num = card_num - 39;
		}
			
		Card car;
		
		String alphabet = "";
		
		if(card_num % 13 < 10 && card_num % 13 != 0 ) {
			car = new NumberCard(card_num+1,suit);
			list.add(car);
		}
		else if(card_num % 13 > 9) {
			
			if(card_num % 13 == 10) alphabet = "J";	
			else if(card_num % 13 == 11) alphabet = "Q";
			else if(card_num % 13 == 12) alphabet = "K";
			
			car = new MajorCard(card_num+1,suit,alphabet);
			list.add(car);
		}
		else if(card_num % 13 == 0 ) {
			car = new AceCard(card_num+1,suit);
			list.add(car);
		}
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(p1.getName()+"의  "+(i+1)+"번 째 카드 : "+list.get(i));
		}
		System.out.println("=============================");
		
	}
	// 카드가 21이 넘아가면 버스트를 체크하고 21이면 해당 플레이어의 블랙잭을 체크.
	public boolean cardCheck(Player p1,ArrayList<Card> list) {
		int sum = 0;
		boolean burst = false;
		sum = sum_set(p1, list);
		
		if(sum>21) {
			burst = true;
		}
		else if(sum == 21) {
			p1.setBlackJack(true);
		}
		return burst;
	}
	// 체크하는 플레이어의 모든 카드의 수의 합을 연산.	
	public int sum_set(Player p1, ArrayList<Card> list) {
		
		int sum = 0;		
		for (int i = 0; i < list.size(); i++) {
			
			Card card = list.get(i);
			// 만약에 에이스카드면 변환할건지 물어본다
		    if((card.getNumber() == 1 || card.getNumber() == 11)
		    		&& card instanceof AceCard) {
				AceCard ac = (AceCard)card;
				ac.transNum(p1.getName());
			}		    
			sum = sum + card.getNumber();
		}		
		return sum;		
	}	
}
