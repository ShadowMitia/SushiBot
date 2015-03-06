import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;

import java.awt.event.InputEvent;
import java.awt.event.MouseListener;


public class GameWindow {
	
	public static void main(String [] args){
		
		ClicButton clic = new ClicButton(new File("sprites.txt"));
		clic.clique("start");
		clic.clique("continue");
		clic.clique("skip");
		clic.clique("continue");
		clic.clique("rice");
		}
	
	private BufferedImage img;
	
	public GameWindow(){
		
		try{
			Desktop d = Desktop.getDesktop();
			d.browse(new URI("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf"));		
			Thread.sleep(9000);	
			this.img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(img, "png", new File("Screen.png"));
		}catch (Exception e){System.out.println("Exception: " + e);};
		
	}
	
	//on recupere le x du bord gauche de la fenêtre du jeu
	
	public int xGauche(BufferedImage img){
		int x = 0, tmp = 0, compteur = 0;
		int[] line = new int[img.getWidth()];
		for(int i = 0; i< img.getWidth(); i++){
			line[i] = img.getRGB(i, img.getHeight()/2);
		}
		for(int i = 0; i < line.length/2; i++){
			if(line[i] == 0xFF000000){
				tmp ++;
				//System.out.println("noir" + i);
			}
			else if(tmp > compteur && line[i] != 0xFF000000){
				compteur = tmp;
				x = i;
			}
			else tmp = 0;
			
		}
		x--; // le tableau commence à 0 mais pas les pixels de l'image
		//System.out.println(x);
		
		
		return x;
	}
	
	public int xDroit(BufferedImage img){
		
		int x = 0, tmp = 0, compteur = 0;
		int[] line = new int[img.getWidth()];
		for(int i = 0; i< img.getWidth(); i++){
			line[i] = img.getRGB(i, img.getHeight()/2);
		}
		
		for(int i = img.getWidth()-1; i > line.length/2; i--){
			
			if(line[i] == 0xFF000000){
				tmp ++;
				//System.out.println("noir" + i);
			}
			else if(tmp > compteur && line[i] != 0xFF000000){
				compteur = tmp;
				x = i;
			}
			else tmp = 0;
			
		}
		x++; // le tableau commence à 0 mais pas les pixels de l'image
		//System.out.println(x);
		
		
		return x;
	}
	
	public int yHaut(BufferedImage img){
		int x = this.xGauche(img);
		//int y = 0;
		int j = img.getHeight()/2;
		
		while(img.getRGB(x, j) == 0xFF000000 && j > 0)
			j--;
		
		//System.out.println(j);
		return j+1 ;
	}
	public BufferedImage returnCropped(BufferedImage img){ 
		int hauteur = (3*(this.xDroit(img)-this.xGauche(img))/4);
		BufferedImage gameWindow = this.img.getSubimage(this.xGauche(img), this.yHaut(img), this.xDroit(img)-this.xGauche(img), hauteur);
		
		try {
			ImageIO.write(gameWindow, "png", new File("ScreenCrop.png"));
		} catch (IOException e) {
		}
		return gameWindow;
		
	}
	
	public BufferedImage getImg(){return this.img;}
	public int getWindowH(){return (3*(this.xDroit(img)-this.xGauche(img))/4);}
	public int getWindowW(){return this.xDroit(img)-this.xGauche(img);}
	public Point getDepart(){
		return new Point(this.xGauche(this.img), this.yHaut(this.img));
		}
}
