import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @brief Classe pour créer des screenshots
 * @details [long description]
 * 
 * @param x [description]
 * @return [description]
 */
public class Screen {

	private Robot bot;
	private BufferedImage img;
	private Dot departGauche;
	private Dot departDroit;


	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param x [description]
	 * @return [description]
	 */
	public Screen(int x) throws Exception {

		Thread.sleep(1000 * x);
		this.img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		this.departGauche = new Dot((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/6, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.departDroit = new Dot(5 * (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/6,this.departGauche.y);

	}

	/*
	 * La fonction ci-dessous convertit un "pixel numérique", en rouge, vert,
	 * bleu. L'alpha nous on s'en tape. Un peu comme en processing ;)
	 */
	/**
	 * @brief Convertit le pixel en RGB
	 * @details [long description]
	 * 
	 * @param x [description]
	 * @param y [description]
	 * 
	 * @return [description]
	 */
	public int[] getPixelARGB(int x, int y) {
		int pixel = this.img.getRGB(x, y);
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		/*System.out.println("argb: " + alpha + ", " + red + ", " + green + ", "
				+ blue);*/
		
		int[] RGB = new int[3];
		RGB[0] = red;
		RGB[1] = green;
		RGB[2] = blue;
		return RGB;
	}

	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param name [description]
	 */
	public void saveImage(String name) {

		try {
			ImageIO.write(this.img, "png", new File(name));
		} catch (IOException e) {
		}

	}
	
	
	/*
	 * La fonction du dessous renvoie le x du premier pixel dont la couleur est différente du pixel de départ, en parcourant
	 * l'image horizontalement, dans le sens de la largeur, dans la direction indiquée par le caractère "direction", avec
	 * e = est, w = west.
	 */
	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param direction [description]
	 * @param x [description]
	 * 
	 * @return [description]
	 */
	public int lookHorizontal(char direction, int x){
		
		if(direction == 'e'){
			if(this.img.getRGB(this.departGauche.x, this.departGauche.y) != this.img.getRGB(x, this.departGauche.y)) return x;
			else {
				
				if(direction == 'e') return lookHorizontal(direction, x+1);
				
				else return lookHorizontal(direction, x-1);
			}
		}
		else {
			if(this.img.getRGB(this.departDroit.x, this.departDroit.y) != this.img.getRGB(x, this.departDroit.y)) return x;
			else {
				
				if(direction == 'e') return lookHorizontal(direction, x+1);
				
				else return lookHorizontal(direction, x-1);
			}
		}
		
		
		
	}
	/* 
	 * La fonction du dessous renvoie le premier pixel dont la couleur est différente du pixel de départ, en parcourant l'image
	 * dans le sens de la hauteur, dans la direction indiquée par le caractère "direction", avec n = nord, s = sud.
	 */
	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param direction [description]
	 * @param y [description]
	 * 
	 * @return [description]
	 */
	public int lookVertical(char direction, int y) {
		
		if(this.img.getRGB(this.departGauche.x, this.departGauche.y) != this.img.getRGB(this.departGauche.x, y))return y;
		else {
			
			if(direction == 'n') return lookVertical(direction, y - 1);
			else return lookVertical(direction, y + 1 );
			
		}
		
	}
	
	
	public BufferedImage getImg (){
		
		return this.img;
		
	}

	public Dot getDepartGauche () {
		
		return this.departGauche;
		
	}
	
	public Dot getDepartDroit () {
		
		return this.departDroit;
		
	}

	/**
	 * @brief [brief description]
	 * @details [long description]
	 * @return [description]
	 */
	public int[] getGameArea(){
		
		int[] res = new int[4]; // 1ere case, x du point haut-gauche, 2eme, y du point haut-gauche, 3eme largeur, 4eme hauteur.
		
		// 1ère étape, récupérer les coordonnées du point haut-gauche.
		
		int xRightBorder = this.lookHorizontal('e', this.getDepartGauche().x);
		// xBorder vaut normalement le x des points haut-gauche et bas-gauche de la fenetre de jeu.
		
		int yTopLeft = this.lookVertical('n', this.getDepartGauche().y) + 2;
		//yTopLeft vaut normalement le y du point haut-gauche. On soustraie 2 car il y a un espace de 1 pixel entre la fenetre de jeu et le navigateur.
		res[0] = xRightBorder;
		res[1] = yTopLeft;
		
		//2ème étape, récupérer la hauteur de la zone, en soustrayant des coordonnées.
		
		int yBottomLeft = this.lookVertical('s', this.getDepartGauche().y);
		
		int height = yBottomLeft - yTopLeft + 2;
		
		System.out.println("ybottomleft" + yBottomLeft + "yTopLeft" + yTopLeft);
		
		// Ensuite on va utiliser le point de départ droit pour calculer la largeur de la zone de jeu.
		
		int xLeftBorder = this.lookHorizontal('w', this.getDepartDroit().x);
		int width = xLeftBorder - xRightBorder + 1;
		
		res[2] = width;
		res[3] = height;
		
		
		
		return res;
		
	}

	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param name [description]
	 */
	public void saveGameArea(String name){
		
		int[] tab = this.getGameArea();
		
		BufferedImage reduc = this.img.getSubimage(tab[0], tab[1], tab[2], tab[3]);
		System.out.println("1: " + tab[0] + "      " + tab[1] + "      " + tab[2] + "      " + tab[3]);
		
		try {
			ImageIO.write(reduc, "png", new File(name));
		} catch (IOException e) {
		}
		
	}	
}

	/**
	 * @brief [brief description]
	 * @details [long description]
	 * 
	 * @param x [description]
	 * @param y [description]
	 * 
	 * @return [description]
	 */
	class Dot {
		
		int x;
	    int y;
		
		public Dot(int x, int y){
			
			this.x = x;
			this.y = y;
			
			
		}
		
		public void setPos(int x, int y) {
			
			this.x = x;
			this.y = y;
			
		}
			
	}
