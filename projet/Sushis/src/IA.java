package Sushis.src;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class IA extends ToolBox {


	private static final long serialVersionUID = 5210558056865804594L;
	private int width;
	private int height;
	public static int xOr;
	public static int yOr;
	private static int spriteCount = 0;
	private Zone zones;
	private ReadZone bulles;
	private HashMap<BufferedImage, String> sushiReference;

	public IA(Rectangle rec) throws AWTException {

		// super();
		this.width = rec.width;
		this.height = rec.height;
		xOr = rec.x;
		yOr = rec.y;

		// System.out.println("x: " + xOr + ", y : " + yOr + " , width : " +
		// rec.width + " , height :" + rec.height);

		this.zones = new Zone();

		this.zones.addZone("Shrimp", new Point((int)(70. / 1236.) * this.width,
				(int)(644. / 928.) * this.height));
		this.zones.addZone("Rice", new Point((int)(178. / 1236.) * this.width,
				(int)(644. / 928.) * this.height));
		this.zones.addZone("Nori", new Point((int)(70. / 1236.) * this.width,
				(int)(750. / 928.) * this.height));
		this.zones.addZone("FishEggs", new Point((int)(178. / 1236.) * this.width,
				(int)(750. / 928.) * this.height));
		this.zones.addZone("Salmon", new Point((int)(70. / 1236.) * this.width,
				(int)(856. / 928.) * this.height));
		this.zones.addZone("Unagi", new Point((int)(178. / 1236.) * this.width,
				(int)(856. / 928.) * this.height));
		this.zones.addZone("SushiMakerZone", new Point((int)(1. / 4.) * this.width,
				(int)(3. / 4.) * this.height));
		this.zones.addZone("Sake", new Point((int)(5. / 8.) * this.width, (int)(3. / 4.)
				* this.height));
		this.zones.addZone("Telephone", new Point((int)(7. / 8.) * this.width,
				(int)(3. / 4.) * this.height));
		this.zones.addZone("Continue", new Point((int)(1. / 2.) * this.width,
				(int)(1. / 2.) * this.height));
		this.zones.addZone("Continue_Day", new Point((int)(1. / 2.) * this.width,
				(int)(4. / 5.) * this.height));
		this.zones.addZone("Skip", new Point((int)(7. / 8.) * this.width, (int)(10. / 11.)
				* this.height));
		this.zones.addZone("Start", new Point((int)(1. / 2.) * this.width,
				(int)((1. / 2.) * this.height) - 30));

		this.bulles = new ReadZone();
		this.bulles.addZone("Bulle1", new Point((int)(78. / 1236.) * this.width,
				(int)(98. / 928.) * this.height), 64, 69);
		this.bulles.addZone("Bulle2", new Point((int)(273. / 1236.) * this.width,
				(int)(98. / 928.) * this.height), 64, 69);
		this.bulles.addZone("Bulle3", new Point((int)(469. / 1236.) * this.width,
				(int)(98. / 928.) * this.height), 64, 69);
		this.bulles.addZone("Bulle4", new Point((int)(664. / 1236.) * this.width,
				(int)(98. / 928.) * this.height), 64, 69);
		this.bulles.addZone("Bulle5", new Point((int)(860. / 1236.) * this.width,
				(int)(98. / 928.) * this.height), 64, 69);
		this.bulles.addZone("Bulle6", new Point((int)(1055. / 1236.) * this.width,
				(int)(98. / 928.) * this.height), 64, 69);

		this.sushiReference = new HashMap<BufferedImage, String>();
		
		this.sushiReference.put(
				new IMGRecognition().loadImage("Sprits/roll.png"), "roll");
		this.sushiReference
				.put(new IMGRecognition().loadImage("Sprits/onigiri.png"),
						"onigiri");
		this.sushiReference.put(
				new IMGRecognition().loadImage("Sprits/gunkan.png"), "gunkan");

	}

	public void click(String zone) throws AWTException, InterruptedException {
		
		Thread.sleep(10);
		Robot bot = new Robot();
		bot.mouseMove((int) this.zones.getZonesTable().get(zone).x,
				(int) this.zones.getZonesTable().get(zone).y);
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(50);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		Thread.sleep(10);

	}

	public void initiateGame() throws AWTException, InterruptedException {

		click("Start");
		Thread.sleep(100);
		click("Skip");
		Thread.sleep(100);
		click("Continue_Day");

	}

	public Zone getZone() {

		return this.zones;

	}

	public void saveSprites() throws AWTException, IOException {

		File dir = new File(System.getProperty("user.dir") + "/Sprites");
		dir.mkdirs();

		for (int i = 1; i <= 6; i++) {

			String bubbleName = "Bulle" + Integer.toString(i);
			Rectangle rec = this.bulles.getZones().get(bubbleName);
			BufferedImage img = new Robot().createScreenCapture(rec);
			ImageIO.write(img, "png", new File(bubbleName + ".png"));

		}

		spriteCount++;

	}

	public ArrayList<BufferedImage> loadSprites() throws AWTException {

		ArrayList<BufferedImage> row = new ArrayList<BufferedImage>();

		for (int i = 1; i <= 6; i++) {

			Rectangle rect = this.bulles.getZones().get(
					"Bulle" + Integer.toString(i));
			row.add(new Robot().createScreenCapture(rect));

		}
		System.out.println("Successfully Loaded Sprites");
		return row;

	}

	public String getSushi(BufferedImage sushiToTest) {

		IMGRecognition ir = new IMGRecognition();
		
		for (BufferedImage tempImg : this.sushiReference.keySet()) {
			
			if (ir.areTheseImagesEqualInvertMethod(this.sushiReference.get(tempImg),sushiToTest, 75))
				return sushiReference.get(tempImg);
	
		}
		
		
		return "void";
	}

}