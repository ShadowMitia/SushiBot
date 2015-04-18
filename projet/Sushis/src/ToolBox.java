

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
	private JButton saveSprites;
	private JButton makeSushi;
	private JTextField txt;

	private int countScreen;
	public static Rectangle ga;
	private Manager manager;

	public ToolBox() {

	}

	public ToolBox(int largeur, int longeur) throws AWTException {

		super("Toolbox");

		this.w = largeur;
		this.h = longeur;
		this.countScreen = 0;
		this.setSize(this.w, this.h);

		// this.setLayout(new BorderLayout());
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
		this.saveSprites = new JButton("Save Sprites");
		this.makeSushi = new JButton("mkSushi");

		this.txt = new JTextField(5);

		this.snap.addActionListener(this);
		this.detect.addActionListener(this);
		this.launch.addActionListener(this);
		this.Auto.addActionListener(this);
		this.startBot.addActionListener(this);
		this.saveSprites.addActionListener(this);
		this.makeSushi.addActionListener(this);

		super.add(this.launch);
		super.add(this.detect);
		super.add(this.txt);
		super.add(this.Auto);
		super.add(this.snap);
		super.add(this.startBot);
		super.add(this.saveSprites);
		super.add(this.makeSushi);

		super.setVisible(true);

	}

	public void Capture() throws AWTException {

		BufferedImage screen = new Robot().createScreenCapture(this.ga);
		try {
			ImageIO.write(screen, "png",
					new File("Screen" + Integer.toString(this.countScreen)
							+ ".png"));

			this.countScreen++;
		}

		catch (IOException e) {
		}

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

		catch (Exception e) {
		}

		setVisible(true);

	}

	public void Launch() {

		try {

			Desktop d = Desktop.getDesktop();
			d.browse(new URI(
					"http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf"));

		} catch (IOException | URISyntaxException e) {
		}

	}

	public void Run() throws InterruptedException, AWTException {

		Thread.sleep(200);
		IA ia;

		ia = new IA(this.ga);

		ia.initiateGame();

		this.manager = new Manager();

	}

	public void Serve() throws InterruptedException {

		while (true) {

			try {

				this.manager.CheckBar();

			} catch (AWTException | InterruptedException e) {}

			Thread.sleep(2000);
			
		}
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == this.snap) {

			try {
				Capture();
			}

			catch (AWTException e) {
			}

		}

		else if (arg0.getSource() == this.startBot) {

			try {
				Run();
			} catch (InterruptedException | AWTException e) {
			}

		}

		else if (arg0.getSource() == this.detect) {

			try {
				CaptureGame(1);
			}

			catch (AWTException e) {
			}

		} else if (arg0.getSource() == this.launch) {

			Launch();

		}

		else if (arg0.getSource() == this.Auto) {

			String temp = this.txt.getText();

			Launch();

			try {
				CaptureGame(Integer.parseInt(temp));
			}

			catch (NumberFormatException e) {
			}

			catch (AWTException e) {
			}

			try {
				Run();
			} catch (InterruptedException | AWTException e) {
			}

			try {
				Serve();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (arg0.getSource() == this.saveSprites) {

			try {
				IA ia = new IA(this.ga);
				ia.saveSprites();

			} catch (AWTException e) {
			} catch (IOException e) {
			}

		} else if (arg0.getSource() == this.makeSushi) {

			try {
				Serve();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
