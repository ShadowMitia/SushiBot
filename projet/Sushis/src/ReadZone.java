

import java.awt.Rectangle;
import java.util.HashMap;

public class ReadZone{

	private HashMap<String, Rectangle> ensembleZones;

	public ReadZone() {

		this.ensembleZones = new HashMap<String, Rectangle>();

	}

	public void addZone(String nom, Dot origin, int width, int height) {
		
		Rectangle temp = new Rectangle(origin.x, origin.y, width, height);
		this.ensembleZones.put(nom, temp);

		
	}
	
	
	public HashMap<String, Rectangle> getZones(){
		
		
		return this.ensembleZones;
		
	}

}