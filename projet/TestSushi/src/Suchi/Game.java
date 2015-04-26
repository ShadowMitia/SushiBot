package TestSushi.src.Suchi;

import TestSushi.src.Pictures.FindPicture;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;



public class Game {

	private String youwin1 = "sprites/youwin.png";
	private String youwin2 = "sprites/youWin2.png";

	/**
	 * Constructeur
	 * @param url l'url du jeu
	 */
	public Game(String url){
		try {
			GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));		
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		}
	}

	/**
	 * méthode qui prend un screen après x millisecondes
	 * @param x le temps de délai en millisecondes
	 * @return l'image de la zone de jeu déjà cadré
	 */
	public static BufferedImage getScreen(int x){
		BufferedImage img	 = null ; 

		try{
			Thread.sleep(x);	
			img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch (Exception e){

		}

		if (img==null) throw new RuntimeException("Pas d'image");
		return img;//.getSubimage(img.getWidth()/8,img.getHeight()/12,img.getWidth()-2*img.getWidth()/8,img.getHeight()-img.getHeight()/12);

	}

	/**
	 * Méthode qui crée l'image du screen au format png
	 * @param image Un BufferedImage a sauvegardé
	 */
	public void createScreenImage (BufferedImage image){
		try {
			ImageIO.write(image, "png", new File("screenshots/screenshot.png"));
		} catch (IOException e) {
		}
	}

	/**
	 *
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void start() throws AWTException, InterruptedException{
		Robot me = new Robot();
		Thread.sleep(10000);
		//me.mouseMove(0,0);
		me.mouseMove(621,344);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 *
	 * @throws AWTException
	 */
	public void continuee () throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(649,663);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 *
	 * @throws AWTException
	 */
	public void continue2() throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(649,635);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 *
	 * @throws AWTException
	 */
	public void skip () throws AWTException{
		Robot me = new Robot();
		me.delay(100);
		me.mouseMove(0,0);
		me.mouseMove(1000,722);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 *
	 * @return
	 */
	public boolean youWinLevel1(){

		FindPicture test1 = new FindPicture(Game.getScreen(100),this.youwin1); // changé : le this.getScreen en Game.getScreen
		if(test1.checkImage()) return true ;
		return false ;

	}

	/**
	 *
	 * @return
	 */
	public boolean youWinLevel2(){

		FindPicture test1 = new FindPicture(Game.getScreen(100), this.youwin2); // changé : le this.getScreen en Game.getScreen
		if(test1.checkImage())return true ;
		return false ;

	}

	/**
	 *
	 * @param g
	 * @param c
	 * @throws AWTException
	 * @throws InterruptedException
	 */
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

	/**
	 *
	 * @param g
	 * @param c
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void startLevel2 (Game g , Client c ) throws AWTException, InterruptedException{
		g.continue2();
		g.continue2();
		Recette.resetAliments();
		while (!g.youWinLevel1()){
			//				Thread.sleep(0);
			c.update();
			c.check();
		}
	}

	/**
	 *
	 * @param g
	 * @param c
	 * @throws AWTException
	 * @throws InterruptedException
	 */
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
		Game g = new Game ("http://www.miniclip.com/games/sushi-go-round/en/sushigoround.swf");
		Client c = new Client() ; 
		g.startLevel1(g, c);
		g.startLevel2(g, c);
		g.startLevel3(g, c);
	}
}



