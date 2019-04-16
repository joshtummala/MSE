import java.awt.Color;
import java.util.ArrayList;
import javalib.worldimages.*;

public class Stock {
	double price;
	String symbol;
	String company;

	Stock(double price, String symbol, String company) {
		this.price = price;
		this.symbol = symbol;
		this.company = company;
	}

	public double getPrice() {
		return this.price;
	}

	public boolean sameStock(String symbol) {
		return this.symbol.equals(symbol);
	}

	public boolean sameStock(Stock other) {
		return this.symbol.equals(other.symbol);
	}

	public double sellStock() {
		return this.price;
	}

	public double buyStock() {
		return this.price;
	}

	public boolean containsStock(ArrayList<Stock> stocks) {
		boolean contains = false;
		for(Stock item : stocks) {
			if(item == null) break;
			contains = contains || this.sameStock(item);

			if(contains) break;
		}
		return contains;
	}

	public WorldImage drawSymbol() {
		return new TextImage(this.symbol, 13, Color.black);
	}
	
	public WorldImage drawPrice() {
		return new TextImage("$" + Double.toString(this.price),
				13, Color.black);
	}

}
