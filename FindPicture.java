import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class FindPicture {
	// source qui est l'image du screen et img et celle qu'on doit trouver dans source 
	public BufferedImage source  , img ; 
	// dot est le point des coordonées 
	public Point dot  ; 

	// constructeur on doit donc y mettre une buffered image qui sera le screen et le path du sprite à chercher
	public FindPicture(BufferedImage src , String img){
		this.loadPictures(img) ;
		this.source = src ;
		this.dot=new Point(-1,-1);
	}

	// loader le sprite dans le buffer img 
	public  void loadPictures( String pathImg){
		//File fSrc = new File (pathSrc);
		File fImg = new File (pathImg);
		try {
			//source = ImageIO.read(fSrc);
			this.img = ImageIO.read(fImg);
		} catch (IOException e) {
			throw new RuntimeException("impossible to load image");
		}
	}
	
	//renvoie une array list qui contient la ligne "line" de l'image "img"
	public   ArrayList<Integer> getLine(BufferedImage img , int line ){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < img.getWidth(); i++) {

			l.add(img.getRGB(i,line));

		}
		return l ; 
	}
	
	// meme principe mais pour les colonnes 
	public ArrayList<Integer> getColumn(BufferedImage img , int column){
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(int i = 0 ; i<img.getHeight();i++){
			c.add(img.getRGB(column, i));
		}
		return c ; 
	}

	// la on check si on trouve la colonne dans la grosse image 
	public boolean checkColumn (BufferedImage source , BufferedImage img ){
		ArrayList<Integer> columnImg = getColumn(img,img.getHeight()/2);// une colonne de l'image qu'oin cherche
		ArrayList<Integer> columnSrc = new ArrayList<Integer>();//representera les colonnes de l'image source 
		for(int i = 0 ; i<source.getWidth();i++){
			columnSrc = getColumn(source,i);
			if(columnSrc.containsAll(columnImg)){
				dot.x=i;
				return true ; 
			}
		}
		return false ; 
	}


	// meme principe mais pour les lignes 
	public  boolean checkLine(BufferedImage source , BufferedImage img){
		ArrayList<Integer> lineImg = getLine(img,img.getHeight()/2);// une ligne de l'image qu'oin cherche
		ArrayList<Integer> lineSrc = new ArrayList<Integer>();//representera les lignes de l'image source 

		for (int j = 0; j < source.getHeight(); j++) {
			lineSrc = getLine(source,j);// retirer les lignes de l'image source 
			if(lineSrc.containsAll(lineImg)){
				dot.y=j;
				return true ; 
			}
		}

		return false; 
	}

	///////////////////////////////
	// les selectors 			///
	//////////////////////////////
	
	//renvoie l'image source
	public BufferedImage getSrc(){return this.source;}
	//renvoie le sprite 
	public BufferedImage getImg(){return this.img;}
	//renvoie la coordonnée X
	public int getX() { return dot.x;}
	//renvoie la coordonnée Y 
	public int getY(){ return dot.y;}


	public static void main (String [] args) throws AWTException{
		Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
		g.Start("screenshot9.png");
		
		
		
		
		
		
		
		
		
//		BufferedImage game = g.getScreen(10000);
//		FindPicture test = new FindPicture(game,"/Users/sofiane/desktop/screenshot10.png");
//		test.checkLine(test.getSrc(), test.getImg());
//		test.checkColumn(test.getSrc(), test.getImg());
//		if (test.getX()==-1 || test.getY()==-1)throw new RuntimeException("Sprite non detecté");
//		else {
//		System.out.println("is line working ? "+test.getY());
//		System.out.println("is column working ? "+test.getX());
//		me.delay(1000);
//		me.mouseMove(0,0);
//		me.mouseMove(test.getX(), test.getY());
		//me.mousePress(InputEvent.BUTTON1_MASK);
		//me.mouseRelease(InputEvent.BUTTON1_MASK);
		//}	
	
	}


}
