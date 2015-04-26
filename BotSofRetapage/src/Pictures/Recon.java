package Pictures;

import Suchi.Dot;
import Suchi.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * 
 * Cette classe va prendre un chemin de sprite et une zone en param��tre, et dira si l'image et la zone sont identiques.
 * 
 */

public class Recon {

	private HashMap<String, Rectangle> reconZones;
	private int height, width;

	/**
	 * Constructeur
	 */
	public Recon(){
		
		this.height = Game.getGameZone().height; this.width = Game.getGameZone().width;
		
		this.reconZones = new HashMap<String, Rectangle>();
		
		 addZone("Bulle1", new Dot((78. / 1236.) * this.width,
				 (98. / 928.) * this.height), 64*(1236./(double)this.width), 69*(959./this.height));
		 addZone("Bulle2", new Dot((272. / 1236.) * this.width,
				(98. / 928.) * this.height), 64*(1236./(double)this.width), 69*(959./this.height));
		 addZone("Bulle3", new Dot((467. / 1236.) * this.width,
				(98. / 928.) * this.height), 64*(1236./(double)this.width), 69*(959./this.height));
		 addZone("Bulle4", new Dot((662. / 1236.) * this.width,
				(98. / 928.) * this.height), 64*(1236./(double)this.width), 69*(959./this.height));
		 addZone("Bulle5", new Dot((858. / 1236.) * this.width,
				(98. / 928.) * this.height), 64*(1236./(double)this.width), 69*(959./this.height));
		 addZone("Bulle6", new Dot((1053. / 1236.) * this.width,
				(98. / 928.) * this.height), 64*(1236./(double)this.width), 69*(959./this.height));
		
	}

	/**
	 *
	 * @param nom
	 * @param origin
	 * @param width
	 * @param height
	 */
	public void addZone(String nom, Dot origin, double width, double height) {
		
		Rectangle temp = new Rectangle(origin.x, origin.y, (int)width, (int)height);
		 this.reconZones.put(nom, temp);

		
	}

	/**
	 *
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public BufferedImage chargerZone(String s) throws Exception {
		
		if(this.reconZones.containsKey(s)){
			
			return new Robot().createScreenCapture(this.reconZones.get(s));
			
		}
		else throw new Exception("Zone d'image non connue");
	}

	/**
	 *
	 * @param img1
	 * @param img2
	 * @param marge
	 * @return
	 */
	public Boolean sontEgales(BufferedImage img1, BufferedImage img2, int marge) {
		
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		array1 = this.mosaique(img1);
		array2 = this.mosaique(img2);
		if (this.testEq(array1, array2, marge))
			return true;
		else
			return false;

	}

	/**
	 *
	 * @param file
	 * @param img
	 * @return
	 */
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

	/**
	 *
	 * @param array1
	 * @param array2
	 * @param taux
	 * @return
	 */
	public Boolean testEq(ArrayList<Integer> array1, ArrayList<Integer> array2, int taux) {
		int compteur = 100;
		for (int i = 0; i < array1.size(); i++) {
			if (array1.get(i) >= array2.get(i) + array2.get(i) * taux / 100
			|| array1.get(i) <= array2.get(i) - array2.get(i) * taux/ 100)
				compteur--;
		}
		//System.out.println(compteur);
		if(compteur >= 97) return true;
		else return false;
	}

	/**
	 *
	 * @param img
	 * @return
	 */
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

	/**
	 *
	 * @param img
	 * @return
	 */
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

	/**
	 * Méthode qui permet de charger un fichier image dans un BufferedImage
	 * @param s Le chemin vers le fichier
	 * @return BufferedImage contenant l'image
	 */
	public BufferedImage loadSingleSpriteByPath(String s) {
		
		BufferedImage img ;
		
		try {
			
			img = ImageIO.read(new File(s));
			return img;
			
		} catch (IOException e){
			
			//e.printStackTrace();
			throw new RuntimeException("Impossible de charger l'image: " + s);
			
		}
	
		
	}

	/**
	 * Méthode qui permet de sauvegarder un BufferedImage au format png
	 * @param im Le BufferedImage à sauvegarder
	 * @param nom Le nom du fichier résultat
	 */
	public void writeImage(BufferedImage im, String nom) {

		try {

			ImageIO.write(im, "png", new File(nom + ".png"));

		} catch (IOException e) {
		}

	}
	
}