
import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Rectangle;

import org.junit.Test;

public class IATest {

	@Test
	public void test() throws AWTException {
		
		Rectangle rectangle = new Rectangle(321, 121, 1277, 959);
		
		IA ia = new IA(rectangle);
		
		for(String chaine : ia.getZone().getZonesTable().keySet()){
			
			System.out.println(chaine + " , x: " + ia.getZone().getZonesTable().get(chaine).x + ", y : " + ia.getZone().getZonesTable().get(chaine).y);
			
		}
		
		
	}

}
