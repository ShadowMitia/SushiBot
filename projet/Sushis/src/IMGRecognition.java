

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;



import javax.imageio.ImageIO;

public class IMGRecognition {

	public static int count = 0;
	
	public IMGRecognition(){
		
		
	}
	
	public BufferedImage loadImage(String filename) {

		BufferedImage img = null;

		try {

			img = ImageIO.read(new File(filename));

		} catch (IOException e) {
			System.out.println("Loading failed");
		}

		return img;
	}

	public void writeImage(BufferedImage im, String nom) {

		try {

			ImageIO.write(im, "png", new File(nom + ".png"));

		} catch (IOException e) {
		}

	}

	public Boolean areTheseImagesEqualBucketPixelMethod(String name1,
			String name2, int precision) {

		BufferedImage im1, im2;
		im1 = loadImage(name1);
		im2 = loadImage(name2);
		int[] tableCouleurs1 = new int[5];

		int[] tableCouleurs2 = new int[5];

		tableCouleurs1 = countPixels(im1, tableCouleurs1);
		tableCouleurs2 = countPixels(im2, tableCouleurs2);
		double rpBlanc, rpNoir, rpRouge, rpVert, rpBleu;

		if (tableCouleurs1[0] != 0 && tableCouleurs2[0] != 0)
			rpBlanc = tableCouleurs1[0] / tableCouleurs2[0];
		else
			rpBlanc = 0;

		if (tableCouleurs1[1] != 0 && tableCouleurs2[1] != 0)
			rpNoir = tableCouleurs1[1] / tableCouleurs2[1];
		else
			rpNoir = 0;

		if (tableCouleurs1[2] != 0 && tableCouleurs2[2] != 0)
			rpRouge = tableCouleurs1[2] / tableCouleurs2[2];
		else
			rpRouge = 0;

		if (tableCouleurs1[3] != 0 && tableCouleurs2[3] != 0)
			rpVert = tableCouleurs1[3] / tableCouleurs2[3];
		else
			rpVert = 0;

		if (tableCouleurs1[4] != 0 && tableCouleurs2[4] != 0)
			rpBleu = tableCouleurs1[4] / tableCouleurs2[4];
		else
			rpBleu = 0;

		System.out.println(rpBlanc + " " + rpNoir + " " + rpRouge + " "
				+ rpVert + " " + rpBleu);
		double somme = rpBlanc + rpBleu + rpNoir + rpRouge + rpVert;
		if ((somme) / 5.0 > 5.0 - (5.0 * (double) precision / 100.0)
				&& (somme) / 5.0 < 5.0 + (5.0 * (double) precision / 100.0)) {

			return true;

		}

		else
			return false;

	}

	public int[] countPixels(BufferedImage img, int[] tab) {

		for (int i = 0; i < img.getHeight(); i++) {

			for (int j = 0; j < img.getWidth(); j++) {

				int r, g, b;
				r = getRed(img, j, i);
				g = getGreen(img, j, i);
				b = getBlue(img, j, i);

				if (r == g && g == b) {

					if (r >= 175)
						tab[0]++;
					else
						tab[1]++;

				} else {

					if (r > g && r > b)
						tab[2]++;
					else if (g > b && g > r)
						tab[3]++;
					else
						tab[4]++;

				}

			}

		}
		return tab;
	}

	public int getRed(BufferedImage img, int x, int y) {

		int temp = img.getRGB(x, y);
		Boolean alph = img.getColorModel().hasAlpha();
		Color c = new Color(temp, alph);
		return c.getRed();

	}

	public int getBlue(BufferedImage img, int x, int y) {

		int temp = img.getRGB(x, y);
		Boolean alph = img.getColorModel().hasAlpha();
		Color c = new Color(temp, alph);
		return c.getBlue();

	}

	public int getGreen(BufferedImage img, int x, int y) {

		int temp = img.getRGB(x, y);
		Boolean alph = img.getColorModel().hasAlpha();
		Color c = new Color(temp, alph);
		return c.getGreen();

	}

	public BufferedImage resizeImage(BufferedImage original, int newW, int newH) {

		BufferedImage resizedImage = new BufferedImage(newW, newH,
				original.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(original, 0, 0, newW, newH, null);
		g.dispose();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		return resizedImage;
	}

	public Boolean areTheseImagesEqualInvertMethod(String imN1, BufferedImage im2,
			int precision) { //im1 est le sprite de référence, im2 le sprite screené
		
//		writeImage(im1, "IM1/im1_debut_" + Integer.toString(count));
//		writeImage(im2, "IM2/im2_debut_" + Integer.toString(count));
		
		BufferedImage im1 = loadImage("Sprits/" + imN1 +".png");
		
		writeImage(im1, "IM1/im1_" + Integer.toString(count));
		writeImage(im2, "IM2/im2_" + Integer.toString(count));
		
		BufferedImage imInversed = invertImage(im1);
		
		writeImage(imInversed, "Inversed/inversed_" + Integer.toString(count));
		
		BufferedImage imAdd = addImages(imInversed, im2);
		
		writeImage(imAdd, "Final/final_" + Integer.toString(count));
		
		count++;
		
		return checkWhiteImage(imAdd, precision);
		
		
	}

	public BufferedImage addImages(BufferedImage im1, BufferedImage im2) {

		for (int i = 0; i < im1.getHeight(); i++) {

			for (int j = 0; j < im1.getWidth(); j++) {

				im1 = setRGB(im1, j, i, getRed(im1, j, i) + getRed(im2, j, i),
						getGreen(im1, j, i) + getGreen(im2, j, i),
						getBlue(im1, j, i) + getBlue(im2, j, i));

			}

		}

		return im1;

	}

	public BufferedImage invertImage(BufferedImage img) {

		img = setAlpha(img, (byte) 255);

		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {

				img.setRGB(j, i, 0xFFFFFF - img.getRGB(j, i));

			}

		}
		return img;
	}

	public BufferedImage setAlpha(BufferedImage img, byte alpha) {
		alpha %= 0xff;
		for (int cx = 0; cx < img.getWidth(); cx++) {
			for (int cy = 0; cy < img.getHeight(); cy++) {
				int color = img.getRGB(cx, cy);

				int mc = (alpha << 24) | 0x00ffffff;
				int newcolor = color & mc;
				img.setRGB(cx, cy, newcolor);

			}

		}
		return img;
	}

	public BufferedImage setRGB(BufferedImage img, int x, int y, int red,
			int green, int blue) {

		int col = (red << 16) | (green << 8) | blue;
		img.setRGB(x, y, col);

		return img;

	}

	public BufferedImage setRGBwAlpha(BufferedImage img, int x, int y, int red,
			int green, int blue, int alpha) {

		int col = (alpha << 24) | (red << 16) | (green << 8) | blue;
		img.setRGB(x, y, col);

		return img;

	}

	public Boolean checkWhiteImage(BufferedImage img, int precision) {

		int nOfWhitePixels = 0;

		for (int i = 0; i < img.getHeight(); i++) {

			for (int j = 0; j < img.getWidth(); j++) {

				if (getRed(img, j, i) >= 240 && getGreen(img, j, i) >= 240
						&& getBlue(img, j, i) >= 240)
					nOfWhitePixels++;

			}

		}
		double ratio = ((double) nOfWhitePixels / (double) (img.getHeight() * img
				.getWidth())) * 100.;
		

		if ((double)precision <= Math.floor(ratio)) {
			return true;
		} else {
			
			return false;
		}
	}

}