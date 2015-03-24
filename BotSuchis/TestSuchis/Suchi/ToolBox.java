package Suchi;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ToolBox extends JFrame implements ActionListener {
	
	private int w;
	private int h;
	
	private JButton snap;
	private JButton detect;
	private JButton launch;
	private JButton Auto;
	private JTextField txt;
	
	private int countScreen;
	private GameArea ga;

	
	
	public ToolBox(int largeur, int longeur) {
		
		super("Toolbox");
		
		
		this.w = largeur;
		this.h = longeur;
		this.countScreen = 0;
		this.setSize(this.w, this.h);
		
		this.setLayout(new BorderLayout());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		
		this.snap = new JButton("Screen!");
		this.detect = new JButton("Detection Immediate");
		this.launch = new JButton("Go (johnny go)le jeu");
		this.Auto = new JButton("Auto avec attente");
		
		this.txt = new JTextField();
		
		this.snap.addActionListener(this);
		this.detect.addActionListener(this);
		this.launch.addActionListener(this);
		this.Auto.addActionListener(this);
		
		super.add(this.snap, BorderLayout.NORTH);
		super.add(this.detect, BorderLayout.SOUTH);
		super.add(this.launch, BorderLayout.WEST);
		super.add(this.txt, BorderLayout.CENTER);
		super.add(this.Auto, BorderLayout.EAST);
		
		super.show();
		//super.pack();
	}

	public void Capture(){
		
		
		try {
			BufferedImage screen = new Robot().createScreenCapture(new Rectangle(this.ga.getX(), this.ga.getY(), this.ga.getHeight(), this.ga.getWidth()));
			
			ImageIO.write(screen, "png", new File("ScreenShot"+ Integer.toString(this.countScreen) + ".png"));
			
			this.countScreen++;
			
		} 
		catch (AWTException e) {}
		
		catch (IOException e) {}
		
		
	}
	
	public void CaptureGame(int temps){
		
		try{
			
			super.setVisible(false);
			
			
			Robot bot = new Robot();
			
			bot.mouseMove(0, 0);
			
			Screen screenshot = new Screen(temps); // le parametre est le temps avant le
												// screen en secondes;
			
			screenshot.saveImage("Screenshot_Full_Res");
			screenshot.saveGameArea("Screen_reduit");
			
			int[] tab = new int[4];
			tab = screenshot.getGameArea();
			
			this.ga = new GameArea(tab[0], tab[1], tab[2], tab[3]);
			
			super.setVisible(true);
			
		}
		catch (Exception e){}
		
		
	}
		
	public void Launch(){
		
		try {
			
			Desktop d = Desktop.getDesktop();
			d.browse(new URI("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf"));
			
		} 
		catch (IOException | URISyntaxException e) {}
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == this.snap){
			
			Capture();
			
		}
		
		else if(arg0.getSource() == this.detect){
			
			CaptureGame(1);
			
		}
		else if(arg0.getSource() == this.launch){
			
			Launch();
			
		}
	
		else if(arg0.getSource() == this.Auto) {
			
			String temp = this.txt.getText();
			Launch();
			CaptureGame(Integer.parseInt(temp));
			
		}
	
	}
}

 