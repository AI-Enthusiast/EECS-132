/** Lab 11
  * @author Alexander Patrick
  * @author Cormac Dacker
  */

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics.*;
import java.awt.Canvas;
import java.awt.Color;

public class Painter extends JFrame {
  
  JFrame j = new JFrame();
  Canvas canvas = new Canvas();
  
  
  public Painter() {
    j.getContentPane().add(canvas, "Center");
    canvas.addMouseMotionListener(new MouseMotionListener() {
    @Override
    public void mouseDragged(MouseEvent e) {
      canvas.getGraphics().setColor(Color.BLACK);
      canvas.getGraphics().fillOval(e.getX(), e.getY(), 10, 10);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
      ;
    }
  });
    j.pack();
    j.setVisible(true);
  }
  
  
}
