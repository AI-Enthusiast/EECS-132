import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * A window with two clickable buttons
 */
public class PreLab11 extends JFrame implements ActionListener {

    /*Recalls the JButton button2*/
    private JButton button1;

    /*Recalls the JButton button2*/
    private JButton button2;

    /**
     * Creates a window with two clickable buttons
     */
    public PreLab11() {
        button1 = new JButton("Button 1");
        button1.addActionListener(this);
        button2 = new JButton("Button 2");
        button2.addActionListener(this);


        getContentPane().add(this.button1, "North");
        getContentPane().add(this.button2, "South");

        this.pack();
    }

    /**
     * Method required by ActionListener
     * Prints a message about the which button was clicked
     *
     * @param e info on which button was clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("You clicked Button 1");
        } else {
            System.out.println("You clicked Button 2");
        }
    }
}