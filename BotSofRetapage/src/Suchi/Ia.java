package Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Pictures.FindPicture;
import Pictures.Recon;

public class Ia {

	private HashMap<String, Dot> zones;
	private HashMap<String, Integer> ingredients;
	private HashMap<String, BufferedImage> greyReference;
	private HashMap<String, Rectangle> greyIngredients;
	private HashMap<String, Rectangle> commandes;

	private int width, height;

	public Ia() throws AWTException {

		this.width = Game.getGameZone().width;
		this.height = Game.getGameZone().height;

		zones = new HashMap<String, Dot>();

		zones.put("Continue", new Dot((1. / 2.) * this.width,
				(4. / 5.) * this.height));
		zones.put("Skip", new Dot((7. / 8.) * this.width, (10. / 11.)
				* this.height));
		zones.put("Start", new Dot((1. / 2.) * this.width,
				((1. / 2.) * this.height) - 30.));

		zones.put("Shrimp", new Dot((70. / 1236.) * this.width,
				(644. / 928.) * this.height));
		zones.put("Rice", new Dot((178. / 1236.) * this.width,
				(644. / 928.) * this.height));
		zones.put("Nori", new Dot((70. / 1236.) * this.width,
				(750. / 928.) * this.height));
		zones.put("FishEggs", new Dot((178. / 1236.) * this.width,
				(750. / 928.) * this.height));
		zones.put("Salmon", new Dot((70. / 1236.) * this.width,
				(856. / 928.) * this.height));
		zones.put("Unagi", new Dot((178. / 1236.) * this.width,
				(856. / 928.) * this.height));

		zones.put("SushiMakerZone", new Dot((1. / 4.) * this.width,
				(3. / 4.) * this.height));
		zones.put("Sake", new Dot((5. / 8.) * this.width, (3. / 4.)
				* this.height));
		
		zones.put("Telephone", new Dot((7. / 8.) * this.width, (3. / 4.)
				* this.height));
		zones.put("Tel_Back", new Dot((1113. / 1274.) * this.width, (660. / 959.)
				* this.height));
		zones.put("Tel_Hang", new Dot((1186. / 1274.) * this.width, (660. / 959.)
				* this.height));
		zones.put("Tel_Rice_Back", new Dot((1010. / 1277.) * this.width,
				(667. / 959.) * this.height));
		zones.put("Tel_Rice_Hang", new Dot((1185. / 1277.) * this.width,
				(667. / 959.) * this.height));
		zones.put("Tel_Topping", new Dot((1086. / 1286.) * this.width,
				(552. / 956.) * this.height));
		zones.put("Tel_Rice", new Dot((1086. / 1286.) * this.width,
				(590. / 956.) * this.height));
		zones.put("Tel_Shrimp", new Dot((994. / 1286.) * this.width,
				(551. / 956.) * this.height));
		zones.put("Tel_Nori", new Dot((994. / 1286.) * this.width,
				(551. / 956.) * this.height));
		zones.put("Tel_FishEggs", new Dot((1156. / 1286.) * this.width,
				(551. / 956.) * this.height));
		zones.put("Tel_Salmon", new Dot((940. / 1277.) * this.width,
				(650. / 959.) * this.height));
		zones.put("Tel_Rice_Ingre", new Dot((1086. / 1286.) * this.width,
				(570. / 956.) * this.height));
		zones.put("Checkout", new Dot((760. / 1016.) * this.width,
				(470. / 761.) * this.height));

		zones.put("plate1", new Dot((163. / 1274.) * this.width,
				(413. / 959.) * this.height));
		zones.put("plate2", new Dot((363. / 1274.) * this.width,
				(413. / 959.) * this.height));
		zones.put("plate3", new Dot((563. / 1274.) * this.width,
				(413. / 959.) * this.height));
		zones.put("plate4", new Dot((763. / 1274.) * this.width,
				(413. / 959.) * this.height));
		zones.put("plate5", new Dot((968. / 1274.) * this.width,
				(413. / 959.) * this.height));
		zones.put("plate6", new Dot((1168. / 1274.) * this.width,
				(413. / 959.) * this.height));

		ingredients = new HashMap<String, Integer>();

		this.resetIngredients();

		// coordonn��es relatives �� la zone de jeu des zones d'achat des
		// ingr��dients.
		greyIngredients = new HashMap<String, Rectangle>();
		
		fillGreyTable("greyRice", new Dot((1020. / 1277.) * this.width,
				(507. / 959.) * this.height), (134. / 1277.) * this.width,
				(118. / 959.) * this.height);
		fillGreyTable("greyNori", new Dot((914. / 1277.) * this.width,
				(512. / 959.) * this.height), (142. / 1277.) * this.width,
				(92. / 959.) * this.height);
		fillGreyTable("greyShrimp", new Dot((914. / 1277.) * this.width,
				(402. / 959.) * this.height), (142. / 1277.) * this.width,
				(92. / 959.) * this.height);
		fillGreyTable("greyFishEggs", new Dot((1078. / 1277.) * this.width,
				(512. / 959.) * this.height), (142. / 1277.) * this.width,
				(92. / 959.) * this.height);
		fillGreyTable("greySalmon", new Dot((914. / 1277.) * this.width,
				(622. / 959.) * this.height), (142. / 1277.) * this.width,
				(92. / 959.) * this.height);
		
		greyReference = new HashMap<String, BufferedImage>();

		// remplissage d'un tableau de r��f��rence des images gris��es des
		// ingr��dients.

		commandes = new HashMap<String, Rectangle>();
		fillCommandes("Bulle1", new Dot((78. / 1236.) * this.width,
				(98. / 928.) * this.height),
				64 * (1236. / (double) this.width), 69 * (959. / this.height));
		fillCommandes("Bulle2", new Dot((272. / 1236.) * this.width,
				(98. / 928.) * this.height),
				64 * (1236. / (double) this.width), 69 * (959. / this.height));
		fillCommandes("Bulle3", new Dot((467. / 1236.) * this.width,
				(98. / 928.) * this.height),
				64 * (1236. / (double) this.width), 69 * (959. / this.height));
		fillCommandes("Bulle4", new Dot((662. / 1236.) * this.width,
				(98. / 928.) * this.height),
				64 * (1236. / (double) this.width), 69 * (959. / this.height));
		fillCommandes("Bulle5", new Dot((858. / 1236.) * this.width,
				(98. / 928.) * this.height),
				64 * (1236. / (double) this.width), 69 * (959. / this.height));
		fillCommandes("Bulle6", new Dot((1053. / 1236.) * this.width,
				(98. / 928.) * this.height),
				64 * (1236. / (double) this.width), 69 * (959. / this.height));

	}

