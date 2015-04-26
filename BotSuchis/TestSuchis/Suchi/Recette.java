package Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public abstract class Recette {
	protected static int Rice = 10 ; 
	protected static int Roe = 10 ; 
	protected static int Noori = 10 ;
	protected static int salmon = 5 ; 
	protected final Point phone = new Point (1030,620);
	protected final Point exitPhone = new Point (1050,573);
	protected final Point RiceC = new Point (973,503);
	protected final Point RiceCbuy = new Point (976,487);
	protected final Point Topping = new Point (978,470);
	protected final Point fishEggs = new Point (979,472);
	protected final Point nori = new Point (929,469);
	protected final Point validate = new Point (900,500);
	protected final Point saumon = new Point (910,560);

	private final int  time = 500 ; 




	public static void resetAliments() {
		Rice = 10 ; 
		Roe = 10 ; 
		Noori = 10 ; 
		salmon = 5 ; 
	}
	public void useRice() throws AWTException, InterruptedException{
		Rice-- ; 
		this.checkAliment();
	}
	public void useRoe() throws AWTException, InterruptedException{
		Roe-- ; 
		this.checkAliment();
	}
	public void useNoori() throws AWTException, InterruptedException{
		Noori-- ; 
		this.checkAliment();
	}
	public void useSalmon() throws AWTException, InterruptedException{
		salmon-- ; 
		this.checkAliment();
	}

	public void checkAliment() throws AWTException, InterruptedException{

		if (Rice==0){
			this.buyRice();
		}else if (Roe==0){
			this.buyRoe();
		}
		else if (Noori==0){
			this.buyNoori();
		}else if (salmon==0){
			this.buySalmon() ; 
		}

	}

	public void phoneClick() throws AWTException, InterruptedException{
		Robot me = new Robot() ; 
		me.delay(2*time);
		me.mouseMove(this.phone.x, this.phone.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}



	public void validatePhone() throws AWTException{
		Robot me = new Robot () ; 
		// valider l'achat le telephone 
		me.delay(2*time);
		me.mouseMove(this.validate.x, this.validate.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void buyRice() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot() ; 
		// clique sur rice 
		me.delay(time);
		me.mouseMove(this.RiceC.x, this.RiceC.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le riz
		me.delay(time);
		me.mouseMove(this.RiceCbuy.x, this.RiceCbuy.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		// mise à jour de la variable 
		Rice+=10 ; 
	}
	public void buyRoe() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(time);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le roe
		me.delay(time);
		me.mouseMove(this.fishEggs.x, this.fishEggs.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		Roe+=10 ; 

	}
	public void buyNoori() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(time);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le nori
		me.delay(time);
		me.mouseMove(this.nori.x, this.nori.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		Noori+=10 ; 

	}

	public void buySalmon() throws AWTException, InterruptedException{
		this.phoneClick();
		Robot me = new Robot () ; 
		// clique sur topping 
		me.delay(time);
		me.mouseMove(this.Topping.x, this.Topping.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		//achete le saumon
		me.delay(time);
		me.mouseMove(this.saumon.x, this.saumon.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		this.validatePhone();
		Thread.sleep(6000);
		salmon+=5 ; 

	}

	// reste à remplir la partie des coordonnées d'achats 


}
