package Suchi;


import java.awt.AWTException;

public abstract class Recette {
	
	protected static Ia ia;
	
	protected Recette() throws AWTException{
		
		ia = new Ia();
		
	}


	/**
	 * Méthode qui va cherche les ingrédients de la recette
	 * @throws Exception
	 */
	abstract public void make() throws Exception;

	/**
	 *  Méthode qui valide la recette et la met sur le tapis roulant
	 * @throws Exception
	 */
	abstract public void validate () throws Exception;

}