	public BufferedImage[] loadCommandes() throws AWTException {

		BufferedImage[] sprites = new BufferedImage[6];

		for (int i = 1; i <= 6; i++) {

			sprites[i - 1] = new Robot().createScreenCapture(commandes
					.get("Bulle" + Integer.toString(i)));

		}

		return sprites;
	}

	public void clearTable() throws Exception {

		for (int i = 1; i <= 6; i++) {

			clickZone("plate" + Integer.toString(i));

		}

	}

	public void fillGreyTable(String name, Dot hautGauche, double width,
			double height) {

		greyIngredients.put(name, new Rectangle(hautGauche.x,
				hautGauche.y, (int) width, (int) height));

	}
	
	public void initGreyReference() throws Exception {
	
		clickZone("Telephone");
		clickZone("Tel_Rice");
		
		greyReference.put("greyRice", new Robot().createScreenCapture(greyIngredients.get("greyRice")));

		clickZone("Tel_Rice_Back");
		clickZone("Tel_Topping");
		
		greyReference.put("greyNori", new Robot().createScreenCapture(greyIngredients.get("greyNori")));
		greyReference.put("greyShrimp", new Robot().createScreenCapture(greyIngredients.get("greyShrimp")));
		greyReference.put("greyFishEggs", new Robot().createScreenCapture(greyIngredients.get("greyFishEggs")));
		greyReference.put("greySalmon", new Robot().createScreenCapture(greyIngredients.get("greySalmon")));
		
		Thread.sleep(10);
		clickZone("Tel_Hang");
		
	}

