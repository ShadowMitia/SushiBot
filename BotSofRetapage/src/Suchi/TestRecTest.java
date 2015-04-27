package Suchi;


import org.junit.Test;

import java.awt.image.BufferedImage;

public class TestRecTest {

	@Test
	public void test() {
		
		BufferedImage im1 = new TestRec().loadSingleSpriteByPath("sprites/screen_win.png");
		BufferedImage im2 = new TestRec().loadSingleSpriteByPath("sprites/Screen1.png");
		im1 = im1.getSubimage(407, 226, 469, 77);
		im2 = im2.getSubimage(407, 226, 469, 77);
		new TestRec().writeImage(im1, "prout1.png");
		new TestRec().writeImage(im2, "prout2.png");
		assert(new TestRec().sontEgales(im1, new TestRec().loadSingleSpriteByPath("sprites/youwin.png"), 90));
		assert (!(new TestRec().sontEgales(im2, new TestRec().loadSingleSpriteByPath("sprites/youwin.png"), 80)));
		
	}

}
