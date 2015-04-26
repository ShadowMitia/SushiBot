package Suchi;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Pictures.Recon;


public class Game {
	/*constructeur
	 * prend l'url du jeu pour debuter
	 */

	private BufferedImage youwin1;
	private String youwin2 = "/sprites/youWin2.png";
	private static Point origin;
	private static Rectangle gameZone;
	private static Rectangle zoneYouWin;
	
	public Game(String url){
		
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(url));		
		} catch (URISyntaxException e) {
		} catch (IOException e) {
		}
		
		
	}

	public void setZoneWin() {
		
		int height = Game.getGameZone().height; int width = Game.getGameZone().width;
		Dot pouet = new Dot((272. / 1236.) * width, (98. / 928.) * height);
		zoneYouWin = new Rectangle(pouet.x, pouet.y, (int)((469. / 1277.)*width), (int)((77./959.)*height));
		
	}
	
	public static Point getOrigin() {
		
		return origin;
		
	}
	public static void setOrigin(Point newOrigin) {
		
		origin = newOrigin;
		
	}
	public static Rectangle getGameZone() {
		
		return gameZone;
		
	}
	public static void setGameZone(Rectangle newGameZone) {
		
		gameZone = newGameZone;
		zoneYouWin = new Rectangle((new Dot(407, 226)).x, (new Dot(407, 226)).y, 469, 77);
		
	}
	
	/*public void gameZone(int temps) throws Exception {
		
		Screen scr = new Screen(temps);
		
		
	}*/
	
	public void startGame() throws Exception{
		
		new Ia().clickZone("Start");
		new Ia().clickZone("Skip");
		new Ia().clickZone("Continue");
		
	}
	public void setYouWin(){
		
		this.youwin1 = new Recon().loadSingleSpriteByPath("sprites/youwin.png");
		
	}
	
	public boolean youWinLevel1() throws AWTException{

		
		return new Recon().sontEgales(this.youwin1,
				new Robot().createScreenCapture(zoneYouWin), 5);

	}

	public boolean youWinLevel2() throws AWTException{


		return new Recon().sontEgales(new Recon().loadSingleSpriteByPath(this.youwin2),
				new Robot().createScreenCapture(zoneYouWin), 5);

	}
	
	public Game getGame () {
		
		return this ;
		
	}			

	public void startLevel1(Client c ) throws Exception{
		
		startGame();
		setYouWin();
		while (!youWinLevel1()){
			Thread.sleep(0);
			c.update();
			c.check();
		}
	}

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
		
		Toolbox tb = new Toolbox(100,300);
		
	}
	
}



