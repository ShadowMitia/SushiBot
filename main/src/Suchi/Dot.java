package Suchi;


public class Dot {
	
	public int x;
	public int y;


	/**
	 * Constructeur.
	 * @param x Coordonnée x dans le repère de l'écran
	 * @param y Coordonnée y dans le repère de l'écran
	 */
	public Dot(double x, double y) {
		this.x = (int)x;
		this.y = (int)y;
	//	System.out.println("x : " + this.x + ", y : " + this.y);
		translate();
	//	System.out.println("x : " + this.x + ", y : " + this.y);
	}


	/**
	 * Méthode pour changer la position du point
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Translate les coordonnée dans les coordonnées de la zone de jeu
	 */
	public void translate(){
		this.x = this.x + Game.getOrigin().x;
		this.y = this.y + Game.getOrigin().y;
		
	}
	
}


