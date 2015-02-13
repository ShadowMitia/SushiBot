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

///////////////////////////
// created 04/02/2015 	 //
// Last update : 04/02/15//
///////////////////////////

/**
 * @brief Classe qui s'occupe de gérer le jeu à jouer et initialiser l'I.A.
 * @details [long description]
 * 
 */

public class Game {
	/**
	 * @brief Initialise le jeu à jouer ainsi que l'IA
	 * @details [long description]
	 * 
	 * @param url L'url du jeu à lancer (Exemple: "http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf")
	 * 
	 */
	public Game(String url){
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));		
		} catch (URISyntaxException | IOException e) {
			System.out.println("Exception: " + e);
		}
	}
	
	/**
	 * @brief Méthode qui prend un screen après x temps
	 * @details [long description]
	 * 
	 * @param x temps du delay
	 * @return renvoit la partie de l'écran sur la partie qui détient le jeu
	 */
	public BufferedImage getScreen(int x){
		BufferedImage img	 = null ; 

		try{
			Thread.sleep(x);	
			img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch (Exception e){} ;

		if (img==null) throw new RuntimeException("Erreur: Screenshot non prit");
		return img;//.getSubimage(img.getWidth()/8,img.getHeight()/12,img.getWidth()-2*img.getWidth()/8,img.getHeight()-img.getHeight()/12);
		
	}

	/**
	 * @brief Méthode qui prend la partie de l'écran qui détient le jeu
	 * @details [long description]
	 * 
	 * @param x temps du delay
	 * @return renvoit la partie de l'écran sur la partie qui détient le jeu
	 */
	public BufferedImage getScreen(){
		BufferedImage img	 = null ; 
		try{	
			img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch (Exception e){} ;

		if (img==null) throw new RuntimeException("Erreur: Screenshot non prit");
		return img;//.getSubimage(img.getWidth()/8,img.getHeight()/12,img.getWidth()-2*img.getWidth()/8,img.getHeight()-img.getHeight()/12);
		
	}
	
	/**
	 * @brief Méthode qui prend une image et l'enregistre en PNG
	 * 
	 * @param image Le BufferedImage qui doit être enregistré
	 */
	public void createScreenImage (BufferedImage image){
		try {
			ImageIO.write(image, "png", new File("/home/dimitri/Desktop/screenshot.png"));
		} catch (IOException e) {
		}
	}
	
	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param srcPath [description]
	 */
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

	public static void main(String[] args) {
		Game g = new Game("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
		BufferedImage img = g.getScreen(12000);
		g.createScreenImage(img);
	}
	
}




/////////////////////////////////
// pour tester cette classe    //////////////////////////////////////////////////////
//Game g = new Game("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");//
//BufferedImage img = g.getScreen(12000);////////////////////////////////////////////
//g.createScreenImage(img);				 //
///////////////////////////////////////////
