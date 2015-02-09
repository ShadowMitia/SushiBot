import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

public class Screen {
	private BufferedImage img;
	private Dot departGauche;
	private Dot departDroit;

	public Screen(int x) throws Exception {
		Thread.sleep(1000 * x);
		this.img = new Robot().createScreenCapture(new Rectangle(Toolkit
				.getDefaultToolkit().getScreenSize()));
		this.departGauche = new Dot((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 10), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
		this.departDroit = new Dot(9 *( (int) Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 10), this.departGauche.y);
	}

	/*
	 * 
	 * La fonction ci-dessous convertit un "pixel numérique", en rouge, vert,
	 * bleu. L'alpha nous on s'en tape. Un peu comme en processing ;)
	 */
	public int[] getPixelARGB(int x, int y) {
		int pixel = this.img.getRGB(x, y);
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		/*
		 * System.out.println("argb: " + alpha + ", " + red + ", " + green +
		 * ", " + blue);
		 */
		int[] RGB = new int[3];
		RGB[0] = red;
		RGB[1] = green;
		RGB[2] = blue;
		return RGB;
	}

	public void saveImage(String name) {
		try {
			ImageIO.write(this.img, "png", new File(name + ".png"));
		} catch (IOException e) {
		}
	}

	/*
	 * La fonction du dessous renvoie le x du premier pixel dont la couleur est
	 * différente du pixel de départ, en parcourant l'image horizontalement,
	 * dans le sens de la largeur, dans la direction indiquée par le caractère
	 * "direction", avec e = est, w = west.
	 */
	public int lookHorizontal(char direction, int x) {
		if (direction == 'e') {
			if ( (this.img.getRGB(this.departGauche.x, this.departGauche.y) != this.img.getRGB(x, this.departGauche.y)) || (x == (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()))
				return x;
			else {
				if (direction == 'e')
					return lookHorizontal(direction, x + 1);
				else
					return lookHorizontal(direction, x - 1);
			}
		} else {
			if ( (this.img.getRGB(this.departDroit.x, this.departDroit.y) != this.img.getRGB(x, this.departDroit.y)) || (x == 0))
				return x;
			else {
				if (direction == 'e')
					return lookHorizontal(direction, x + 1);
				else
					return lookHorizontal(direction, x - 1);
			}
		}
	}

	/*
	 * La fonction du dessous renvoie le premier pixel dont la couleur est
	 * différente du pixel de départ, en parcourant l'image dans le sens de la
	 * hauteur, dans la direction indiquée par le caractère "direction", avec n
	 * = nord, s = sud.
	 */
	public int lookVertical(char direction, int y) {		//si le pixel est different (couleur) ou si on atteint le bord de l'écran
		if ( (y == (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) || (y == 0) || (this.img.getRGB(this.departGauche.x, this.departGauche.y) != this.img.getRGB(this.departGauche.x, y)))
			return y;
		else {
			if (direction == 'n')
				return lookVertical(direction, y - 1);
			else
				return lookVertical(direction, y + 1);
		}
	}

	public BufferedImage getImg() {
		return this.img;
	}

	public Dot getDepartGauche() {
		return this.departGauche;
	}

	public Dot getDepartDroit() {
		return this.departDroit;
	}

	public int[] getGameArea() {
		int[] res = new int[4]; // 1ere case, x du point haut-gauche, 2eme, y du
								// point haut-gauche, 3eme largeur, 4eme
								// hauteur.
		// 1ère étape, récupérer les coordonnées du point haut-gauche.
		int xRightBorder = this.lookHorizontal('e', this.getDepartGauche().x);
		// xBorder vaut normalement le x des points haut-gauche et bas-gauche de
		// la fenetre de jeu.
		int yTopLeft = this.lookVertical('n', this.getDepartGauche().y) + 2;
		// yTopLeft vaut normalement le y du point haut-gauche. On soustraie 2
		// car il y a un espace de 1 pixel entre la fenetre de jeu et le
		// navigateur.
		res[0] = xRightBorder;
		res[1] = yTopLeft;
		// 2ème étape, récupérer la hauteur de la zone, en soustrayant des
		// coordonnées.
		int yBottomLeft = this.lookVertical('s', this.getDepartGauche().y) - 1;
		int height = Math.abs(yBottomLeft - yTopLeft) + 2;
		// System.out.println("ybottomleft" + yBottomLeft + "yTopLeft" +
		// yTopLeft);
		// Ensuite on va utiliser le point de départ droit pour calculer la
		// largeur de la zone de jeu.
		int xLeftBorder = this.lookHorizontal('w', this.getDepartDroit().x);
		int width = Math.abs(xLeftBorder - xRightBorder) + 1;
		res[2] = width;
		res[3] = height;
		return res;
	}

	public void saveGameArea(String name) throws FileNotFoundException,
			UnsupportedEncodingException {
		int[] tab = this.getGameArea();
		BufferedImage reduc = this.img.getSubimage(tab[0], tab[1], tab[2],tab[3]);
		// System.out.println("1: " + tab[0] + " " + tab[1] + " " + tab[2] + " "
		// + tab[3]);
		try {
			ImageIO.write(reduc, "png", new File(name + ".png"));
		} catch (IOException e) {
		}
	}
}

class Dot {
	int x;
	int y;

	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}