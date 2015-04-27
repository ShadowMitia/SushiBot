package Suchi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen {
	private BufferedImage img;
	private Dot departGauche;
	private Dot departDroit;

	/**
	 * Constructeur.
	 * @param x Le délai avant de prendre la capture de l'écran
	 * @throws Exception
	 */
	public Screen(int x) throws Exception {

		Thread.sleep(x);

		this.img = new Robot().createScreenCapture(new Rectangle(Toolkit
				.getDefaultToolkit().getScreenSize()));

		this.departGauche = new Dot(0, (int) Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 2, false);

		this.departDroit = new Dot(Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() - 1, this.departGauche.y, false);

	}

	/**
	 * Méthode qui permet de sauvegarder l'image dans un fichier au format .png
	 * @param name Le nom du fichier dans lequel sauvegardé
	 */
	public void saveImage(String name) {

		try {

			ImageIO.write(this.img, "png", new File(name + ".png"));

		} catch (IOException e) {
			System.out.println("Write failed");
		}
	}

	/**
	 * La méthode renvoie le x du premier pixel dont la couleur est différente du pixel de départ, en parcourant
	 * l'image horizontalement, dans le sens de la largeur, dans la direction indiquée par le caractère
	 * "direction", avec e = est, w = west.
	 *
	 * @param direction "e" ou "w"
	 * @param x int le pixel sur lequel commençé
	 * @return int le pixel différent trouvé
	 */
	public int lookHorizontal(char direction, int x) {

		if (direction == 'e') {
			if (x < (int) Toolkit.getDefaultToolkit().getScreenSize()
					.getWidth()
					&& this.img.getRGB((int) this.departGauche.x,
							(int) this.departGauche.y) == this.img.getRGB(
							(int) x, (int) this.departGauche.y)) {

				return lookHorizontal(direction, x + 1);
			} else
				return x;
		} else {
			if (x > 0
					&& this.departDroit.x > 0 &&  this.img.getRGB((int) this.departDroit.x,
							(int) this.departDroit.y) == this.img.getRGB(
							(int) x, (int) this.departDroit.y)) {
				return lookHorizontal(direction, x - 1);
			} else
				return x;
		}
	}



	/**
	 * La méthode renvoie le premier pixel dont la couleur est différente du pixel de départ, en parcourant l'image
	 * dans le sens de la hauteur, dans la direction indiquée par le caractère "direction", avec n = nord, s = sud.
	 * @param direction "n" ou "s"
	 * @param y int le pixel de départ pour le scan
	 * @return int le pixel différent de la couleur d'origine
	 */
	public int lookVertical(char direction, int y) { // si le pixel est

		if ((y > 0 && y < (int) Toolkit.getDefaultToolkit().getScreenSize().height)
				&& (this.img.getRGB((int) this.departGauche.x,
						(int) this.departGauche.y) == this.img.getRGB(
						(int) this.departGauche.x, (int) y))) {

			if (direction == 'n') {
				return lookVertical(direction, y - 1);
			}
			else {
				return lookVertical(direction, y + 1);
			}
		} else{
			return y;
		}


	}

	/**
	 * Accesseur qui retourne l'image de l'écran
	 * @return BufferedImage
	 */
	public BufferedImage getImg() {
		return this.img;
	}

	/**
	 * Accesseur qui permet d'accéder à la coordonnée de départ gauche
	 * @return int
	 */
	public Dot getDepartGauche() {
		return this.departGauche;
	}

	/**
	 * Accesseur qui permet d'accéder à la coordonnée de départ droit
	 * @return int
	 */
	public Dot getDepartDroit() {
		return this.departDroit;
	}

	/**
	 * Méthode qui permet de trouver la zone de jeu sur l'écran
	 * @return Rectangle ayant les coordonnées de la zone de jeu dans le repère de l'écran
	 */
	public Rectangle getGameArea() {

//		Point departGaucheLocal = new Point(this.departGauche.x,
//				this.departGauche.y);
//		Point departDroitLocal = new Point(this.departDroit.x,
//				this.departDroit.y);
		int xNewFrame;
		int yNewFrame;
		int yBottom;
		int xLeft;
		int height;
		int width;

		do {
			xNewFrame = this.lookHorizontal('e', (int) this.departGauche.x);

			yNewFrame = this.lookVertical('n', (int) this.departGauche.y);

			yBottom = this.lookVertical('s', (int) this.departGauche.y);

			//System.out.println(this.departDroit.x + " " + this.departDroit.y);
			
			xLeft = this.lookHorizontal('w', (int) this.departDroit.x);

			height = Math.abs(yNewFrame - yBottom);

			width = Math.abs(xLeft - xNewFrame);

			if(this.departGauche.x < Toolkit.getDefaultToolkit().getScreenSize().getWidth())this.departGauche.x += 1;
			
			if(this.departGauche.x > 0)this.departDroit.x -= 1;
			
			//System.out.println("Did it x1");

		} while (!((double) width / (double) height <= 1.35 && (double) width / (double) height >= 1.30));


		return new Rectangle(xNewFrame, yNewFrame, width, height);

	}

	/**
	 * Méthode qui permet de sauvegarder la zone de jeu dans un fichier .png
	 * @param name Le nom du fichier dans lequel sauvegardé l'image
	 * @throws IOException
	 */
	public void saveGameArea(String name) throws IOException {

		Rectangle rec = getGameArea();

		BufferedImage reduc = this.img.getSubimage(rec.x, rec.y, rec.width, rec.height);
		
		System.out.println(rec.x + " " + rec.y + " " + rec.width + " " + rec.height);

		ImageIO.write(reduc, "png", new File(name + ".png"));

	}

}
