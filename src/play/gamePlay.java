package play;

import java.util.ArrayList;
import java.util.Scanner;
import card.Card;


public class gamePlay {
	
	Scanner sc = new Scanner(System.in);
	boolean cardDeck[];
	ArrayList<Card> playerList;
	ArrayList<Card> comList;
	Dealer dealer;
	
	public gamePlay() {
		
		Player player = new Player(3000,0,false,"player");
		Player computer = new Player(3000,0,false,"computer");
		
		dealer = new Dealer();
		
		playerList = new ArrayList<Card>();
		comList = new ArrayList<Card>();
		cardDeck = new boolean[52];
		
		while(true) {
			// 처음에 게임을 시작하기 전 배팅금액, 카드 분배.	
			gameSetting(player, computer, cardDeck);
			
			// 만약에 누군가 2장의 카드로 블랙잭을 맞춰다면  게임을 시작하지 않음.
			if((!player.getBlackJack()) && (!computer.getBlackJack())) {		
				gameStart(player, computer, cardDeck);						
			}
			// 맞춘 플레이어의 블랙잭 표시를 확인하고 출력.
			if(player.getBlackJack()) System.out.println("플레이어의 승리!");
			else if(computer.getBlackJack()) System.out.println("컴퓨터의 승리!");
			// 게임이 끝났으니 분배한 카드를 다시회수, 블랙잭 표시도 초기화.
			game_init(player, computer);
			// 게임의 결과
			System.out.println("게임결과");
			System.out.println(player.toString()+"\n"+computer.toString());
			
			System.out.print("게임을 다시하시겠습니까?(yes/no)");		
			String str = sc.next();
			
			if(str.equals("yes")) { 
				continue;
			}
			else if(str.equals("no")) {
				System.out.print("게임을 종료합니다.");
				break;	
			}
		}		
	}
	// 게임세팅 : 돈을 배팅하고 카드 두 장씩 나누어준다.	
	public void gameSetting(Player player, Player computer, boolean cardDeck[]) {
		
		for (int i = 0; i < cardDeck.length; i++) {
			cardDeck[i] = false;		
		}
		while(true) {
			System.out.println("player의 소유 금액 : " + player.getCash());
			if(player.getCash()==0) {
				System.out.println("player는 소유 금액이 없어 게임시작할 수 없습니다.");
				System.exit(0);
			}
			System.out.print("배팅금액을 정해주십시오. player >>");
			int bet1 = sc.nextInt();
			if(bet1 > player.getCash()) {
				System.out.println("소유 금액이 충분치 않습니다.");
				continue;
			}
			
			System.out.println("computer의 소유 금액 : " + computer.getCash());
			if(computer.getCash()==0) {
				System.out.println("computer는 소유 금액이 없어 게임시작할 수 없습니다.");
				System.exit(0);
			}
			System.out.print("배팅금액을 정해주십시오. computer >>");
			int bet2 = sc.nextInt();
			if(bet2 > computer.getCash()) {
				System.out.println("소유 금액이 충분치 않습니다.");
				continue;
			}
			// 배팅금액을 저장하고 소유금액에서 배팅금액을 감소.
			player.setBetting(bet1);
			player.betAfter(bet1);
			
			computer.setBetting(bet2);
			computer.betAfter(bet2);
			
			break;
		}	
		dealer.share(playerList, cardDeck, player);
		dealer.share(comList, cardDeck, computer);
		dealer.share(playerList, cardDeck, player);
		// 두 번째 카드를 받을 때부터 버스트를 체크.
		boolean burst = dealer.cardCheck(player,playerList);
		
		// 버스트면 배팅금액의 추가수당을 주고 리턴.
		if(burst) {
			System.out.println("플레이어의 버스트");
			computer.setBlackJack(true);
			win_judge(2,player,computer);
			return;
		}
		// 두 번째 카드를 받을 때부터 버스트를 체크.
		dealer.share(comList, cardDeck, computer);
	
		burst = dealer.cardCheck(computer,comList);
		// 버스트면 배팅금액의 추가수당을 주고 리턴.
		if(burst) {
			System.out.println("컴퓨터의 버스트");
			player.setBlackJack(true);
			win_judge(1,player,computer);
			return;
		}
					
	}
	
