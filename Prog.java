import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Prog {
	public static void main(String[] args) throws Exception {
		
		ToolBox toolbox = new ToolBox(250, 150);
		
	}

	
	
	
	/*
	public static void main(String []args) throws Exception {
	
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(
					"http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf"));
		} catch (URISyntaxException | IOException e) {
		}
		
		Robot bot = new Robot();
		bot.mouseMove(0, 0);
		Screen screenshot = new Screen(10); // le parametre est le temps avant le screen en secondes;
		screenshot.saveImage("Screenshot1");
		screenshot.saveGameArea("Screen_reduit2");
		
		
	}
	
	*/
	
	
}