	public void fillCommandes(String name, Dot hautGauche, double width,
			double height) {

		commandes.put(name, new Rectangle(hautGauche.x, hautGauche.y,
				(int) width, (int) height));

	}

	public Dot getZone(String s) {

		return zones.get(s);

	}

	public void clickZone(String s) throws Exception {

		if (zones.containsKey(s)) {

			Robot robot = new Robot();
			Point point = new Point(getZone(s).x, getZone(s).y);
			robot.mouseMove(point.x, point.y);
			robot.delay(30);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.delay(30);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(30);

		} else
			throw new Exception("Zone inconnue");

	}

	public void resetIngredients() {

		ingredients.clear();
		ingredients.put("Rice", 10);
		ingredients.put("FishEggs", 10);
		ingredients.put("Nori", 10);
		ingredients.put("Salmon", 5);
		ingredients.put("Shrimp", 5);

	}

	public void useIngredient(String s) throws Exception {

		if (ingredients.containsKey(s)) {
			
			if(ingredients.get(s) > 0){
				
			System.out.println("On utilise 1 : " + s);
			int temp = ingredients.get(s);
			ingredients.remove(s);
			ingredients.put(s, temp - 1);
			System.out.println("Il en reste : " + ingredients.get(s));
			checkIngredients();
			
			}
			else{checkIngredients();
			useIngredient(s);}
		}

		else throw new Exception("Ingredient non pr��sent dans la liste."); 

	}

	public void checkIngredients() throws Exception {

		for (String s : ingredients.keySet()) {

			if (ingredients.get(s) < 1){
				buyIngredient(s);
				//Thread.sleep(2000);
				}
		}


	}

	public void buyIngredient(String s) throws Exception {

		Boolean greyedOut = true;
		
		if (ingredients.containsKey(s)) {

			clickZone("Telephone");

			if (s == "Rice") {
				
				clickZone("Tel_Rice");

				/*while (greyedOut) {

					Thread.sleep(200);
					BufferedImage img1 = new Robot().createScreenCapture(greyIngredients.get("grey" + s));
					System.out.println(greyIngredients.get("greyRice").x + "    " + greyIngredients.get("greyRice").y);
					//BufferedImage img2 = new Robot().createScreenCapture(Game.getGameZone());
					BufferedImage img2 = greyReference.get("grey" + s);
					
					new Recon().writeImage(img1, "Imageachercher");
					new Recon().writeImage(img2, "imageref");
					
					greyedOut = !(new Recon().sontEgales(img1, img2, 90));
					//System.out.println(greyedOut);
					//greyedOut = !(new FindPicture(img1, img2).checkImage());
					
					if(greyedOut) Thread.sleep(50000);

				}*/

				clickZone("Tel_Rice_Ingre");

			} else {

				clickZone("Tel_Topping");
				
				/*while (greyedOut) {

					BufferedImage img1 = new Robot()
					.createScreenCapture(greyIngredients.get("grey" + s));

					BufferedImage img2 = greyReference.get("grey" + s);
					//BufferedImage img2 = new Robot().createScreenCapture(Game.getGameZone());

					greyedOut = !(new Recon().sontEgales(img1, img2, 90));
					//greyedOut = !(new FindPicture(img1, img2).checkImage());
					
					//System.out.println(greyedOut);
					if(greyedOut) Thread.sleep(500);

				}*/

				clickZone("Tel_" + s);
				
			}
			clickZone("Checkout");
			
			if(s == "Rice" || s == "Nori" || s == "FishEggs"){
				
				ingredients.put(s, 10);
				
			}
			else {
				
				ingredients.put(s, 5);
				
			}
			
			Thread.sleep(4500);
		} 
		else	
			throw new Exception("Ingr��dient non pr��sent dans la liste.");
	}

}