package Suchi;

public class California extends Recette {
 
	California() throws Exception {

		super();
		this.make();
		this.validate();
		Thread.sleep(2000);

	}

	public void make() throws Exception{
		
		ia.useIngredient("Rice");
		ia.useIngredient("FishEggs");
		ia.useIngredient("Nori");
		
		ia.clickZone("Rice");
		ia.clickZone("FishEggs");
		ia.clickZone("Nori");
		
	}
	public void validate () throws Exception {
		
		ia.clickZone("SushiMakerZone");

	}

}
