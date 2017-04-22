import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * A window with two buttons.  Uses the Java 8 "lambda" and "method reference"
 * style shortcuts for the anonymous classes
 */
public class Java8Frame extends JFrame {
  
  private int numclicks = 0;     // the number of times button was clicked
  private JButton button;        // the button being clicked
  
  /** Create the window */
  public Java8Frame() {
    button = new JButton("clicks : 0");
    JButton resetButton = new JButton("reset");
    
    // "lambda" shortcut to create an anonymous class that implements
    // the ActionListener interface and overrides the actionPerformed method
    button.addActionListener(e -> {
        JButton b = (JButton)e.getSource();
        b.setText("clicks : " + ++numclicks);
        pack();
      }
    );
    
    // "method reference" shortcut to create an anonymous class
    // that implements ActionListener and overrides the actionPerformed
    // method to have it call this object's reset method
    resetButton.addActionListener(this::reset);
    
    getContentPane().add(button, "North");
    getContentPane().add(resetButton, "South");
    pack();
    setVisible(true);
  }
  
  // A method to reset the button text and the number of clicks
  // The input parameter is there so we can use the "method reference" shortcut above.
  private void reset(ActionEvent e) {
    button.setText("clicks : " + (numclicks = 0));
    pack();
  }
  
}