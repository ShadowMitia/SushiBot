package Suchi;


public class Salmon extends Recette {

	/**
	 * Constructeur
	 * @throws Exception
	 */
	Salmon() throws Exception{
		
		super();
		this.make();
		this.validate();
		Thread.sleep(2000);
		
	}
	public void make() throws Exception{

		ia.useIngredient("Rice");
		ia.useIngredient("Salmon");
		ia.useIngredient("Salmon");
		ia.useIngredient("Nori");
		
		ia.clickZone("Rice");
		ia.clickZone("Nori");
		ia.clickZone("Salmon");
		ia.clickZone("Salmon");
		
	}
	public void validate () throws Exception {
	
		ia.clickZone("SushiMakerZone");
		
	}


}
