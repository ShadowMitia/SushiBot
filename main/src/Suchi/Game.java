package Suchi;
import Pictures.Recon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Game {
	/*constructeur
	 * prend l'url du jeu pour debuter
	 */

	private String youwin1 = "sprites/youwin.png";
	private String youwin2 = "sprites/youwin.png";
	private static Point origin;
	private static Rectangle gameZone;
	public static Rectangle zoneYouWin;

	/**
	 * Constructeur. Point d'entrée du programme, nécessite l'url du jeu à jouer
	 * @param url URL du jeu à jouer
	 */
	public Game(String url){
		
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));		
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		}
		
		
	}

	/**
	 *
	 */
	public static void setZoneWin() {
		
		Dot temp = new Dot((407. / 1236.) * gameZone.width, (226. / 928.) * gameZone.height);
		System.out.println("Debug : x:" + temp.x + " , y:" + temp.y);
		zoneYouWin = new Rectangle(temp.x, temp.y, (int)((469. / 1277.)*gameZone.width), (int)((77./959.)*gameZone.height) );
		
	}

	/**
	 *
	 * @return
	 */
	public static Point getOrigin() {
		
		return origin;
		
	}

	/**
	 *
	 * @param newOrigin
	 */
	public static void setOrigin(Point newOrigin) {
		
		origin = newOrigin;
		
	}

	/**
	 *
	 * @return
	 */
	public static Rectangle getGameZone() {
		
		return gameZone;
		
	}

	/**
	 *
	 * @param newGameZone
	 */
	public static void setGameZone(Rectangle newGameZone) {
		
		gameZone = newGameZone;
		zoneYouWin = new Rectangle((new Dot((407./1236.)*gameZone.width, (226./959.)*gameZone.height)).x, (new Dot((407./1236.)*gameZone.width, (226./959.)*gameZone.height)).y,
				(int)((469./1274.)*gameZone.width), (int)((77./959.)*gameZone.height));
		//setZoneWin();
		
	}

	/**
	 *
	 * @throws Exception
	 */
	public void startGame() throws Exception{
		
		new Ia().clickZone("Start");
		new Ia().clickZone("Skip");
		new Ia().clickZone("Continue");
		
	}

	/**
	 *
	 * @return
	 * @throws AWTException
	 */
	public boolean youWinLevel1() throws AWTException{


		return new Recon().sontEgales(new Recon().loadSingleSpriteByPath(this.youwin2),
				new Robot().createScreenCapture(zoneYouWin), 5);
	}

	/**
	 *
	 * @return
	 * @throws AWTException
	 */
	public boolean youWinLevel2() throws AWTException{


		return new Recon().sontEgales(new Recon().loadSingleSpriteByPath(this.youwin2),
				new Robot().createScreenCapture(zoneYouWin), 5);

	}

	/**
	 *
	 * @param c
	 * @throws Exception
	 */
	public void startLevel1(Client c ) throws Exception{
		
		startGame();
		while (!youWinLevel1()){
			System.out.println("Toujours pas gagné");
			Thread.sleep(0);
			c.update();
			c.check();
		}
	}

	/**
	 *
	 * @param c
	 * @throws Exception
	 */
	public void startLevel2 (Client c ) throws Exception{
		new Ia().clickZone("Continue");
		new Ia().clickZone("Continue");
		new Ia().resetIngredients();
		while (!youWinLevel2()){
			//				Thread.sleep(0);
			c.update();
			c.check();
		}
	}

	/**
	 *
	 * @param c
	 * @throws Exception
	 */
	public void startLevel3(Client c ) throws Exception{
		new Ia().clickZone("Continue");
		new Ia().clickZone("Continue");
		new Ia().resetIngredients();
		while (!youWinLevel2()){
			Thread.sleep(0);
			c.update();
			c.check();
		}
	}



	/*public static void main (String [] args) throws AWTException, InterruptedException {
		//Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
		Game g = new Game ("http://redcloud.fr/sushi.swf");
		Client c = new Client() ; 
		g.startLevel1(g, c);
		g.startLevel2(g, c);
		g.startLevel3(g, c);
	} */
	
	public static void main (String []args) {
		
		Toolbox tb = new Toolbox(75,75);
		
	}
	
}



