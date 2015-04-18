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


public class Game {
	/*constructeur
	 * prend l'url du jeu pour debuter
	 */

	private String youwin1 = "/Users/sofiane/desktop/L2/S4/POO/projet/sprites/youwin.png";
	private String youwin2 = "/Users/sofiane/desktop/L2/S4/POO/projet/sprites/youWin2.png";

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
	public static BufferedImage getScreen(int x){
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
			ImageIO.write(image, "png", new File("/Users/sofiane/desktop/L2/S4/POO/projet/sprites/screenshot.png"));
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

	public boolean youWinLevel1(){

		FindPicture test1 = new FindPicture(Game.getScreen(100),this.youwin1); // changé : le this.getScreen en Game.getScreen
		if(test1.checkImage())return true ;
		return false ;

	}

	public boolean youWinLevel2(){

		FindPicture test1 = new FindPicture(Game.getScreen(100),this.youwin2); // changé : le this.getScreen en Game.getScreen
		if(test1.checkImage())return true ;
		return false ;

	}
	public Game getGame () {
		return this ; 
	}			

	public void startLevel1(Game g , Client c ) throws AWTException, InterruptedException{
		g.start();
		g.continuee();
		g.skip();
		g.continuee();

		while (!g.youWinLevel1()){
			Thread.sleep(0);
			c.update();
			c.check();
		}
	}

	public void startLevel2 (Game g , Client c ) throws AWTException, InterruptedException{
		g.continue2();
		g.continue2();
		Recette.resetAliments();
		while (!g.youWinLevel2()){
			//				Thread.sleep(0);
			c.update();
			c.check();
		}
	}

	public void startLevel3( Game g , Client c ) throws AWTException, InterruptedException{
		g.continue2();
		g.continue2();
		Recette.resetAliments();
		while (!g.youWinLevel2()){
			Thread.sleep(0);
			c.update();
			c.check();
		}
	}



	public static void main (String [] args) throws AWTException, InterruptedException {
		//Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
		Game g = new Game ("http://redcloud.fr/sushi.swf");
		Client c = new Client() ; 
		g.startLevel1(g, c);
		g.startLevel2(g, c);
		g.startLevel3(g, c);
	}
}