	// 게임시작 : 턴마다 카드를 받을건지 정하고 21이 넘어가면 버스트, 21을 맞추면 블랙잭.
	// 둘 다 더 이상 안 받으면 둘 중 큰 수가 승리, 수가 같으면 무승부.
	public void gameStart(Player player, Player computer, boolean cardDeck[]) {
		
		String answer_p = null;
		String answer_c = null;
		
		while(true) {
			
			// 1. 플레이어의 턴			
			System.out.print("카드를 받으시겠습니까?(yes/no) player >>");
			answer_p =sc.next();
			// 카드를 받는 경우.
			if(answer_p.equals("yes")) {
				
				dealer.share(playerList, cardDeck, player);
				// 버스트 체크 혹은 블랙잭인지 확인.
				boolean burst = dealer.cardCheck(player, playerList);
				
				if(burst) {
					System.out.println("플레이어의 버스트!");
					computer.setBlackJack(true);
					break;
				}
				// 블랙잭을 맞출 경우.
				else if(player.getBlackJack()) break;
			}
			
			// 2. 컴퓨터의 턴
			System.out.print("카드를 받으시겠습니까?(yes/no) computer >>");
			answer_c =sc.next();
			// 카드를 받는 경우.
			if(answer_c.equals("yes")) {
				
				dealer.share(comList, cardDeck, computer);
				// 버스트 체크 혹은 블랙잭인지 확인.
				boolean burst = dealer.cardCheck(computer, comList);
				
				if(burst) {
					System.out.println("컴퓨터의 버스트!");
					
					player.setBlackJack(true);
					break;
				}
				// 블랙잭을 맞출 경우.
				else if(computer.getBlackJack()) break;		
			}
			
			// 둘 다 더 이상 카드 받기를 거부했을 때
			if(answer_p.equals("no")&&answer_c.equals("no")) {
				// 두 플레이어의 카드의 수의 합을 연산.
				int sum1 = dealer.sum_set(player, playerList);
				int sum2 = dealer.sum_set(computer, comList);
				// 플레이어가 이긴 경우.
				if(sum1>sum2) {
					win_judge(1,player, computer);
				}
				// 컴퓨터가 이긴 경우.
				else if(sum1<sum2) {
					win_judge(2,player, computer);
				}
				// 무승부
				else {
					win_judge(3,player, computer);
				}			
				break;	
			}
		}
		
		// 누군가 블랙잭을 맞추고 나왔다면 추가수당를 수여.
		if(player.getBlackJack()) {
				win_judge(1,player, computer);
			}
			else if(computer.getBlackJack()) {
				win_judge(2,player, computer);
			}
	}
	
	//플레이어가 이기면 1, 컴퓨터가 이기면 2 이긴 사람에게 추가수당을 준다. 지면 배팅 금액이 0.
	public void win_judge(int num,Player player, Player computer) {
		if(num == 1) {
				
			computer.setBetting(0);
			int plus = player.getBetting()*2;
			player.setCash(player.getCash()+plus);
			player.setBetting(0);
		}
		else if(num == 2) {
			
			player.setBetting(0);
			int plus = computer.getBetting()*2;
			computer.setCash(computer.getCash()+plus);
			computer.setBetting(0);
		}
		else if(num == 3) {
			System.out.println("무승부입니다.");
			player.setBetting(0);
			computer.setBetting(0);
		}
	}
	
	// 랜덤으로 분배한 카드를 초기화(회수).
	public void game_init(Player player, Player computer) {
		
		for (int i = 0; i < playerList.size();) {
			playerList.remove(i);
		}
		for (int i = 0; i < comList.size();) {
			comList.remove(i);
		}
		player.setBlackJack(false);
		computer.setBlackJack(false);
	}
	
}
