package Sushis.src;

import java.awt.*;
import java.util.HashMap;

public class Zone {

	private HashMap<String, Point> ensemblePointsZones;

	public Zone() {

		this.ensemblePointsZones = new HashMap<String, Point>();

	}

	public void addZone(String nom, Point point) {

		this.ensemblePointsZones.put(nom, point);

	}
	
	public void addZone(String nom, Point origin, int width, int height) {


	}

	public HashMap<String, Point> getZonesTable() {

		return this.ensemblePointsZones;

	}

}



