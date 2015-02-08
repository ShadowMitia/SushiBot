import java.awt.AWTException;
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
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ToolBox extends JFrame implements ActionListener {

	private int w;
	private int h;
	private JButton snap;
	private int countScreen;
	private GameArea ga;

	public ToolBox(int largeur, int longeur, GameArea gameA) {

		super("Toolbox");
		this.ga = gameA;
		this.w = largeur;
		this.h = longeur;
		this.countScreen = 0;

		this.setSize(this.w, this.h);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		snap = new JButton("Snap!");
		snap.addActionListener(this);
		add(snap);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			BufferedImage screen = new Robot().createScreenCapture(new Rectangle(this.ga.getX(), this.ga.getY(), this.ga.getHeight(), this.ga.getWidth()));
			ImageIO.write(screen, "png", new File("ScreenShot" +Integer.toString(this.countScreen) + ".png"));
			this.countScreen ++ ;
		} catch (AWTException e) {} catch (IOException e) {}
		

	}

}
