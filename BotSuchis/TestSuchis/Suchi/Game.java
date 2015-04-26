package Suchi;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import Pictures.FindPicture;

/////////////////////////////////////////////////////////////////////////////
// Une classe Game qui demarre un jeu 									   //
//Liste des M��thodes : 													   //
// getscreen(int x) ---> renvoit un screen apres x milisecondes	(cadr�� sur //
// l'image)																   //
// createScreenImage(BUfferedImage img) ---> cree un png du screen (sert   //
//  �� tester)															   //
/////////////////////////////////////////////////////////////////////////////

///////////////////////////
// created 04/02/2015 	 //
// Last update : 04/02/15//
///////////////////////////


public class Game {
	/*constructeur
	 * prend l'url du jeu pour debuter
	 */
	
	private String youwin = "Sprites/youwin.png";
	public Game(String url){
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));		
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		}
	}

	/*methode qui prend un screen apres x temps
	 * renvoit un screen deja cadr�� sur le jeu  
	 */
	public static BufferedImage getScreen(int x){
		BufferedImage img	 = null ; 

		try{
			Thread.sleep(x);	
			img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch (Exception e){} ;

		if (img==null) throw new RuntimeException("pas d'image");
		return img;//.getSubimage(img.getWidth()/8,img.getHeight()/12,img.getWidth()-2*img.getWidth()/8,img.getHeight()-img.getHeight()/12);

	}

	/*methode qui cr��e l'image du screen (png)
	 * prend en param une BufferedImage
	 */
	public void createScreenImage (BufferedImage image){
		try {
			ImageIO.write(image, "png", new File("/Users/sofiane/desktop/screenshot.png"));
		} catch (IOException e) {
		}
	}

	public void start() throws AWTException, InterruptedException{
		Robot me = new Robot();
		Thread.sleep(10000);
		//me.mouseMove(0,0);
		me.mouseMove(621,344);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void continuee () throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(649,663);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void continue2() throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(649,635);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void skip () throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(1000,722);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public boolean youWin(){
		
		FindPicture test1 = new FindPicture(this.getScreen(100),this.youwin);
		if(test1.checkImage())return true ;
		return false ;
		
	}
	 public Game getGame () {
		 return this ; 
	 }



	public static void main (String [] args) throws AWTException, InterruptedException {
		Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
		g.start();
		g.continuee();
		g.skip();
		g.continuee();
		Client c = new Client() ; 
		boolean win = false ; 
		while (!win){
		//Thread.sleep(0);
		c.update();
		c.check();
		win = g.youWin();
		System.out.println(win);
		//c.clearTable();
		}
		win = false ; 
		System.out.println("you win !!"+win);
		g.continue2();
		g.continue2();
		Recette.resetAliments();
		while (!win){
			Thread.sleep(0);
			c.update();
			c.check();
			win = g.youWin();
			System.out.println(win);
			//c.clearTable();
			}

		//Onigiri o = new Onigiri();
		//California c = new California();
		//Maki m = new Maki() ; 
	}
}


/**
principale tache �� regler : 
g��rer la caisse, il faut faire gaffe avant de commander on doit avoir assez d'argent sinon on attend 
 */
