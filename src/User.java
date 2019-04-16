import java.util.ArrayList;
import javalib.worldimages.*;

public class User {
	String username;
	String password;
	ArrayList<Stock> stocks;
	double balance;
	Utils utils;
	
	User(String username, String password, ArrayList<Stock> stocks, double balance) {
		this.username = username;
		this.password = password;
		this.stocks = stocks;
		this.balance = balance;
		this.utils = new Utils();
	}
	
	public boolean login(String username, String password) {
		return this.username.toLowerCase().equals(username.toLowerCase())
			&& this.password.equals(password);
	}
	
	public double portfolioValuation() {
		double x = this.balance;
		for(Stock item : this.stocks) {
			x += item.getPrice();
		}
		return x;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public Stock sellStock(String sym, int num) {
		Stock temp = new Stock(0, "", "");
		if(num > 0) {
			for(Stock item : this.stocks) {
				if(item.sameStock(sym)) {
					return item;
				}
			}
		}
		return temp;
	}
	
	public int sellOrder(String sym, int num) {
		int tempNum = num;
		int sold = 0;
		for(Stock item : this.stocks) {
			if(item.sameStock(sym)) {
				this.balance += item.sellStock();
				tempNum --;
				item = null;
				sold ++;
			}
			
			if(tempNum == 0) break;
		}
		return sold;
	}
	
	public ArrayList<Stock> buyOrder(String sym, int num, ArrayList<Stock> stocks) {
		for (int i = num; i > 0; i--) {

		}
		return stocks;
	}
	
	public WorldImage makePortfolio() {
		return new BesideImage(this.utils.drawSymbol(this.stocks),
				this.utils.drawQuantity(this.stocks), this.utils.drawPrice(this.stocks));
	}
	
}
