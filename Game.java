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
	
	public void Start (String srcPath) throws AWTException{
		Robot me = new Robot();
		BufferedImage game = this.getScreen(10000);
		FindPicture test = new FindPicture(game,srcPath);
		test.checkLine(test.getSrc(), test.getImg());
		test.checkColumn(test.getSrc(), test.getImg());
		if (test.getX()==-1 || test.getY()==-1)throw new RuntimeException("Sprite non detecté");
		else {
		//System.out.println("is line working ? "+test.getY());
		//System.out.println("is column working ? "+test.getX());
		me.delay(1000);
		me.mouseMove(0,0);
		me.mouseMove(test.getX(), test.getY());
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		}
	     }
	
}




/////////////////////////////////
// pour tester cette classe    //////////////////////////////////////////////////////
//Game g = new Game("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");//
//BufferedImage img = g.getScreen(12000);////////////////////////////////////////////
//g.createScreenImage(img);				 //
///////////////////////////////////////////
