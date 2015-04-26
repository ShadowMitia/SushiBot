package TestSushi.src.Suchi;


import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class ScreenMouse extends Applet implements ActionListener{

   /**
    *
    * @param evt
    */
   public void actionPerformed(ActionEvent evt) {
      String composant = evt.getActionCommand();
      showStatus("Action sur le composant : " + composant);
   }

    /**
     *
     */
   public void init() {
      super.init();

      Button b1 = new Button("boutton 1");
      b1.addActionListener(this);
      add(b1);

      Button b2 = new Button("boutton 2");
      b2.addActionListener(this);
      add(b2);

      Button b3 = new Button("boutton 3");
      b3.addActionListener(this);
      add(b3);
   }
}