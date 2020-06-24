package play;

public class Player {
	
	private double cash;
	private int betting;
	private boolean blackJack;
	String name;
	
	public Player() {
		
	}
	
	public Player(double cash, int betting, boolean blackJack, String name) {
		super();
		this.cash = cash;
		this.betting = betting;
		this.blackJack = blackJack;
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void betAfter(int batting) {
		this.cash -= (double)batting;
	}
	
	public boolean getBlackJack() {
		return blackJack;
	}


	public void setBlackJack(boolean blackJack) {
		this.blackJack = blackJack;
	}



	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public int getBetting() {
		return betting;
	}

	public void setBetting(int betting) {
		this.betting = betting;
	}

	@Override
	public String toString() {
		return "플레이어명 : "+name+" 소지한 돈 : "+cash;
	}

	
}
