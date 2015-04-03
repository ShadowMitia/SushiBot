import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

	private static Boolean[] bar; // La contrainte sur bar, est que le nombre
							// de clients doit ��tre compris entre 1 et
							// 6. Le bool��en d��crit le fait que le client
							// assis �� la place i soit servi ou non.

	private IA ia;

	private HashMap<String, Integer> ingredients;
	
	private HashMap<String, Integer> prices;

	private int cash;

	private int[] cashPile;
	
	private HashMap<String, Integer> orders;
	

	public Manager() throws AWTException {

		this.bar = new Boolean[6];
		for (int i = 0; i < 6; i++)
			this.bar[i] = false;

		this.ia = new IA(ToolBox.ga);

		this.ingredients = new HashMap<String, Integer>();

		this.ingredients.put("Shrimp", 5);
		this.ingredients.put("Rice", 10);
		this.ingredients.put("Nori", 10);
		this.ingredients.put("FishEggs", 10);
		this.ingredients.put("Salmon", 5);
		this.ingredients.put("Unagi", 5);
		
		this.prices = new HashMap<String, Integer>();
		this.prices.put("Shrimp", 250);
		this.prices.put("Rice", 100);
		this.prices.put("Nori", 100);
		this.prices.put("FishEggs", 200);
		
		this.cash = 0;
		this.cashPile = new int[6];
		this.orders = new HashMap<String, Integer>();

	}

	public void CheckBar() throws AWTException, InterruptedException {

		System.out.println("Argent :" + this.cash);
		
		ArrayList<BufferedImage> row;

		row = this.ia.loadSprites();

		
		
//		for (int i = 0; i < 6; i++) {
//
//			if (!this.bar[i]) {
//				new IMGRecognition().writeImage(row.get(i), "spr" + Integer.toString(i));
//				
//				this.cash += this.cashPile[i];
//				this.cashPile[i] = 0;
//				
//				String currentSushi = this.ia.getSushi(row.get(i));
//				
//				if(currentSushi != "void")System.out.println(i + " : " + currentSushi);
//
//				if (currentSushi != "void") {
//					if (makeSushi(currentSushi, i)) this.bar[i] = true;
//				}
//				
//				this.ia.click("plate" + Integer.toString(i+1));
//
//			}
//			else {
//				
//				new IMGRecognition().writeImage(row.get(i), "spr" + Integer.toString(i));
//				String currentSushi = this.ia.getSushi(row.get(i));
//				
//				if(currentSushi == "void")this.bar[i] = false;
//				
//			}
//
//		}
		
		for(int i = 0; i < 6; i++) {
			
			if(! this.bar[i] && this.ia.getSushi(row.get(i)) != "void") {
				this.cash += this.cashPile[i]; this.cashPile[i] = 0;
				this.orders.put(this.ia.getSushi(row.get(i)), i);
			}
			else if(this.bar[i] && this.ia.getSushi(row.get(i)) == "void")this.bar[i] = false;
			// si un client n'est pas servi et qu'il commande quelque chose, on l'ajoute à la liste des sushis à envoyer
			
		}

	}

	private Boolean makeSushi(String sushiName, int i) throws AWTException,
			InterruptedException {

		if (sushiName == "onigiri") {
				this.cashPile[i] += 60;
				this.ingredients.put("Rice", this.ingredients.get("Rice") - 2);
				this.ingredients.put("Nori", this.ingredients.get("Nori") - 1);
				ia.click("Nori");
				ia.click("Rice");
				ia.click("Rice");
				ia.click("SushiMakerZone");
				Thread.sleep(1800);
				return true;

		} else if (sushiName == "gunkan") {

			
				this.cashPile[i] += 120;
				this.ingredients.put("Rice", this.ingredients.get("Rice") - 1);
				this.ingredients.put("Nori", this.ingredients.get("Nori") - 1);
				this.ingredients.put("FishEggs",
						this.ingredients.get("FishEggs") - 2);
				ia.click("Rice");
				ia.click("FishEggs");
				ia.click("FishEggs");
				ia.click("Nori");
				ia.click("SushiMakerZone");
				Thread.sleep(1800);
				return true;


		} else if (sushiName == "roll") {

				this.cashPile[i] += 80;
				this.ingredients.put("Rice", this.ingredients.get("Rice") - 1);
				this.ingredients.put("Nori", this.ingredients.get("Nori") - 1);
				this.ingredients.put("FishEggs",
						this.ingredients.get("FishEggs") - 1);
				ia.click("Rice");
				ia.click("FishEggs");
				ia.click("Nori");
				ia.click("SushiMakerZone");
				Thread.sleep(1800);
				return true;
		}
			 else
			return false;
	}

	
	public void buyComponent() throws AWTException, InterruptedException{
		
		for(String s : this.ingredients.keySet()){
				
				if(s == "Rice" && this.cash >= 100) {
					this.ia.click("Telephone");
					this.ia.click("Tel_Rice");
					this.ingredients.put(s , this.ingredients.get(s) + 10);
					this.ia.click("Checkout");
					this.cash -= this.prices.get(s);
					
				}
				else if(s == "Nori" && this.cash >= 100) {
					this.ia.click("Telephone");
					this.ia.click("Tel_Nori");
					this.ingredients.put(s , this.ingredients.get(s) + 10);
					this.ia.click("Checkout");
					this.cash -= this.prices.get(s);
				}
				else if(s == "FishEggs" && this.cash >=200) {
					this.ia.click("Telephone");
					this.ia.click("Tel_FishEggs");
					this.ingredients.put(s , this.ingredients.get(s) + 10);
					this.ia.click("Checkout");
					this.cash -= this.prices.get(s);
					
				}
				
			
			
		}
	Thread.sleep(4000);	
	}
	
}