import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * A window with four buttons and a text area that works as a simple text editor.
 * @author <em>Alexander Patrick</em>
 * @author <em>Cormac Dacker</em>
 */
public class Lab9JFrame extends JFrame {

  /** North button. */
  private JButton northButton;

  /** South button. */
  private JButton southButton;

  /** East button. */
  private JButton eastButton;

  /** West button. */
  private JButton westButton;

  /** Text area, in the center. */
  private JTextArea centerArea;

  /**
   * Create four buttons in the north, south, east, and west, and
   * create a 40x80-character text area in the center.
   */
  public Lab9JFrame() {
    // Create the four buttons and the text area.
    this.northButton = new JButton("north button");
    this.southButton = new JButton("south button");
    this.eastButton  = new JButton("east button");
    this.westButton  = new JButton("west button");
    this.centerArea  = new JTextArea("Center", 40, 80); // 40 lines high, 80 chars wide.

    // Add the buttons and the text area to this window.
    Container c = this.getContentPane();
    c.add(this.eastButton, "East");
    c.add(this.northButton, "North");
    c.add(this.centerArea, "Center");
    c.add(this.southButton, "South");
    c.add(this.westButton, "West");
    
    this.pack();
  }
  
  public void fileDisplay(String s) throws IOException {
    centerArea.setText("");
    BufferedReader br = new BufferedReader(new FileReader(s));
    
    /* Courtesy of lab instructor, to help us with our terrible coding practices */
    String x = new String();
    while ((x = br.readLine()) != null) {
      centerArea.append(x + "\n");
    }
    
    /*
    while (true) {
      String string = br.readLine();
      if (string != null) {
        centerArea.append(string);
        centerArea.append("\n");
      }
      else {
        break;
      }
    }
    */
    
    br.close();
    this.pack();
  }
}