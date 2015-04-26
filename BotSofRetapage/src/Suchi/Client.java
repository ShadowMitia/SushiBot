package Suchi;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.util.Date;

import Pictures.Recon;

public class Client  {
	private BufferedImage[] customer ; 
	static Boolean first = true;
	private boolean served[] ; 
	private String onigiri , maki , california , salmonRolls, shrimp; 
	private long [] compteurs ;
	static int delaiDeCommande = 30000 ; // delai �� optimiser  
	private Ia ia;

	Client() throws Exception{
		
		this.customer= new BufferedImage[6];
		this.served= new boolean[6];
		for(int i = 0 ; i<this.served.length;i++){
			this.served[i]=false ; 
		}
		this.compteurs = new long [6];
		for (int i = 0 ; i<this.compteurs.length;i++)
			this.compteurs[i]=0 ; 
		
		
		this.onigiri = "sprites/onigiri.png";
		this.maki = "sprites/maki.png";
		this.california="sprites/cali.png";
		this.salmonRolls="sprites/salmon.png";
		this.shrimp  =  "sprites/shrimp.png";
		ia = new Ia();
		

	}
	

	public void update() throws HeadlessException, AWTException{
		
		this.customer = new BufferedImage[6];
		this.customer = new Ia().loadCommandes();

	}
	
	private void makeSushi(String s) throws Exception{
		
		if(s == "Onigiri") {
			
			ia.useIngredient("Rice");
			ia.useIngredient("Rice");
			ia.useIngredient("Nori");

			ia.clickZone("Rice");
			ia.clickZone("Rice");
			ia.clickZone("Nori");
			
		}
		else if(s == "Maki"){
			
			ia.useIngredient("Rice");
			ia.useIngredient("FishEggs");
			ia.useIngredient("Nori");
			ia.useIngredient("FishEggs");

			ia.clickZone("Rice");
			ia.clickZone("FishEggs");
			ia.clickZone("Nori");
			ia.clickZone("FishEggs");
			
		}
		else if(s == "California") {
			
			ia.useIngredient("Rice");
			ia.useIngredient("FishEggs");
			ia.useIngredient("Nori");
			
			ia.clickZone("Rice");
			ia.clickZone("FishEggs");
			ia.clickZone("Nori");
			
		}
		else if (s == "ShrimpSh") {
			
			ia.useIngredient("Rice");
			ia.useIngredient("Nori");
			ia.useIngredient("Shrimp");
			ia.useIngredient("Shrimp");

			ia.clickZone("Rice");
			ia.clickZone("Nori");
			ia.clickZone("Shrimp");
			ia.clickZone("Shrimp");
			
		}
		else if(s == "SalmonSh") {
			
			ia.useIngredient("Rice");
			ia.useIngredient("Salmon");
			ia.useIngredient("Salmon");
			ia.useIngredient("Nori");
			
			ia.clickZone("Rice");
			ia.clickZone("Nori");
			ia.clickZone("Salmon");
			ia.clickZone("Salmon");	
		}
		ia.clickZone("SushiMakerZone");
		Thread.sleep(1400);
		
	}
	

	public void check() throws Exception{
		
		if(first){ 
			ia.initGreyReference();
			first = false;
		}
		
		ia.clearTable();
		
		for (int i = 0; i < this.customer.length; i++) {

			Recon recon = new Recon();
			
			if (!recon.sontEgales(this.customer[i], recon.loadSingleSpriteByPath(maki), 80)) {// premier if 

				if (!recon.sontEgales(this.customer[i], recon.loadSingleSpriteByPath(california), 82)) {// deuxieme if 
					
					if (!recon.sontEgales(this.customer[i], recon.loadSingleSpriteByPath(onigiri), 80)){  // troisieme if 
						
						if (!recon.sontEgales(this.customer[i], recon.loadSingleSpriteByPath(salmonRolls), 80)){//4 eme if 
							
							if (!recon.sontEgales(this.customer[i], recon.loadSingleSpriteByPath(shrimp), 80)){

								//Si on arrive �� traverser toutes ces ��preuves (lol), cela signifie que le client n'a rien command��, 
								//ou alors qu'il est en train de manger.
								//Si le client est servi, soit il mange soit il est parti, auquel cas on peut remettre le
								//bool��en servi �� faux, ce qui permettra la d��tection du prochain sprite.
									
								if(this.served[i]) {
									
									this.served[i]=false;
									
								}
								continue; 
							}
							
							// Cas o�� le sushi est un sushi crevette.
							else {
								
								if(!this.served[i]) {// bulle + false
									
									makeSushi("ShrimpSh"); 
									this.served[i]=true ; // on le sert 
									this.compteurs[i]=new Date().getTime();
									
								} 
								else {// bulle + true 
									// ������a veut dire qu'il attend
									if(new Date().getTime()-this.compteurs[i]>delaiDeCommande) {
										makeSushi("ShrimpSh");  
										this.served[i]=true ;
										this.compteurs[i]=new Date().getTime();
									} 
									else { continue; }
								} 
							} 
							//Fin du cas sushi crevette. 
						} 
						
						// Cas o�� le sushi est un sushi saumon.
						else {
							
							if(!this.served[i]){// bulle + false
								
								makeSushi("SalmonSh");
								this.served[i]=true ; // on le sert 
								this.compteurs[i]=new Date().getTime();

							} 
							else {// bulle + true 
								// ������a veut dire qu'il attend
								if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
									makeSushi("SalmonSh");
									this.served[i]=true ;
									this.compteurs[i]=new Date().getTime();
									
								}
								else { continue ; } 
							}
						}
					}

					// Cas o�� le sushi est un onigiri.
					else {
						
						if(!this.served[i]) {// bulle + false
							
							makeSushi("Onigiri");
							this.served[i]=true ; // on le sert 
							this.compteurs[i]=new Date().getTime();

						} 
						else {// bulle + true 
							// ������a veut dire qu'il attend
							if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
								makeSushi("Onigiri");
								this.served[i]=true ;
								this.compteurs[i]=new Date().getTime();
								
							}
							else { continue ; } 
						}
					}
					
					
				}
				
				//Cas o�� le sushi est un california roll.
				else {
					
					if(!this.served[i]) {// bulle + false
						
						makeSushi("California");
						this.served[i]=true ; // on le sert 
						this.compteurs[i]=new Date().getTime();

					}
					else {// bulle + true 
						// ������a veut dire qu'il attend 
						if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
							makeSushi("California");
							this.served[i]=true ;
							this.compteurs[i]=new Date().getTime();
							
						}
						else { continue ; } 
					}
				}
			}
			
			// Dernier cas, le sushi est un maki.
			else{
				
				if(!this.served[i]) {// bulle + false 
					
					makeSushi("Maki");
					this.served[i]=true ; // on le sert 
					this.compteurs[i]=new Date().getTime();

				}
				else {// bulle + true 
					// ������a veut dire qu'il attend 
					if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
						makeSushi("Maki");
						this.served[i]=true ;
						this.compteurs[i]=new Date().getTime();
						
					}
					else { continue ; }
					//System.out.println(new Date().getTime()-this.compteurs[i]);
				}
			}

		}
		//this.clearTable() ;

	}

}	
