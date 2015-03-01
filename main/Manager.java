package main;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

	private static Boolean[] bar; // La contrainte sur bar, est que le nombre
							// de clients doit être compris entre 1 et
							// 6. Le booléen décrit le fait que le client
							// assis à la place i soit servi ou non.

	private IA ia;

	private HashMap<String, Integer> ingredients;

	private int cash;
	
	private int cashPile;
	
	private Service s1, s2, s3, s4, s5, s6;

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

		s1 = new Service("1");
		s2 = new Service("2");
		s3 = new Service("3");
		s4 = new Service("4");
		s5 = new Service("5");
		s6 = new Service("6");
		
		
		this.cash = 0;
		this.cashPile = 0;

	}

	public void CheckBar() throws AWTException, InterruptedException {

		ArrayList<BufferedImage> row;

		row = this.ia.loadSprites();

		
		for (int i = 0; i < 6; i++) {

			if (!this.bar[i]) {
				new IMGRecognition().writeImage(row.get(i), "spr" + Integer.toString(i));
				
				String currentSushi = this.ia.getSushi(row.get(i));
				
				if(currentSushi != "void")System.out.println(i + " : " + currentSushi);

				if (currentSushi != "void") {
					if (makeSushi(currentSushi)) this.bar[i] = true;
				}

			}

		}

	}

	private Boolean makeSushi(String sushiName) throws AWTException,
			InterruptedException {

		if (sushiName == "onigiri") {

			if ((this.ingredients.get("Rice") >= 2)
					&& (this.ingredients.get("Nori") >= 1)) {
				this.cashPile += 60;
				this.ingredients.put("Rice", this.ingredients.get("Rice") - 2);
				this.ingredients.put("Nori", this.ingredients.get("Nori") - 1);
				ia.click("Nori");
				ia.click("Rice");
				ia.click("Rice");
				ia.click("SushiMakerZone");
				Thread.sleep(1800);
				return true;

			} else
				return false;

		} else if (sushiName == "gunkan") {

			if ((this.ingredients.get("Rice") >= 1)
					&& (this.ingredients.get("Nori") >= 1)
					&& (this.ingredients.get("FishEggs") >= 2)) {

				this.cashPile += 120;
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

			} else
				return false;

		} else if (sushiName == "roll") {

			if ((this.ingredients.get("Rice") >= 1)
					&& (this.ingredients.get("Nori") >= 1)
					&& (this.ingredients.get("FishEggs") >= 1)) {

				this.cashPile += 80;
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

			} else
				return false;

		} else
			return false;
	}

}