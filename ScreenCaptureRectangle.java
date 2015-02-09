import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;

public class ScreenCaptureRectangle {

    Rectangle captureRect;
     public BufferedImage screenCopy ;

    ScreenCaptureRectangle(final BufferedImage screen) {
        screenCopy=  new BufferedImage(screen.getWidth(),screen.getHeight(), screen.getType());//init du  panel 
        final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));// le screen dans le panel 
        
        // cette partie la n'est que le panel avec son bouton et tout le tralala 
        JScrollPane screenScroll = new JScrollPane(screenLabel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(screenScroll, BorderLayout.CENTER);

        final JLabel selectionLabel = new JLabel(
                "Séléctionnez ce que vous voulez couper");
        panel.add(selectionLabel, BorderLayout.SOUTH);

        repaint(screen, screenCopy);
        screenLabel.repaint();
        
        
        //till here 

        screenLabel.addMouseMotionListener(new MouseMotionAdapter() {

            Point start = new Point();

            @Override
            public void mouseMoved(MouseEvent me) {
                start = me.getPoint();
                repaint(screen, screenCopy);
                selectionLabel.setText("Start Point: " + start);
                screenLabel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                Point end = me.getPoint();
                captureRect = new Rectangle(start,
                        new Dimension(end.x-start.x, end.y-start.y));
                repaint(screen, screenCopy);
                screenLabel.repaint();
                selectionLabel.setText("Rectangle: " + captureRect);
            }
        });

        JOptionPane.showMessageDialog(null, panel);
        //retour du rectangle selectionné 
        System.out.println("Rectangle of interest: " + captureRect);
        ///////phase d'enregistrement 
        try {
			BufferedImage sortie = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			
			ImageIO.write(sortie.getSubimage(getBeginX0(), getBeginX1(), getNewWidth()+getBeginX1(), getNewHeight()+getBeginX1()) , "png", new File("/Users/sofiane/desktop/screenshot9.png"));
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    //servent pas à grand chose je ne les utilse que sur l'enregistrement de l'image 
    public int getBeginX0 () { return this.captureRect.x ;}
    public int getBeginX1() { return this.captureRect.y;}
    public int getNewHeight() {return this.captureRect.height;}
    public int getNewWidth(){ return this.captureRect.width;}
    

    public void repaint(BufferedImage orig, BufferedImage copy) {
    	Graphics2D g = copy.createGraphics();
    	g.drawImage(orig,0,0, null);
    	if (captureRect!=null) {
    		g.setColor(Color.RED);
    		g.draw(captureRect);
    		g.setColor(new Color(255,255,255,150));
    		g.fill(captureRect);
    	}
    	g.dispose();
    }

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Game g = new Game ("http://www.jeux-flash-gratuits.biz/games/sushi-go-round.swf");
        BufferedImage game = g.getScreen(10000);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScreenCaptureRectangle(game);
            }
        });
    }
}