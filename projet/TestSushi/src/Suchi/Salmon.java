package Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Salmon extends Recette {
	private Point Rice,Noori,Saumon ; 
	Salmon() throws AWTException, InterruptedException {

		this.Rice=new Point(274,543);
		this.Noori=new Point(211,617);
		this.Saumon=new Point(214,714);
		this.make();
		this.validate();

	}
	public void make() throws AWTException, InterruptedException{

		super.useRice();
		super.useSalmon();
		super.useSalmon();
		super.useNoori();
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

		me.mouseMove(this.Saumon.x, this.Saumon.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

		me.mouseMove(this.Saumon.x, this.Saumon.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.delay(super.time);
		me.mouseRelease(InputEvent.BUTTON1_MASK);

	}
	public void validate () throws AWTException, InterruptedException {
		Robot me = new Robot();
		me.delay(super.timeTapis);
		me.mouseMove(554, 535);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(super.timeTapis);


	}


}
