package main;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class IMGRecognitionTest {

	@Test
	public void test() {
		
		BufferedImage im1 = new IMGRecognition().loadImage("IM1/im1_1.png");
		
		BufferedImage im2 = new IMGRecognition().loadImage("Sprits/gunkan.png");
	
		BufferedImage imresultat = IMGRecognition.xorEffect(im1,  im2);
		
		new IMGRecognition().writeImage(imresultat, "pouet");
		
	}

}
