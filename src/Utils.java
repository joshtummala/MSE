import java.awt.Color;
import java.util.ArrayList;
import javalib.worldimages.AboveImage;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;

public class Utils {
	
	public WorldImage drawSymbol(ArrayList<Stock> stocks) {
		ArrayList<Stock> tempStocks = this.uniqueStocks(stocks);
		WorldImage tempImage = new TextImage("Symbol  ", 15, Color.black);
		for(Stock item : tempStocks) {
			if(item == null) break;
			tempImage = new AboveImage(tempImage, item.drawSymbol());
		}
		return tempImage;
	}

	public WorldImage drawQuantity(ArrayList<Stock> stocks) {
		ArrayList<Stock> tempStocks = new ArrayList<Stock>();
		int temp = 0;
		WorldImage tempImage = new TextImage("  Quantity  ", 15, Color.black);
		for(Stock item : stocks) {
			if(item == null) break;
			if(!item.containsStock(tempStocks)) {
				tempStocks.add(item);
				WorldImage x = new TextImage(Integer.toString(
						this.countStock(item, stocks)), 13, Color.black);
				tempImage = new AboveImage(tempImage, x);
				temp ++;
			}
		}
		return tempImage;
	}

	public WorldImage drawPrice(ArrayList<Stock> stocks) {
		ArrayList<Stock> tempStocks = this.uniqueStocks(stocks);
		WorldImage tempImage = new TextImage("  Price", 15, Color.black);
		for(Stock item : tempStocks) {
			if(item == null) break;
			tempImage = new AboveImage(tempImage, item.drawPrice());
		}
		return tempImage;
	}


	int countStock(Stock stock, ArrayList<Stock> stocks) {
		int count = 0;
		for(Stock item : stocks) {
			if(item.sameStock(stock)) {
				count ++;
			}
		}
		return count;
	}

	ArrayList<Stock> uniqueStocks(ArrayList<Stock> stocks) {
		ArrayList<Stock> tempStocks = new ArrayList<Stock>();
		for(Stock item : stocks) {
			if(item == null) break;
			if(!item.containsStock(tempStocks)) {
				tempStocks.add(item);
			}
		}
		return tempStocks;
	}
}
