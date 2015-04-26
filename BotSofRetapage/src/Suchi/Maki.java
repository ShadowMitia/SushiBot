package Suchi;

public class Maki extends Recette{
	
	Maki() throws Exception {
		
		super();
		this.make();
		this.validate();
		Thread.sleep(1500);
		
	}
	public void make() throws Exception{		

		
		ia.useIngredient("Rice");
		ia.useIngredient("FishEggs");
		ia.useIngredient("Nori");
		ia.useIngredient("FishEggs");

		ia.clickZone("Rice");
		ia.clickZone("FishEggs");
		ia.clickZone("Nori");
		ia.clickZone("FishEggs");
		
			}
	public void validate () throws Exception {
		
		ia.clickZone("SushiMakerZone");
		
	}

}
