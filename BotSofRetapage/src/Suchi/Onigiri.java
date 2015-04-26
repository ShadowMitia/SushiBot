package Suchi;


public class Onigiri extends Recette{
	 	
	public Onigiri() throws Exception{
		
		super();
		this.make();
		this.validate();
		Thread.sleep(2000);
		
	}

	public void make() throws Exception{
		
		ia.useIngredient("Rice");
		ia.useIngredient("Rice");
		ia.useIngredient("Nori");

		ia.clickZone("Rice");
		ia.clickZone("Rice");
		ia.clickZone("Nori");
	}

	public void validate () throws Exception{

		ia.clickZone("SushiMakerZone");
	}	

}
