import javax.swing.JButton;

/** A JButton that is always square */
public class SquareButton extends JButton {
  
  /** The default constructor */
  public SquareButton() {
  }
  
  /** A constructor that sets the text on the button 
    * @param title the text for the button
    */
  public SquareButton(String title) {
    super(title);
  }
  
  /** Gets the height of the button.  To be a square button, we made it be
    * the smaller of the parent class's height and width
    * @return the height of the button
    */
  @Override
  public int getHeight() {
    return (super.getWidth() < super.getHeight()) ? super.getWidth() : super.getHeight();
  }
  
  /** Gets the width of the button.  It returns the same value as getHeight()
    * @return the width of the button
    */
  @Override
  public int getWidth() {
    return this.getHeight();
  }
}