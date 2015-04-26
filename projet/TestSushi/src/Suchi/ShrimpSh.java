package TestSushi.src.Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class ShrimpSh extends Recette  {
	private Point Rice,Noori,shrimp ;

	/**
	 *  Constructeur pour créer un Sushi Shrimp
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public ShrimpSh() throws AWTException, InterruptedException {

		this.Rice=new Point(274,543);
		this.Noori=new Point(211,617);
		this.shrimp = new Point (214,550);
		this.make();
		this.validate();
	}

	/**
	 *
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void make() throws AWTException, InterruptedException{
		super.useRice();
		super.useNoori();
		super.useShrimp();
		super.useShrimp();

		Robot me = new Robot();
		me.delay(2*super.time);
		me.mouseMove(this.Rice.x, this.Rice.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		me.mouseMove(this.Noori.x, this.Noori.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		me.mouseMove(this.shrimp.x, this.shrimp.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		me.mouseMove(this.shrimp.x, this.shrimp.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	/**
	 *
	 * @throws AWTException
	 */
	public void validate () throws AWTException {
		Robot me = new Robot();
		me.delay(super.timeTapis);

		me.mouseMove(554, 535);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(super.timeTapis);
	}
}

