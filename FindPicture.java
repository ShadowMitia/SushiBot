package Pictures;

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
	public BufferedImage source  , img ; 
	
	public  void loadPictures(String pathSrc , String pathImg){
		File fSrc = new File (pathSrc);
		File fImg = new File (pathImg);
		try {
			source = ImageIO.read(fSrc);
			img = ImageIO.read(fImg);
		} catch (IOException e) {
			throw new RuntimeException("impossible to load image");
		}
	}

	public   ArrayList<Integer> getLine(BufferedImage img , int line ){
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < img.getWidth(); i++) {

			l.add(img.getRGB(i,line));

		}
		return l ; 
	}


	public  Point check(BufferedImage source , BufferedImage img){
		 Point dot = new Point(-1,-1);
		ArrayList<Integer> lineImg = getLine(img,10);// une ligne de l'image qu'oin cherche
		ArrayList<Integer> lineSrc = new ArrayList<Integer>();//representera les lignes de l'image source 

		for (int j = 0; j < source.getHeight(); j++) {
			lineSrc = getLine(source,j);// retirer les lignes de l'image source 
			if(lineSrc.containsAll(lineImg)){
				dot.y=j;
				dot.x=lineImg.size()/2;
			}
		}

		return dot; 
	}
	
	public BufferedImage getSrc(){return this.source;}
	public BufferedImage getImg(){return this.img;}

	public static void main (String [] args){
		FindPicture test = new FindPicture();
		test.loadPictures("/Users/sofiane/desktop/screenshot.png","/Users/sofiane/desktop/screenshot9.png");
		System.out.println(" is it working ?? "+test.check(test.getSrc(), test.getImg()));
	}

}
