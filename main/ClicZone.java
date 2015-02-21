package main;

import java.util.HashMap;

public class ClicZone {

	private HashMap<String, Dot> ensemblePointsZones;

	public ClicZone() {

		this.ensemblePointsZones = new HashMap<String, Dot>();

	}

	public void addClickZone(String nom, Dot point) {

		this.ensemblePointsZones.put(nom, point);

	}

	public HashMap<String, Dot> getZonesTable() {

		return this.ensemblePointsZones;

	}

}