package main;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.Robot; 

public class IA extends ToolBox{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5210558056865804594L;
	private int width;
	private int height;
	public static int xOr;
	public static int yOr;
	
	private ClicZone zones;
	
	
	
	public IA(Rectangle rec) throws AWTException {
		
		//super();
		this.width = rec.width;
		this.height = rec.height;
		xOr = rec.x;
		yOr = rec.y;
		
		System.out.println("x: " + xOr + ", y : " + yOr + " , width : " + rec.width + " , height :" + rec.height);
		
		this.zones = new ClicZone();
		this.zones.addClickZone("Crevette", new Dot((70./1236.)*this.width, (644./928.)*this.height));
		this.zones.addClickZone("Riz", new Dot((178./1236.)*this.width, (644./928.)*this.height));
		this.zones.addClickZone("Nori", new Dot((70./1236.)*this.width, (750./928.)*this.height));
		this.zones.addClickZone("FishEggs", new Dot((178./1236.)*this.width, (750./928.)*this.height));
		this.zones.addClickZone("Saumon", new Dot((70./1236.)*this.width, (856./928.)*this.height));
		this.zones.addClickZone("Unagi", new Dot((178./1236.)*this.width, (856./928.)*this.height));
		this.zones.addClickZone("SushiMakerZone", new Dot((1./4.)*this.width,(3./4.)*this.height));
		this.zones.addClickZone("sake", new Dot((5./8.)*this.width, (3./4.)*this.height));
		this.zones.addClickZone("Telephone", new Dot((7./8.)*this.width, (3./4.)*this.height));
		this.zones.addClickZone("Continue", new Dot((1./2.)*this.width, (1./2.)*this.height));
		this.zones.addClickZone("Continue_Day", new Dot((1./2.)*this.width, (4./5.)*this.height));
		this.zones.addClickZone("Skip",  new Dot((7./8.)*this.width, (10./11.)*this.height));
		this.zones.addClickZone("Start", new Dot((1./2.)*this.width, ((1./2.)*this.height)-30.));
		
		
	}
	
	public void click(String zone) throws AWTException, InterruptedException{
		
		Robot bot = new Robot();
		bot.mouseMove((int)this.zones.getZonesTable().get(zone).x, (int)this.zones.getZonesTable().get(zone).y); 
		bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(50);
		bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		System.out.println(this.zones.getZonesTable().get(zone).x + " " + this.zones.getZonesTable().get(zone).y);
	
	}
	
	public void initiateGame() throws AWTException, InterruptedException{
		
		click("Start");
		Thread.sleep(10);
		click("Skip");
		Thread.sleep(10);
		click("Continue_Day");
		
	}
	
	
	public ClicZone getClicZone(){
		
		return this.zones;
		
	}
	
//	public BufferedImage getSprite(){
//		
//		
//		
//	}
	
	
	
	
}