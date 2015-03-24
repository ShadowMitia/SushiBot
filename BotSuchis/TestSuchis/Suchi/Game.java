package Suchi;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import Pictures.FindPicture;

/////////////////////////////////////////////////////////////////////////////
// Une classe Game qui demarre un jeu 									   //
//Liste des Méthodes : 													   //
// getscreen(int x) ---> renvoit un screen apres x milisecondes	(cadré sur //
// l'image)																   //
// createScreenImage(BUfferedImage img) ---> cree un png du screen (sert   //
//  à tester)															   //
/////////////////////////////////////////////////////////////////////////////

///////////////////////////
// created 04/02/2015 	 //
// Last update : 04/02/15//
///////////////////////////


public class Game {
	/*constructeur
	 * prend l'url du jeu pour debuter
	 */
	public Game(String url){
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));		
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		}
	}

	/*methode qui prend un screen apres x temps
	 * renvoit un screen deja cadré sur le jeu  
	 */
	public BufferedImage getScreen(int x){
		BufferedImage img	 = null ; 

		try{
			Thread.sleep(x);	
			img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch (Exception e){} ;

		if (img==null) throw new RuntimeException("pas d'image");
		return img;//.getSubimage(img.getWidth()/8,img.getHeight()/12,img.getWidth()-2*img.getWidth()/8,img.getHeight()-img.getHeight()/12);

	}

	/*methode qui crée l'image du screen (png)
	 * prend en param une BufferedImage
	 */
	public void createScreenImage (BufferedImage image){
		try {
			ImageIO.write(image, "png", new File("/Users/sofiane/desktop/screenshot.png"));
		} catch (IOException e) {
		}
	}

	public void start() throws AWTException{
		Robot me = new Robot();
		me.delay(10000);
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

	public void skip () throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(1000,722);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}




	public static void main (String [] args) throws AWTException, InterruptedException {
		Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
		g.start();
		g.continuee();
		g.skip();
		g.continuee();
		Client c = new Client() ; 
		while (true){
		Thread.sleep(5000);
		c.update();
		c.check();
		c.clearTable();
		}
		

		//Onigiri o = new Onigiri();
		//California c = new California();
		//Maki m = new Maki() ; 
	}
}


/**
 * il reste à optimiser le traitment des clients --> si un client à déjà été traité pas la peine de le reservir 
 * optimiser le code il y a des fonctions qui pourraient etre raccourcies 
 * et enfin detecter que le niveau 1 est passé et jouer le niveau 2 
 */
