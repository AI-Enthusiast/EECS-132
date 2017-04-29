import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** A window with one button. 
  *  
  * @author Cormac Dacker
  * @secondAuthor Alexander Patrick
  */
public class ButtonJFrame extends JFrame implements ActionListener {
  
  /** My button. */
  private JButton b1;
  private JTextArea textArea = new JTextArea();
  private int timesClicked = 0;
  JPanel panel = new JPanel(new GridLayout(3,3));
  JButton[][] buttons = new JButton[3][3];
  
  
  /** Create one button in the center. */
  public ButtonJFrame() {
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        buttons[i][j] = new JButton();
        buttons[i][j].addActionListener(this);
        panel.add(buttons[i][j]);
      }
    }
    
    b1 = new JButton("Click me");
    b1.addActionListener(this);
    
    Container c = this.getContentPane();
    c.add(this.textArea, "South");
    c.add(this.b1, "North");
    c.add(this.panel, "Center");
    
    this.pack();
  }
  
  /** React to a button click:  change the text on the button
    * @param e  information about the button click event that occurred
    */
  public void actionPerformed(ActionEvent e) {
    JButton b = (JButton) e.getSource(); // this points to what b1 points to!
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        if (buttons[i][j] == e.getSource()) {
          i++;
          j++;
          textArea.append("Row " + i + ", Column " + j + "\n");
        }
      }
    }
    
    // b.setText("Click count: " + ++timesClicked); // change the text of the button
  if (e.getSource() == b1) {
    textArea.append("Click count: " + timesClicked++ + "\n");
  }
    this.pack();
  }
  
}