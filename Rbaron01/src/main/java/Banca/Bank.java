package Banca;

import java.io.Serializable;

public class Bank implements Serializable {

	private double money = 100;
	
	public boolean canPurchase(double price) {
		
//		if(exchange.equals("USD")) {
//			this.money *= 1.3;
//			price *= 1.3;
//		}
//		
//		if(exchange.equals("DOL")) {
//			this.money *= 1.22;
//			price *= 1.22;
//		}
//		
		return money > price;
			
	}
	
public boolean canPurchase(double price, String exchange) {
		
//		if(exchange.equals("USD")) {
//			this.money *= 1.3;
//			price *= 1.3;
//		}
//		
//		if(exchange.equals("DOL")) {
//			this.money *= 1.22;
//			price *= 1.22;
//		}
	
		double rate = 1; 
		switch(exchange) {
		
			case "USD": rate = 1.33;
			case "GBP": rate = 1.55;
			case "INR": rate = 1.88;
			case "AUD": rate = 0.76;
			case "CAD": rate = 0.77;
			default:
				break;
		}
	
		money *= rate;
		price *= rate;
//		
		return money > price;
			
	}
	
}
