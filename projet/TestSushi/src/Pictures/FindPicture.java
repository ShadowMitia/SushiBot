package TestSushi.src.Pictures;

import java.awt.Point;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class FindPicture {
	// source qui est l'image du screen et img et celle qu'on doit trouver dans source 
	public BufferedImage source  , img ; 
	// dot est le point des coordonées 
	private Point dot;

	/**
	 * Constructeur
	 * @param src BufferedImage de l'image sur laquelle chercher
	 * @param img path vers l'image à chercher dans l'image
	 */
	public FindPicture(BufferedImage src , String img){
		this.loadPictures(img);
		this.source = src ;
		this.dot=new Point(-1,-1);
		//this.img = resize(this.img,303,116);
	}

	/**
	 * Constructeur
	 * @param src BufferedImage de l'image sur laquelle chercher
	 * @param img BufferedImage de l'image  à chercher
	 */
	public FindPicture(BufferedImage src , BufferedImage img){
		this.source=src;
		this.img=img;
		this.dot=new Point (-1,-1);
	}

	/**
	 * Méthode qui charge l'image à chercher dans img
	 * @param pathImg Chemin vers l'image à chargé
	 */
	public  void loadPictures( String pathImg){
		//File fSrc = new File (pathSrc);
		File fImg = new File (pathImg);
		try {
			//source = ImageIO.read(fSrc);
			this.img = ImageIO.read(fImg);
		} catch (IOException e) {
			throw new RuntimeException("impossible to load image: " + pathImg);
		}
	}

	//renvoie une array list qui contient la ligne "line" de l'image "img"

	/**
	 * Méthode qui retourne une ArrayList qui contient la ligne sélectionné de l'image img.
	 * @param img L'image sur laquelle travailler
	 * @param line Le numéro de la ligne à prendre
	 * @return ArrayList qui contient les pixels de la ligne sélectionné
	 */
	public   ArrayList<Integer> getLine(BufferedImage img , int line ){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < img.getWidth(); i++) {

			l.add(img.getRGB(i,line));

		}
		return l ; 
	}

	// meme principe mais pour les colonnes

	/**
	 * Méthode qui retourne une ArrayList qui contient la colonne sélectionné de l'image img.
	 * @param img L'image sur laquelle travailler
	 * @param column Le numéro de la colonne à prendre
	 * @return ArrayList qui contient les pixels de la colonne sélectionné
	 */
	public ArrayList<Integer> getColumn(BufferedImage img , int column) {
		ArrayList<Integer> c = new ArrayList<Integer>();
		for (int i = 0; i < img.getHeight(); i++) {
			c.add(img.getRGB(column, i));
		}
		return c;
	}

	/**
	 * méthode qui vérifie si une certaine colonne de l'img se trouve dans source
	 * @param source L'image source dans laquelle chercher
	 * @param img L'image dont une colonne va être chercher
	 * @return true si la colonne existe dans source, false sinon
	 */
	public boolean checkColumn (BufferedImage source , BufferedImage img ){
		ArrayList<Integer> columnImg = getColumn(img, img.getHeight() / 2);// une colonne de l'image qu'on cherche
		ArrayList<Integer> columnSrc = new ArrayList<Integer>();//representera les colonnes de l'image source 
		for(int i = 0 ; i<source.getWidth();i++){
			columnSrc = getColumn(source, i);
			if(columnSrc.containsAll(columnImg)){
				dot.x = i;
				return true ; 
			}
		}
		return false ; 
	}


	// meme principe mais pour les lignes

	/**
	 * méthode qui vérifie si une certaine ligne de l'img se trouve dans source
	 * @param source L'image source dans laquelle chercher
	 * @param img L'image dont une colonne va être chercher
	 * @return true si la ligne existe dans source, false sinon
	 */
	public  boolean checkLine(BufferedImage source , BufferedImage img){
		ArrayList<Integer> lineImg = getLine(img,img.getHeight()/2);// une ligne de l'image qu'oin cherche
		ArrayList<Integer> lineSrc = new ArrayList<>();//representera les lignes de l'image source

		for (int j = 0; j < source.getHeight(); j++) {
			lineSrc = getLine(source,j);// retirer les lignes de l'image source 
			if(lineSrc.containsAll(lineImg)){
				dot.y=j;
				return true ; 
			}
		}
		return false; 
	}

	/**
	 * Vérifie si l'img existe dans source
	 * @return true si l'image chercher se trouve dans l'image, false sinon
	 */
	public boolean checkImage(){
		this.checkLine(this.getSrc(), this.getImg());
		this.checkColumn(this.getSrc(), this.getImg());
		if (this.getX()==-1 || this.getY()==-1) {
			return false ; 
		}
		return true ; 
	}

	/*
	Getters
	 */

	/**
	 * Renvoie l'image source de l'objet
	 * @return
	 */
	public BufferedImage getSrc(){
		return this.source;
	}

	/**
	 * Renvoie l'image img de l'objet
	 * @return
	 */
	public BufferedImage getImg(){
		return this.img;
	}

	/**
	 * Renvoie la coordonnée y
	 * @return
	 */
	public int getX() {
		return dot.x;
	}

	/**
	 * Renovie la coordonnée y
	 * @return
	 */
	public int getY(){
		return dot.y;
	}




	
	public static void main (String [] s){

		File fImg = new File ("sprites/test.png");
		try {
			//source = ImageIO.read(fSrc);
			BufferedImage test  = ImageIO.read(fImg);

			FindPicture p = new FindPicture (test , "sprites/oni.png");
			JFrame frame = new JFrame ("Johnny");
			frame.getContentPane().add(new JLabel(new ImageIcon(p.img)));
			frame.pack();
			frame.setVisible(true);
			p.checkColumn(p.source, p.img);
			p.checkLine(p.source, p.img);
			System.out.println(p.getX()+"   "+p.getY());

		} catch (IOException e) {
			throw new RuntimeException("Impossible to load image");
		}
	}
}

/*
 * si les deux captures d'ecrans ont étées prises de la même façon ( à partir de du miniprogramme de capture ou à partir des screens du pc ) 
 * la detections se fait sinon elle ne se fait pas le probleme pourrait peut etre venir du format des captures sous mac 
 * aucune idée sur comment regler le probleme ???? 
 */
