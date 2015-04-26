package Suchi;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Onigiri extends Recette{
	private Point Rice,Noori ; 
	private final int  time = 500 ; 
	
	public Onigiri() throws AWTException, InterruptedException{
		super.useRice();
		super.useRice();
		super.useNoori();
		this.Rice=new Point(274,543);
		this.Noori=new Point(211,617);
		this.make();
		this.validate();
		
	}
	
public void make() throws AWTException{
	Robot me = new Robot();
	me.delay(2*time);
	me.mouseMove(this.Rice.x, this.Rice.y);
	me.mousePress(InputEvent.BUTTON1_MASK);
	me.mouseRelease(InputEvent.BUTTON1_MASK);
	me.delay(time);
	me.mousePress(InputEvent.BUTTON1_MASK);
	me.mouseRelease(InputEvent.BUTTON1_MASK);
	me.delay(time);
	me.mouseMove(this.Noori.x, this.Noori.y);
	me.mousePress(InputEvent.BUTTON1_MASK);
	me.mouseRelease(InputEvent.BUTTON1_MASK);
	
}

public void validate () throws AWTException {
	Robot me = new Robot();
	me.delay(time);
	me.mouseMove(554, 535);
	me.mousePress(InputEvent.BUTTON1_MASK);
	me.mouseRelease(InputEvent.BUTTON1_MASK);
}
	

}
