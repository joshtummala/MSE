import java.awt.Color;

import javalib.worldimages.AboveImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class Utils {
	
	public WorldImage drawSymbol(Stock[] stocks) {
		Stock[] tempStocks = this.uniqueStocks(stocks);
		WorldImage tempImage = new TextImage("Symbol  ", 15, Color.black);
		for(Stock item : tempStocks) {
			if(item == null) break;
			tempImage = new AboveImage(tempImage, item.drawSymbol());
		}
		return tempImage;
	}

	public WorldImage drawQuantity(Stock[] stocks) {
		Stock[] tempStocks = new Stock[stocks.length];
		int temp = 0;
		WorldImage tempImage = new TextImage("  Quantity  ", 15, Color.black);
		for(Stock item : stocks) {
			if(item == null) break;
			if(!item.containsStock(tempStocks)) {
				tempStocks[temp] = item;
				WorldImage x = new TextImage(Integer.toString(
						this.countStock(item, stocks)), 13, Color.black);
				tempImage = new AboveImage(tempImage, x);
				temp ++;
			}
		}
		return tempImage;
	}

	public WorldImage drawPrice(Stock[] stocks) {
		Stock[] tempStocks = this.uniqueStocks(stocks);
		WorldImage tempImage = new TextImage("  Price", 15, Color.black);
		for(Stock item : tempStocks) {
			if(item == null) break;
			tempImage = new AboveImage(tempImage, item.drawPrice());
		}
		return tempImage;
	}


	int countStock(Stock stock, Stock[] stocks) {
		int count = 0;
		for(Stock item : stocks) {
			if(item.sameStock(stock)) {
				count ++;
			}
		}
		return count;
	}

	Stock[] uniqueStocks(Stock[] stocks) {
		Stock[] tempStocks = new Stock[stocks.length];
		int temp = 0;
		for(Stock item : stocks) {
			if(item == null) break;
			if(!item.containsStock(tempStocks)) {
				tempStocks[temp] = item;
				temp ++;
			}
		}
		return tempStocks;
	}
}
