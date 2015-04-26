import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Stop extends JPanel implements KeyListener, Runnable {
	
	public Stop() {
	
		addKeyListener(this);
		
	}
	public void run() {
		
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		System.out.println("pouet");
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
}