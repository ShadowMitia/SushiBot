package Suchi;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import Pictures.FindPicture;


public class Client  {
	private BufferedImage[] customer ; 
	private String oni , maki , california ; 
	private ArrayList<Sushis> toDo ;
	enum Sushis {
		 CALIFORNIA , 
		 MAKI , 
		 ONIGIRI ; 
	}
	// coordonnées des assietes pour debarasser 
	private final Point a1 = new Point (280,373);
	private final Point a2 = new Point (425,373);
	private final Point a3 = new Point (578,373);
	private final Point a4 = new Point (729,373);
	private final Point a5 = new Point (877,373);
	private final Point a6 = new Point (1031,373);
	

	Client() throws HeadlessException, AWTException{
		this.customer= new BufferedImage[6];
		this.oni = "/Users/sofiane/desktop/oni.png";
		this.maki = "/Users/sofiane/desktop/mak.png";
		this.california="/Users/sofiane/desktop/cali.png";
		this.toDo = new ArrayList<Sushis>();

	}
	
	public void update() throws HeadlessException, AWTException{
		int ecart = 0 ; 
		for (int i = 0; i < this.customer.length; i++) {
			this.customer[i]=new Robot().createScreenCapture(new Rectangle(160+ecart,125,160,100));
			ecart+=160;
			//						 JFrame frame = new JFrame ("test");
			//						 frame.getContentPane().add(new JLabel(new ImageIcon(this.customer[i])));
			//						 frame.pack();
			//						 frame.setVisible(true);
		}

	}
	


	public void check() throws AWTException, InterruptedException{
		for (int i = 0; i < this.customer.length; i++) {
			FindPicture test1 = new FindPicture(this.customer[i],this.maki);
			
			if (!test1.checkImage()) {
				test1.loadPictures(this.california);
				if (!test1.checkImage()) {
					test1.loadPictures(this.oni);
					if (!test1.checkImage()) continue ; 
					else this.toDo.add(Sushis.ONIGIRI); 
				}else this.toDo.add(Sushis.CALIFORNIA);
			}
			else this.toDo.add(Sushis.MAKI);

		}
		this.makeS();
	}
	

	public void makeS() throws AWTException, InterruptedException{
		System.out.println("liste avant un tour "+this.toDo.size());
		for(int i = 0 ; i<this.toDo.size();i++){
			switch(this.toDo.get(i)){
			case ONIGIRI : 
				Onigiri o  = new Onigiri() ; 
				//this.toDo.remove(i);
				break ; 
			case CALIFORNIA :
				California c = new California() ; 
				//.toDo.remove(i);
				break ; 
			case MAKI : 
				Maki m = new Maki() ; 
				//this.toDo.remove(i);
				break ; 
			}
		}
		this.toDo.clear();
		//this.check();
		System.out.println("liste apres un tour "+this.toDo.size());
	}
	
	public void clearTable() throws AWTException{
		Robot me = new Robot();
		me.delay(3000);
		me.mouseMove(this.a1.x, this.a1.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.a2.x, this.a2.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.a3.x, this.a3.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.a4.x, this.a4.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.a5.x, this.a5.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.a6.x, this.a6.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	//		public static void main (String[] args) throws InterruptedException, HeadlessException, AWTException{
	//			Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf") ; 
	//			Thread.sleep(15000);
	//			Client c = new Client() ; 
	//			c.update();
	//			
	//		
	//	}
}	
