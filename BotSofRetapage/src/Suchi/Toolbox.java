package Suchi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbox extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2734805186709412418L;
	private int width, height;
	private JButton detect;
	private Game g;
	
	
	public Toolbox(int longeur, int largeur) {
		
		super("Toolbox");
		this.width = largeur;
		this.height = longeur;
		this.setSize(this.width, this.height);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
		this.detect = new JButton("GO");
		this.detect.addActionListener(this);
		this.add(this.detect);
		
		this.setVisible(true);
		
	}
	
	public void launch() {
		
		g = new Game ("http://redcloud.fr/sushi.swf");
	
	}
	
	public void gameRoutine() throws Exception {
	

		Client c;
		try {
			c = new Client();
			g.startLevel1(c);
			g.startLevel2(c);
			g.startLevel3(c);
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void detectGameZone () throws Exception {
		
		Screen scr = new Screen (10000);
		scr.saveImage("Screenshot_full_res");
		scr.saveGameArea("Screenshot_cropped");
		Rectangle rec = scr.getGameArea();
		Game.setOrigin(new Point(rec.x, rec.y));
		Game.setGameZone(rec);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == this.detect) {
			
			launch(); 
			try {
				
				detectGameZone();
				gameRoutine();
				
			}
			catch (Exception e1) { e1.printStackTrace(); }			
			
		}
	}
	
	
	
	
	
	
}