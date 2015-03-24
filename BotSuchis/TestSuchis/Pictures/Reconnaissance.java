package Pictures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Reconnaissance {

	public static void main(String[] args) {

	}

	public Boolean sontEgales(File file1, File file2, int marge) {
		BufferedImage img = null;
		BufferedImage img2 = null;
		img = this.chargerImg(file1, img);
		img2 = this.chargerImg(file2, img2);
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		array1 = this.mosaique(img);
		array2 = this.mosaique(img2);
//		System.out.println(array1);
//		System.out.println(array2);
		if (this.testEq(array1, array2, marge))
			return true;
		else
			return false;

	}

	public BufferedImage chargerImg(File file, BufferedImage img) {

		try {
			img = ImageIO.read(file);
			// JFrame frame = new JFrame ("Cheval");
			// frame.getContentPane().add(new JLabel(new ImageIcon(img)));
			// frame.pack();
			// frame.setVisible(true);
		} catch (IOException e) {
			throw new RuntimeException("marche pas ");
		}
		return img;
	}

	public Boolean testEq(ArrayList<Integer> array1, ArrayList<Integer> array2,
			int taux) {
		int compteur = 100;
		for (int i = 0; i < array1.size(); i++) {
			if (array1.get(i) >= array2.get(i) + array2.get(i) * taux / 100
			|| array1.get(i) <= array2.get(i) - array2.get(i) * taux/ 100)
				compteur--;
		}
		System.out.println(compteur);
		if(compteur >= 90) return true;
		else return false;
	}

	public int moyenneRGB(BufferedImage img) {
		int moyenne = 0;

		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				int rgb = (img.getRGB(j, i) & 0xFFFFFF)
						/ ((img.getHeight() * img.getWidth()));
				moyenne += rgb;
			}
		}

		return moyenne;
	}

	public ArrayList<Integer> mosaique(BufferedImage img) {

		ArrayList<Integer> listeRGB = new ArrayList<Integer>();

		for (int i = 0; i <= img.getHeight() - (Integer) (img.getHeight() / 10); i += (Integer) (img
				.getHeight() / 10)) {
			for (int j = 0; j <= img.getWidth()
					- (Integer) (img.getWidth() / 10); j += (Integer) (img
					.getWidth() / 10)) {
				listeRGB.add(this.moyenneRGB(img.getSubimage(j, i,
						(Integer) (img.getWidth() / 10),
						(Integer) (img.getHeight() / 10))));
			}
		}

		return listeRGB;
	}
}
