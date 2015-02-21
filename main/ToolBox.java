package main;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ToolBox extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -343788682534799357L;
	private int w;
	private int h;

	private JButton snap;
	private JButton detect;
	private JButton launch;
	private JButton Auto;
	private JButton startBot;
	private JTextField txt;

	private int countScreen;
	public static Rectangle ga;

	public ToolBox(){
		
		
	}
	
	public ToolBox(int largeur, int longeur) {

		super("Toolbox");

		this.w = largeur;
		this.h = longeur;
		this.countScreen = 0;
		this.setSize(this.w, this.h);

		//this.setLayout(new BorderLayout());
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);

		this.snap = new JButton("Screen!");
		this.detect = new JButton("Detect");
		this.launch = new JButton("Go Johnny!");
		this.Auto = new JButton("AutoDetect");
		this.startBot = new JButton("Start Bot!");

		this.txt = new JTextField(5);

		this.snap.addActionListener(this);
		this.detect.addActionListener(this);
		this.launch.addActionListener(this);
		this.Auto.addActionListener(this);
		this.startBot.addActionListener(this);

//		super.add(this.snap, BorderLayout.NORTH);
//		super.add(this.detect, BorderLayout.SOUTH);
//		super.add(this.launch, BorderLayout.WEST);
//		super.add(this.txt, BorderLayout.CENTER);
//		super.add(this.Auto, BorderLayout.EAST);
//		super.add(this.startBot, BorderLayout.EAST);
		
		super.add(this.launch);
		super.add(this.detect);
		super.add(this.txt);
		super.add(this.Auto);
		super.add(this.snap);
		super.add(this.startBot);


		super.setVisible(true);

	}

	public void Capture() throws AWTException {



		BufferedImage screen = new Robot().createScreenCapture(this.ga);
		try {
			ImageIO.write(screen, "png", new File("Screen" + Integer.toString(this.countScreen) + ".png"));

			this.countScreen ++ ;
		}
		
		catch (IOException e) {}
		
	}

	
	public void CaptureGame(int tps) throws AWTException {
		
		
		setVisible(false);
		
		Robot mouveMouse = new Robot();
		
		mouveMouse.mouseMove(0, 0);
		
		try {
			
			Screen screenshot = new Screen(tps);
			screenshot.saveImage("Screen_fullres");
			screenshot.saveGameArea("Screen_reduit");
			this.ga = screenshot.getGameArea();
			
		} 
		
		catch (Exception e) {}
		
		setVisible(true);
		
		
	}

	public void Launch() {

		try {

			Desktop d = Desktop.getDesktop();
			d.browse(new URI("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf"));
		} catch (IOException | URISyntaxException e) {
		}

	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == this.snap) {
			
			try {
				Capture();
			}
			
			catch (AWTException e) {}

		}
		
		else if (arg0.getSource() == this.startBot) {
			
			
			try {
				IA ia = new IA(this.ga);
				ia.initiateGame();
			}
			catch (AWTException e) {} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		else if (arg0.getSource() == this.detect) {
			
			try {
				CaptureGame(1);
			}
			
			catch (AWTException e) {}

		} else if (arg0.getSource() == this.launch) {

			Launch();

		}

		else if (arg0.getSource() == this.Auto) {

			String temp = this.txt.getText();
			
			Launch();
			
			try {
				CaptureGame(Integer.parseInt(temp));
			}
			
			catch (NumberFormatException e) {} 
			
			catch (AWTException e) {}

		}

	}
}
