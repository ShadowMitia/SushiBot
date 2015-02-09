import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class ImageRecognitionTest {


	public static void main(String[] args){

		// INVERSION DE COULEUR D'UNE IMAGE

		File file = new File("screenshot.png");
		BufferedImage img = null;
		try {
			 img = ImageIO.read(file);
		} catch (IOException e){
			System.out.println("Couldn't open file!!!!");
			return;
		}
	
		for (int i = 0; i < img.getWidth(); i++){
			for (int j = 0; j < img.getHeight(); j++){
				img.setRGB(i, j, 255 - img.getRGB(i, j));
			}
		}

		try {
			ImageIO.write(img, "png", new File("screenshot_inverse.png"));
		} catch (IOException e){
			System.out.println("Couldn't write file!!!!");
		}


		//

	}
}