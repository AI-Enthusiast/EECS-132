import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/** A window with a clickable button.
  * An example of using interfaces to register for events
  */
public class MyButtonFrame extends JFrame implements ActionListener {
  
  /** Create a window with a clickable button */
  public MyButtonFrame() {
    JButton button = new JButton("Click me");   // the button
    button.addActionListener(this);             // register to receive events
    
    getContentPane().add(button, "North");
    this.pack();
    this.setVisible(true);
  }
  
  /**
   * The method required by ActionListener.  The method that is run when
   * a button click happens.  It will print a message and change the size of
   * the window
   * @param e  information about the button click
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("I was clicked");
    setSize(getWidth() + 10, getHeight() + 10);
  }
}