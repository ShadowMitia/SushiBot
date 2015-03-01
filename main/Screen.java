package main;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Screen {
	private BufferedImage img;
	private Dot departGauche;
	private Dot departDroit;

	public Screen(int x) throws Exception {

		Thread.sleep(1000 * x);

		this.img = new Robot().createScreenCapture(new Rectangle(Toolkit
				.getDefaultToolkit().getScreenSize()));

		this.departGauche = new Dot(0, (int) Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 2, false);

		this.departDroit = new Dot(Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() - 1, this.departGauche.y, false);

	}

	public void saveImage(String name) {

		try {

			ImageIO.write(this.img, "png", new File(name + ".png"));

		} catch (IOException e) {
			System.out.println("Write failed");
		}
	}

	/*
	 * La fonction du dessous renvoie le x du premier pixel dont la couleur est
	 * diff��rente du pixel de d��part, en parcourant l'image horizontalement,
	 * dans le sens de la largeur, dans la direction indiqu��e par le caract��re
	 * "direction", avec e = est, w = west.
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

	/*
	 * La fonction du dessous renvoie le premier pixel dont la couleur est
	 * diff��rente du pixel de d��part, en parcourant l'image dans le sens de la
	 * hauteur, dans la direction indiqu��e par le caract��re "direction", avec
	 * n = nord, s = sud.
	 */
	public int lookVertical(char direction, int y) { // si le pixel est

		if ((y > 0 && y < (int) Toolkit.getDefaultToolkit().getScreenSize().height)
				&& (this.img.getRGB((int) this.departGauche.x,
						(int) this.departGauche.y) == this.img.getRGB(
						(int) this.departGauche.x, (int) y))) {

			if (direction == 'n')
				return lookVertical(direction, y - 1);
			else
				return lookVertical(direction, y + 1);

		} else
			return y;

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

			System.out.println(this.departDroit.x + " " + this.departDroit.y);
			
			xLeft = this.lookHorizontal('w', (int) this.departDroit.x);

			height = Math.abs(yNewFrame - yBottom);

			width = Math.abs(xLeft - xNewFrame);

			if(this.departGauche.x < Toolkit.getDefaultToolkit().getScreenSize().getWidth())this.departGauche.x += 1;
			
			if(this.departGauche.x > 0)this.departDroit.x -= 1;
			
			System.out.println("Did it x1");

		} while (!((double) width / (double) height <= 1.35 && (double) width
				/ (double) height >= 1.30));


		return new Rectangle(xNewFrame, yNewFrame, width, height);

	}

	public void saveGameArea(String name) throws IOException {

		Rectangle rec = getGameArea();

		BufferedImage reduc = this.img.getSubimage(rec.x, rec.y, rec.width,
				rec.height);
		
		System.out.println(rec.x + " " + rec.y + " " + rec.width + " " + rec.height);

		ImageIO.write(reduc, "png", new File(name + ".png"));

	}

}
