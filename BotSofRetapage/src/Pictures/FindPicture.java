package Pictures;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class FindPicture {
	// On cherche l'image source dans l'image img
	public BufferedImage source  , img ; 
	// dot est le point des coordon������es 
	public Point dot  ; 

	/**
	 * Constructeur.
	 * @param src BufferedImage qui représente l'écran.
	 * @param img Path vers le fichier du sprite à détecter.
	 */
	public FindPicture(BufferedImage src , String img){
		this.loadPictures(img);
		this.source = src ;
		this.dot=new Point(-1,-1);
		//this.img = resize(this.img,303,116);
	}

	/**
	 * Constructeur.
	 * @param src BufferedImage qui représente l'écran.
	 * @param img BufferedImage qui représente le sprite.
	 */
	public FindPicture(BufferedImage src , BufferedImage img){
		this.source=src;
		this.img=img;
		this.dot=new Point (-1,-1);
	}

	/**
	 * Méthode qui permet de charge un sprite dans le BufferedImage approprié
	 * @param pathImg Chemin vers le sprite à chargé.
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

	/**
	 * Méthode qui renvoie l'ArrayList qui contient la ligne "line" dans l'image "img"
	 * @param img Un BufferedImage à scanner
	 * @param line Le numéro de la ligne dans laquelle commencer le scan
	 * @return ArrayList contenant toute la ligne de pixels sélectionné
	 */
	public   ArrayList<Integer> getLine(BufferedImage img , int line ){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < img.getWidth(); i++) {

			l.add(img.getRGB(i,line));

		}
		return l ; 
	}

	/**
	 * Méthode qui renvoie l'ArrayList qui contient la colonne "column" dans l'image "img"
	 * @param img Un BufferedImage à scanner
	 * @param column Le numéro de la colonne dans laquelle commencer le scan
	 * @return ArrayList contenant toute la colonne de pixels sélectionné
	 */
	public ArrayList<Integer> getColumn(BufferedImage img , int column){
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(int i = 0 ; i<img.getHeight();i++){
			c.add(img.getRGB(column, i));
		}
		return c ; 
	}

	/**
	 * On vérifie si la colonne au milieu du sprite se trouve dans l'image
	 * @param source L'écran
	 * @param img Le sprite
	 * @return true si la colonne s'y trouve, et false sinon
	 */
	public boolean checkColumn (BufferedImage source , BufferedImage img ){
		ArrayList<Integer> columnImg = getColumn(img,img.getHeight()/2);// une colonne de l'image qu'on cherche
		ArrayList<Integer> columnSrc;//representera les colonnes de l'image source
		for(int i = 0 ; i<source.getWidth();i++){
			columnSrc = getColumn(source,i);
			if(columnSrc.containsAll(columnImg)){
				dot.x=i;
				return true ; 
			}
		}
		return false ; 
	}

	/**
	 * On vérifie si la ligne au milieu du sprite se trouve dans l'image
	 * @param source L'écran
	 * @param img Le sprite
	 * @return true si la colonne s'y trouve, et false sinon
	 */
	public  boolean checkLine(BufferedImage source , BufferedImage img){
		ArrayList<Integer> lineImg = getLine(img,img.getHeight()/2);// une ligne de l'image qu'oin cherche
		ArrayList<Integer> lineSrc;//representera les lignes de l'image source

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
	 * Méthode qui vérifie si le sprite chargé se trouve dans l'image également chargé
	 * @return vrai si le sprite se trouve dans l'image, false sinon
	 */
	public boolean checkImage(){
		this.checkLine(this.getSrc(), this.getImg());
		this.checkColumn(this.getSrc(), this.getImg());
		if (this.getX()==-1 || this.getY()==-1) {
			return false ; 
		}
		return true ; 
	}


	/**
	 * Accesseur vers l'image source
	 * @return BufferedImage de l'image source (l'écran)
	 */
	public BufferedImage getSrc(){return this.source;}

	/**
	 * Accesseur vers l'image du sprite
	 * @return BufferedImage de l'image du sprite
	 */
	public BufferedImage getImg(){return this.img;}

	/**
	 * Accesseur vers la coordonnée x
	 * @return int
	 */
	public int getX() { return dot.x;}

	/**
	 * Accesseur vers la coordonnée y
	 * @return int
	 */
	public int getY(){ return dot.y;}

}
