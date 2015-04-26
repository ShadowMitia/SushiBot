import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;


public class ClicButton extends SearchRatio {
	
	GameWindow window;
	
public ClicButton(File file) {
	super(file);
	window = new GameWindow();
	
	}

public void clique(String s){
	this.findR(s);
	Robot rob;
	try {
		Thread.sleep(1000);
		rob = new Robot();
		System.out.println(window.getDepart().getY());
		System.out.println(this.ratioY*window.getWindowH());
		System.out.println((int)(window.getDepart().getY()+this.ratioY*window.getWindowH()));
		//System.out.println(this.ratioY);
		//System.out.println(this.findX(super.ratioX) + " " + this.findY(super.ratioY));
		//rob.mouseMove(this.findX(this.ratioX), this.findY(this.ratioY));
		rob.mouseMove((int)(window.getDepart().getX()+this.ratioX*window.getWindowW()), (int)(window.getDepart().getY()+this.ratioY*window.getWindowH()));
		rob.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(100);
		rob.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	} catch (AWTException | InterruptedException e) {
				e.printStackTrace();
	}
	
	
}

public int findX(double ratio) {
	return (int) (window.getDepart().getX()+(ratio*window.getWindowW()));
	}
public int findY(double ratio){
	
	return (int) (window.getDepart().getY()+(ratio*window.getWindowH()));
}
public GameWindow getWindow(){return this.window;}
}

