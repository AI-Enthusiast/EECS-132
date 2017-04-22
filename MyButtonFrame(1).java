import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/** A window with a clickable button.
  * An example of using interfaces to register for events
  */
public class MyButtonFrame extends JFrame implements ActionListener {
  private JButton button1;
  private JButton button2;
  private JTextArea textArea;
  
  /** Create a window with a clickable button */
  public MyButtonFrame() {
    JButton button3;
    final JButton button4;
    
    button1 = new JButton("Click me");       // the button
    button1.addActionListener(this);         // register to receive events
    button2 = new JButton("Click me, too");
    button2.addActionListener(this);         // use the same action listener for buttons 1 and 2
    button3 = new JButton("Don't click");    // button3 gets a different action listener
    button3.addActionListener(new WestButtonListener());
    button4 = new JButton("Click!!");
    // we are creating an anonymous class here for button4's listener!!
    button4.addActionListener(new ActionListener() {
      private int count = 0;
      @Override
      public void actionPerformed(ActionEvent e) {
        if (count++ % 2 == 0)
          button4.setText("You Did Click Me!");
        else
          button4.setText("Do that again!");
      }
    });
    
    textArea = new JTextArea(30,30);
    getContentPane().add(button1, "North");
    getContentPane().add(button2, "South");
    getContentPane().add(textArea, "Center");
    getContentPane().add(button3, "West");
    getContentPane().add(button4, "East");
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
    JButton button = (JButton)e.getSource();  // get the button that was clicked
    if (button == button1) {
      System.out.println("I was clicked");
      setSize(getWidth() + 10, getHeight() + 10);
    }
    else {
      setSize(getWidth() - 10, getHeight() - 10);
    }
  }
  
  /** An inner class (a nested type) that is the action listener for the
    * west button */
  public class WestButtonListener implements ActionListener {
    
    /**
     * Handles the button clicks.
     * @param e  information about the button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      MyButtonFrame.this.textArea.append("Don't click me!\n");
    }
  }
}
