package TestSushi.src.Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class California extends Recette {
	private Point Rice,Noori,Roe ;

	/**
	 * Constructeur, va appeller les différentes méthodes pour créer un california
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	California() throws AWTException, InterruptedException {

		this.Rice=new Point(274,543);   // mettre des rice.x et rice.y
		this.Noori=new Point(211,617);
		this.Roe=new Point(291,620);
		this.make();
		this.validate();

	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void make() throws AWTException, InterruptedException{
		Robot me = new Robot();
		super.useNoori();
		super.useRice();
		super.useRoe();

		me.delay(2*super.time);
		me.mouseMove(this.Rice.x, this.Rice.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseMove(this.Noori.x, this.Noori.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseMove(this.Roe.x, this.Roe.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

	}

    /**
     *
     * @throws AWTException
     * @throws InterruptedException
     */
	public void validate () throws AWTException, InterruptedException {
		Robot me = new Robot();
		me.delay(super.timeTapis);

		me.mouseMove(554, 535);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(super.timeTapis);


	}

}
