package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.*;

import javax.imageio.ImageIO;

public class IMGRecognition {

	public void main(String[] args) {

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

	/*
	 * public Dot getRandomDot() {
	 * 
	 * double random = Math.random(); double random2 = Math.random(); Dot dot =
	 * new Dot(random, random2); return dot;
	 * 
	 * }
	 */

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
				r = getRed(img, new Dot(j, i));
				g = getGreen(img, new Dot(j, i));
				b = getBlue(img, new Dot(j, i));

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

	public int getRed(BufferedImage img, Dot dot) {

		int temp = img.getRGB(dot.x, dot.y);
		Boolean alph = img.getColorModel().hasAlpha();
		Color c = new Color(temp, alph);
		return c.getRed();

	}

	public int getBlue(BufferedImage img, Dot dot) {

		int temp = img.getRGB(dot.x, dot.y);
		Boolean alph = img.getColorModel().hasAlpha();
		Color c = new Color(temp, alph);
		return c.getBlue();

	}

	public int getGreen(BufferedImage img, Dot dot) {

		int temp = img.getRGB(dot.x, dot.y);
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

	public Boolean areTheseImagesEqualInvertMethod(String name1, String name2,
			int precision) {

		BufferedImage im1 = loadImage(name1);
		BufferedImage im2 = loadImage(name2);
		if (im1.getWidth() != im2.getWidth()
				|| im1.getHeight() != im2.getHeight()) {

			if (im1.getWidth() > im2.getWidth()) {

				if (im1.getHeight() > im2.getHeight()) {

					im1 = resizeImage(im1, im2.getHeight(), im2.getWidth());

				} else
					return false;

			} else if (im1.getWidth() == im1.getWidth()) {

				if (im1.getHeight() == im2.getHeight())
					;
				else
					return false;

			} else
				return false;

		} else {

		}
		im1 = invertImage(im1);

		return true;
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

}