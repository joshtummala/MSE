import java.awt.Color;

import javalib.funworld.*;
import javalib.worldimages.*;
import tester.Tester;

public class MSESimulation extends World{
	Stock[] stocks;
	User user;
	boolean login;

	String username;
	String password;
	boolean tempUse;

	int width = 1000;
	int height = 750;
	WorldScene background = new WorldScene(this.width, this.height);

	Utils utils;

	MSESimulation(Stock[] stocks, User user) {
		this.stocks = stocks;
		this.user = user;
		this.login = false;
		this.username = "";
		this.password = "";
		this.tempUse = true;
		this.utils = new Utils();
	}

	public void userLogin() {
		this.login = this.user.login(this.username, this.password);
		this.username = "";
		this.password = "";
	}

	@Override
	public WorldScene makeScene() {
		if(this.login) {
			return this.makeStock();
		}
		else {
			return this.background.placeImageXY(this.makeLogin(), this.width/2, this.height/2);
		}
	}

	WorldImage makeLogin() {
		WorldImage textBox = new RectangleImage(200, 20, "outline", Color.black);
		WorldImage userText; 
		if(this.username.equals("")) {
			userText = new TextImage("Enter your username", 15, Color.gray);
		}
		else {
			userText = new TextImage(this.username, 15, Color.black);
		}
		WorldImage passwordText;
		if(this.password.equals("")) {
			passwordText = new TextImage("Enter your password", 15, Color.gray);
		}
		else {
			String tempPass = "";
			int temp = this.password.length();
			while(temp != 0) {
				tempPass = tempPass.concat("*");
				temp --;
			}
			passwordText  = new TextImage(tempPass, 15, Color.black);
		}
		WorldImage tempBox1 = userText.overlayImages(textBox);
		WorldImage tempBox2 = passwordText.overlayImages(textBox);
		return new AboveImage(tempBox1, tempBox2);
	}

	WorldScene makeStock() {
		WorldImage name = new TextImage("User: " + this.user.username, 30, Color.black);
		WorldImage balance = new TextImage("Balance: $" +
				Double.toString(this.user.getBalance()), 28, Color.blue);
		WorldImage value = new TextImage("Portfolio Valuation: $" +
				Double.toString(this.user.portfolioValuation()), 28, Color.blue);
		WorldImage box1 = new RectangleImage(400, 100, "outline", Color.black);
		WorldImage topBox = new AboveImage(name, balance, value).overlayImages(box1);
		WorldImage rect = new RectangleImage(200, 100, "outline", Color.white);
		WorldImage displays = new BesideImage(this.user.makePortfolio(), rect,
				this.makeStockBoard());

		return this.background.placeImageXY(new AboveImage(topBox,
				displays), this.width/2, this.height/5);
	}

	WorldImage makeStockBoard() {
		return new BesideImage(this.utils.drawSymbol(this.stocks),
				this.utils.drawQuantity(this.stocks), this.utils.drawPrice(this.stocks));
	}

	@Override
	public MSESimulation onTick() {
		return this;
	}

	@Override
	public MSESimulation onKeyEvent(String key) {
		if(this.login) {
			return this;
		}
		else {
			return this.loginKeyEvent(key);
		}
	}

	MSESimulation loginKeyEvent(String key) {
		if(key.equals("backspace")) {
			if(this.tempUse && this.username.length() > 0) {
				this.username = this.username.substring(0, this.username.length() - 1);
				return this;
			}
			else if(this.password.length() > 0){
				this.password = this.password.substring(0, this.password.length() - 1);
				return this;
			}
			else {
				return this;
			}
		}
		else if(key.equals("tab")) {
			this.tempUse = !this.tempUse;
			return this;
		}
		else if(key.equals("enter")) {
			this.userLogin();
			return this;
		}
		else if(this.tempUse) {
			this.username = this.username.concat(key);
			return this;
		}
		else {
			this.password = this.password.concat(key);
			return this;
		}
	}

	@Override

	public MSESimulation onMouseClicked(Posn pos, String buttonName) {
		if(!this.login) {
			return this.loginMouse(pos);
		}
		else {
			return this;
		}
	}

	MSESimulation loginMouse(Posn pos) {
		if(pos.x <= (100 + this.width/2) && pos.x >= ((this.width/2) - 100)) {
			if(pos.y <= ((this.height/2) + 20) && pos.y >= (this.height/2)) {
				this.tempUse = false;
				return this;
			}
			else if(pos.y >= ((this.height/2) - 20) && pos.y <= (this.height/2)) {
				this.tempUse = true;
				return this;
			}
			else {
				return this;
			}
		}
		else {
			return this;
		}
	}

	void buyOrder(String sym, int num) {
		this.stocks = this.user.buyOrder(sym, num, this.stocks);
	}

	void sellOrder(String sym, int num) {
		Stock sell = this.user.sellStock(sym, num);
		for(int numSell = this.user.sellOrder(sym, num); numSell > 0; numSell--) {
			Stock[] temp = new Stock[this.stocks.length + 1];
			temp[0] = sell;
			int x = 1;
			for(Stock stock : this.stocks) {
				temp[x] = stock;
				x ++;
			}
			this.stocks = temp;
		}
	}

}

class TestStuff {
	Stock stock1 = new Stock(1.0, "fap", "Fap inc.");
	World test1 = new MSESimulation(new Stock[] {this.stock1}, new User("bob", "abc123", new Stock[] {this.stock1, this.stock1}, 4.0));

	public void testWorld(Tester t) {
		test1.bigBang(1000, 800);
	}
}
