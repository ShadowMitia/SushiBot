import java.awt.Desktop;
import java.awt.Robot;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Prog {

	
	
	
	
	public static void main(String []args) throws Exception {
	
		
		
		try {
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(
					"http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf"));
		} catch (URISyntaxException | IOException e) {
		}
		
		Robot bot = new Robot();
		bot.mouseMove(0, 0);
		Screen screenshot = new Screen(3); // le parametre est le temps avant le screen en secondes;
		screenshot.saveImage("Screenshot1");
		screenshot.saveGameArea("Screen_reduit");
		int[] tab = new int[4];
		tab = screenshot.getGameArea();
		
		ToolBox toolbox = new ToolBox(200, 300, new GameArea(tab[0], tab[1], tab[2], tab[3]));
		
		
	}
	
	
	
	
}