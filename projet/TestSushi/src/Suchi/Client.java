package Suchi;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

import Pictures.FindPicture;


public class Client  {
	private BufferedImage[] customer ; 
	private boolean served[] ; 
	private String oni , maki , california , salmonRolls, shrimp; 
	private ArrayList<Sushis> toDo ;
	private long [] compteurs ;
	static int delaiDeCommande = 20000 ; // delai à optimiser  

	enum Sushis {
		CALIFORNIA , 
		MAKI , 
		ONIGIRI , 
		SALMONROLLS,
		SHRIMP;
	}
	// coordonnées des assietes pour debarasser 
	private final Point a1 = new Point (280,373);
	private final Point a2 = new Point (425,373);
	private final Point a3 = new Point (578,373);
	private final Point a4 = new Point (729,373);
	private final Point a5 = new Point (877,373);
	private final Point a6 = new Point (1031,373);


	Client() throws HeadlessException, AWTException{
		this.customer= new BufferedImage[6];
		this.served= new boolean[6];
		for(int i = 0 ; i<this.served.length;i++){
			this.served[i]=false ; 
		}
		this.compteurs = new long [6];
		for (int i = 0 ; i<this.compteurs.length;i++)
			this.compteurs[i]=0 ; 
		this.oni = "/Users/sofiane/desktop/L2/S4/POO/projet/sprites/oni.png";
		this.maki = "/Users/sofiane/desktop/L2/S4/POO/projet/sprites/mak.png";
		this.california="/Users/sofiane/desktop/L2/S4/POO/projet/sprites/cali.png";
		this.salmonRolls="/Users/sofiane/desktop/L2/S4/POO/projet/sprites/salmon.png";
		this.shrimp  =  "/Users/sofiane/desktop/L2/S4/POO/projet/sprites/shrimp.png";

		this.toDo = new ArrayList<Sushis>();

	}

	public void update() throws HeadlessException, AWTException{
		int ecart = 0 ; 
		for (int i =0;i< this.customer.length; i++) {
			this.customer[i]=new Robot().createScreenCapture(new Rectangle(160+ecart,125,160,100));
			ecart+=160;
			//						 JFrame frame = new JFrame ("test");
			//						 frame.getContentPane().add(new JLabel(new ImageIcon(this.customer[i])));
			//						 frame.pack();
			//						 frame.setVisible(true);
		}

	}



	public void check() throws AWTException, InterruptedException{
		this.clearTable();
		for (int i = 0; i < this.customer.length; i++) {

			FindPicture test1 = new FindPicture(this.customer[i],this.maki);

			if (!test1.checkImage() ) {// premier if 

				test1.loadPictures(this.california);
				if (!test1.checkImage()) {// deuxieme if 

					test1.loadPictures(this.oni);
					if (!test1.checkImage()){  // troisieme if 
						test1.loadPictures(this.salmonRolls);


						if (!test1.checkImage()){//4 eme if 
							test1.loadPictures(this.shrimp);
							if (!test1.checkImage()){

								if (this.served[i]){// no bulle + vrai il faut repasser à false 
									this.served[i]=false ; 
								}
								continue ; 
							}
							else {
								if (!this.served[i]){// bulle + false 
									this.toDo.add(Sushis.SHRIMP);
									this.served[i]=true ; // on le sert 
									this.compteurs[i]=new Date().getTime();
								} else {// bulle + true 
									// ça veut dire qu'il attend
									if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
										this.toDo.add(Sushis.SHRIMP);
										this.served[i]=true ;
										this.compteurs[i]=new Date().getTime();
									} 
									else { continue ; }
								} 
							} 
							//// limite 4 eme if 
						} 

						else {
							if (!this.served[i]){// bulle + false 
								this.toDo.add(Sushis.SALMONROLLS);
								this.served[i]=true ; // on le sert 
								this.compteurs[i]=new Date().getTime();

							} else {// bulle + true 
								// ça veut dire qu'il attend
								if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
									this.toDo.add(Sushis.SALMONROLLS);
									this.served[i]=true ;
									this.compteurs[i]=new Date().getTime();
								}else { continue ; } 
							}
						}
					}

					else {
						if (!this.served[i]){// bulle + false 
							this.toDo.add(Sushis.ONIGIRI);
							this.served[i]=true ; // on le sert 
							this.compteurs[i]=new Date().getTime();

						} else {// bulle + true 
							// ça veut dire qu'il attend
							if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
								this.toDo.add(Sushis.ONIGIRI);
								this.served[i]=true ;
								this.compteurs[i]=new Date().getTime();
							}else { continue ; } 
						}
					}

				}else {
					if (!this.served[i]){// bulle + false 
						this.toDo.add(Sushis.CALIFORNIA);
						this.served[i]=true ; // on le sert 
						this.compteurs[i]=new Date().getTime();

					}else {// bulle + true 
						// ça veut dire qu'il attend 
						if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
							this.toDo.add(Sushis.CALIFORNIA);
							this.served[i]=true ;
							this.compteurs[i]=new Date().getTime();
						}else { continue ; } 
					}
				}
			}
			else{
				if (!this.served[i]){// bulle + false 
					this.toDo.add(Sushis.MAKI);
					this.served[i]=true ; // on le sert 
					this.compteurs[i]=new Date().getTime();

				}else {// bulle + true 
					// ça veut dire qu'il attend 
					if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
						this.toDo.add(Sushis.MAKI);
						this.served[i]=true ;
						this.compteurs[i]=new Date().getTime();
					}else { continue ; }
					System.out.println(new Date().getTime()-this.compteurs[i]);
				}
			}

		}

		this.makeS();
	}

	// si bulle + false --> servir true 
	// si no bulle + true --> false 


	public void makeS() throws AWTException, InterruptedException{
		System.out.println("liste avant un tour "+this.toDo.size());
		for(int i = 0 ; i<this.toDo.size();i++){
			switch(this.toDo.get(i)){
			case ONIGIRI : 
				new Onigiri() ;
				this.clearTable();
				break ; 
			case CALIFORNIA :
				new California() ; 
				this.clearTable();
				break ; 
			case MAKI : 
				new Maki() ; 
				this.clearTable();
				break ; 

			case SALMONROLLS : 
				new Salmon() ;
				this.clearTable();
				break ; 

			case SHRIMP : 
				new ShrimpSh() ;
				this.clearTable();
				break;
			}
		}
		this.toDo.clear();
		//Thread.sleep(10000);
		//this.check();
		System.out.println("liste apres un tour "+this.toDo.size());
	}

	public void clearTable() throws AWTException{
		Robot me = new Robot();
		me.delay(10);
		me.mouseMove(this.a1.x, this.a1.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(5);
		me.mouseMove(this.a2.x, this.a2.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(5);
		me.mouseMove(this.a3.x, this.a3.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(5);
		me.mouseMove(this.a4.x, this.a4.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(5);
		me.mouseMove(this.a5.x, this.a5.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(5);
		me.mouseMove(this.a6.x, this.a6.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}	
