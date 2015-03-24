package Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Maki extends Recette{
	private Point Rice,Noori,Roe ; 
	Maki() throws AWTException, InterruptedException {
		this.Rice=new Point(274,543);
		this.Noori=new Point(211,617);
		this.Roe=new Point(291,620);
		this.make();
		this.validate();
		super.useRice();
		super.useRoe();
		super.useRoe();
		super.useNoori();
	}
	public void make() throws AWTException{
		Robot me = new Robot();
		me.delay(1000);
		me.mouseMove(this.Rice.x, this.Rice.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.Noori.x, this.Noori.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.Roe.x, this.Roe.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
		me.delay(500);
		me.mouseMove(this.Roe.x, this.Roe.y);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void validate () throws AWTException {
		Robot me = new Robot();
		me.delay(500);
		me.mouseMove(554, 535);
		me.mousePress(InputEvent.BUTTON1_MASK);
		me.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
