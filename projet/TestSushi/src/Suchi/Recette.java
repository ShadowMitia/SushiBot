package TestSushi.src.Suchi;

import TestSushi.src.Pictures.FindPicture;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;



public abstract class Recette {
	protected static int Rice = 10 ; 
	protected static int Roe = 10 ; 
	protected static int Noori = 10 ;
	protected static int salmon = 5 ; 
	protected static int shrimp = 5 ; 
	protected final String egggris  = "sprites/eggGris.png" ;
	protected final String norigris  = "sprites/noriGris.png";
	protected final String saumongris  ="sprites/saumonGris.png";
	protected final String ricegris  =  "sprites/riceGris.png";
	protected final String shrimpGris  =  "sprites/shrimpGris.png";
	protected final Point phone = new Point (1030,620);
	protected final Point exitPhone = new Point (1050,573);
	protected final Point RiceC = new Point (973,503);
	protected final Point RiceCbuy = new Point (976,487);
	protected final Point Topping = new Point (978,470);
	protected final Point fishEggs = new Point (979,472);
	protected final Point nori = new Point (929,469);
	protected final Point validate = new Point (900,500);
	protected final Point saumon = new Point (910,560);
	protected final Point shrimpCoord= new Point (907,386);

	protected final int  time = 150 ;//200 ;
	protected final int phoneTime = 50 ; 
	protected final int timeTapis = 650; //600 ;


	/**
	 *
	 */
	public static void resetAliments() {
		Rice = 10 ; 
		Roe = 10 ; 
		Noori = 10 ; 
		salmon =5 ; 
		shrimp =5 ; 
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void useRice() throws AWTException, InterruptedException{
		Rice-- ; 
		this.checkAliment();
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void useRoe() throws AWTException, InterruptedException{
		Roe-- ; 
		this.checkAliment();
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void useNoori() throws AWTException, InterruptedException{
		Noori-- ; 
		this.checkAliment();
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void useSalmon() throws AWTException, InterruptedException{
		salmon-- ; 
		this.checkAliment();
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void useShrimp() throws AWTException, InterruptedException{
		shrimp-- ; 
		this.checkAliment();
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void checkAliment() throws AWTException, InterruptedException{

		if (Rice<0){
			this.buyRice();
		} 
		if (Roe<0){
			this.buyRoe();
		}
		if (Noori<0){
			this.buyNoori();
		}
		if (salmon<0){
			this.buySalmon() ; 
		}
		if (shrimp<0){
			this.buyShrimp();
		}

	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void phoneClick() throws AWTException, InterruptedException{
		Robot me = new Robot() ; 
		me.delay(2*phoneTime);
		me.mouseMove(this.phone.x, this.phone.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

    /**
     *
     * @throws AWTException
     */
	public void validatePhone() throws AWTException{
		Robot me = new Robot () ; 
		// valider l'achat le telephone 
		me.delay(2*phoneTime);
		me.mouseMove(this.validate.x, this.validate.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void buyRice() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot() ; 
		// clique sur rice 
		me.delay(phoneTime);
		me.mouseMove(this.RiceC.x, this.RiceC.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le riz
		boolean estGris = true ; 
		while (estGris){
			Thread.sleep(1000);
			FindPicture testRice = new FindPicture (Game.getScreen(100),this.ricegris);
			estGris=testRice.checkImage();
		}
		me.delay(phoneTime);
		me.mouseMove(this.RiceCbuy.x, this.RiceCbuy.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();

		Thread.sleep(6000);
		// mise à jour de la variable 
		Rice+=10 ; 
	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void buyRoe() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(phoneTime);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le roe
		boolean estGris = true ; 
		while (estGris){
			Thread.sleep(1000);
			FindPicture testRoe = new FindPicture (Game.getScreen(100),this.egggris);
			estGris=testRoe.checkImage();
		}
		me.delay(phoneTime);
		me.mouseMove(this.fishEggs.x, this.fishEggs.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		Roe+=10 ; 

	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void buyNoori() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(phoneTime);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le nori
		boolean estGris = true ; 
		while (estGris){
			Thread.sleep(1000);
			FindPicture testNori = new FindPicture (Game.getScreen(100),this.norigris);
			estGris=testNori.checkImage();
		}
		me.delay(phoneTime);
		me.mouseMove(this.nori.x, this.nori.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		Noori+=10 ; 

	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void buySalmon() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(phoneTime);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le saumon
		boolean estGris = true ; 
		while (estGris){
			Thread.sleep(1000);
			FindPicture testSaumon = new FindPicture (Game.getScreen(100),this.saumongris);
			estGris=testSaumon.checkImage();
		}
		me.delay(phoneTime);
		me.mouseMove(this.saumon.x, this.saumon.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		salmon+=5 ; 

	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void buyShrimp() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(phoneTime);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete les crevettes
		boolean estGris = true ; 
		while (estGris){
			Thread.sleep(1000);
			FindPicture testShrimp = new FindPicture (Game.getScreen(100),this.shrimpGris);
			estGris=testShrimp.checkImage();
		}
		me.delay(phoneTime);
		me.mouseMove(this.shrimpCoord.x, this.shrimpCoord.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		shrimp+=5 ;
	}
}
