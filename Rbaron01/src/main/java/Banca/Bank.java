package Banca;

public class Bank {
	
	private double money = 100;
	
	
	public boolean canPurchase(double price, String exchange){
		if(exchange.equals("USD")) {
			this.money *= 1.5;
			price *= 1.5;
		}
		if(exchange.equals("GBP")) {
			this.money *= 1.33;
			price *= 1.33;
		}
		return price < money;
	}
}
