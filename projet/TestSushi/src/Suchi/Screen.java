package TestSushi.src.Suchi;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Screen {

	private Robot bot;
	private BufferedImage img;
	private Point departGauche;
	private Point departDroit;

	/**
	 *  Constructeur de la classe
	 * @param x le temps de délais avant la capture de l'écran
	 * @throws Exception
	 */
	public Screen(int x) throws Exception {
		Thread.sleep(1000 * x);
		this.img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		this.departGauche = new Point((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
		this.departDroit = new Point(5 * (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 6, this.departGauche.y);
	}

	/**
	 * La fonction ci-dessous convertit un "pixel numérique", en rouge, vert, bleu. On ignore l'alpha.
	 * @param x coordonnée x à convertir
	 * @param y coordonnée y à convertir
	 * @return le pixel convertit
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
	 *
	 * @param name
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
	 * La fonction renvoie la coordonnée x du premier pixel dont la couleur est différente du pixel de départ.
	 * Elle parcourt l'image dans la largeur dans la direction indiquée.
	 * @param direction La direction de parcourt: soit e pour vers la droite et w pour vers la gauche
	 * @param x la ligne à vérifier
	 * @return la coordonnée x du premier pixel différent
	 */
	public int lookHorizontal(char direction, int x) {
		if (direction == 'e') {
			if (this.img.getRGB(this.departGauche.x, this.departGauche.y) != this.img.getRGB(x, this.departGauche.y))
				return x;
			else {
				if (direction == 'e') return lookHorizontal(direction, x + 1);
				else return lookHorizontal(direction, x - 1);
			}
		} else {
			if (this.img.getRGB(this.departDroit.x, this.departDroit.y) != this.img.getRGB(x, this.departDroit.y))
				return x;
			else {
				if (direction == 'e') return lookHorizontal(direction, x + 1);
				else return lookHorizontal(direction, x - 1);
			}
		}
	}

	/**
	 * La fonction renvoie la coordonnée y du premier pixel dont la couleur est différente du pixel de départ.
	 * Elle parcourt l'image sur la hauteur dans la direction indiquée.
	 * @param direction peut être soit n pour regarder vers le haut ou s pour vers le bas
	 * @param y Donne la coordonnée y du premier pixel de couleur différente du pixel original
	 * @return
	 */
	public int lookVertical(char direction, int y) {
		
		if(this.img.getRGB(this.departGauche.x, this.departGauche.y) != this.img.getRGB(this.departGauche.x, y))return y;
		else {
			
			if(direction == 'n') return lookVertical(direction, y - 1);
			else return lookVertical(direction, y + 1 );
			
		}
		
	}

	/**
	 *
	 * @return
	 */
	public BufferedImage getImg (){
		
		return this.img;
		
	}

	/**
	 *
	 * @return la coordonnée du point en à gauche
	 */
	public Point getDepartGauche () {
		
		return this.departGauche;
		
	}

	/**
	 *
	 * @return la coordonnée du point en haut à droite
	 */
	public Point getDepartDroit () {
		
		return this.departDroit;
		
	}

	/**
	 * Récupére les coordonnées de la zone de jeu dans l'écran
	 * @return un tableau des coordonnées de la zone de jeu
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
	 *  Méthode qui crée un screenshot de la zone de jeu
	 * @param name le fichier dans lequel stocker le screenshot
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

