package main;

import java.util.HashMap;

public class Zone {

	private HashMap<String, Dot> ensemblePointsZones;

	public Zone() {

		this.ensemblePointsZones = new HashMap<String, Dot>();

	}

	public void addZone(String nom, Dot point) {

		this.ensemblePointsZones.put(nom, point);

	}
	
	public void addZone(String nom, Dot origin, int width, int height) {


	}

	public HashMap<String, Dot> getZonesTable() {

		return this.ensemblePointsZones;

	}

}



