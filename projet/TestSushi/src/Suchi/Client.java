package TestSushi.src.Suchi;


import TestSushi.src.Pictures.FindPicture;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Date;


public class Client  {
	private BufferedImage[] customer;
	private boolean served[];
	private String oni , maki , california , salmonRolls, shrimp; 
	private long [] compteurs ;
	static int delaiDeCommande = 30000 ; // delai à optimiser

    private long oldDelaiDebarasser;
    private long delaiDebarasser;

	enum Sushis {
		CALIFORNIA , 
		MAKI , 
		ONIGIRI , 
		SALMONROLLS,
		SHRIMP;
	}


	// coordonnées des assietes pour debarasser
    private final Point[] coordAssiettes = new Point[]{
            new Point (280,373),
            new Point (425,373),
            new Point (578,373),
            new Point (729,373),
            new Point (877,373),
            new Point (1031,373)
    };


	/**
	 * Constructeur
     *
	 * @throws HeadlessException
	 * @throws AWTException
	 */
	Client() throws HeadlessException, AWTException{
		this.customer= new BufferedImage[6];
		this.served= new boolean[6];
		for(int i = 0 ; i < this.served.length;i++){
			this.served[i] = false ;
		}
		this.compteurs = new long [6];
		for (int i = 0 ; i < this.compteurs.length;i++){
            this.compteurs[i] = 0 ;
        }

		this.oni = "sprites/oni.png";
		this.maki = "sprites/mak.png";
		this.california="sprites/cali.png";
		this.salmonRolls="sprites/salmon.png";
		this.shrimp  =  "sprites/shrimp.png";

        oldDelaiDebarasser = System.currentTimeMillis();
	}

    /**
     * Méthode qui met à jour les captures d'écran de bulles des clients
     * @throws HeadlessException
     * @throws AWTException
     */
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


    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void check() throws AWTException, InterruptedException{

        delaiDebarasser = System.currentTimeMillis();
        if (oldDelaiDebarasser + 2000 < delaiDebarasser){
            oldDelaiDebarasser = delaiDebarasser;
            this.clearTable();
        }
		for (int i = 0; i < this.customer.length; i++) {

			FindPicture test1 = new FindPicture(this.customer[i], this.maki);

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
									new ShrimpSh() ; 
									this.served[i]=true ; // on le sert 
									this.compteurs[i]=new Date().getTime();
								} else {// bulle + true 
									// ça veut dire qu'il attend
									if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
										new ShrimpSh() ; 
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
								new Salmon();
								this.served[i]=true ; // on le sert 
								this.compteurs[i]=new Date().getTime();

							} else {// bulle + true 
								// ça veut dire qu'il attend
								if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
									new Salmon () ;
									this.served[i]=true ;
									this.compteurs[i]=new Date().getTime();
								}else { continue ; } 
							}
						}
					}

					else {
						if (!this.served[i]){// bulle + false 
							new Onigiri () ; 
							this.served[i]=true ; // on le sert 
							this.compteurs[i]=new Date().getTime();

						} else {// bulle + true 
							// ça veut dire qu'il attend
							if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
								new Onigiri () ; 
								this.served[i]=true ;
								this.compteurs[i]=new Date().getTime();
							}else { continue ; } 
						}
					}

				}else {
					if (!this.served[i]){// bulle + false 
						new California() ; 
						this.served[i]=true ; // on le sert 
						this.compteurs[i]=new Date().getTime();

					}else {// bulle + true 
						// ça veut dire qu'il attend 
						if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
							new California ();
							this.served[i]=true ;
							this.compteurs[i]=new Date().getTime();
						}else { continue ; } 
					}
				}
			}
			else{
				if (!this.served[i]){// bulle + false 
					new Maki () ; 
					this.served[i]=true ; // on le sert 
					this.compteurs[i]=new Date().getTime();

				}else {// bulle + true 
					// ça veut dire qu'il attend 
					if (new Date().getTime()-this.compteurs[i]>delaiDeCommande){
						new Maki () ; 
						this.served[i]=true ;
						this.compteurs[i]=new Date().getTime();
					}else { continue ; }
					System.out.println(new Date().getTime()-this.compteurs[i]);
				}
			}
		}
	}



	public void clearTable() throws AWTException {
        Robot me = new Robot();
        me.delay(10);
        for (Point p : coordAssiettes) {
            me.mouseMove(p.x, p.y);
            me.mousePress(InputEvent.BUTTON1_MASK);
            me.mouseRelease(InputEvent.BUTTON1_MASK);
            me.delay(5);
        }
    }
}	
