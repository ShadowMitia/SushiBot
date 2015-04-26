package Suchi;



public class ShrimpSh extends Recette  {

	/**
	 * Constructeur
	 * @throws Exception
	 */
	public ShrimpSh() throws Exception {

		super();
		this.make();
		this.validate() ; 
		Thread.sleep(2000);
		
	}


	public void make() throws Exception{
		
		ia.useIngredient("Rice");
		ia.useIngredient("Nori");
		ia.useIngredient("Shrimp");
		ia.useIngredient("Shrimp");

		ia.clickZone("Rice");
		ia.clickZone("Nori");
		ia.clickZone("Shrimp");
		ia.clickZone("Shrimp");
	}

	public void validate () throws Exception {
		
		ia.clickZone("Sushi_Maker_Zone");

	}


}

