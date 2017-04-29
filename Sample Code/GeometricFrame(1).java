import javax.swing.JFrame;

/* An interesting class that does cool stuff with windows */
public class GeometricFrame extends JFrame {
  

  
  /* A method to flip the width and height of the window */
  public void transpose() {
    int width = this.getWidth();   // get the width
    int height = this.getHeight(); // get the height
    this.setSize(height, width);
  }
  
  /* A method to scale the window size */
  public void scale(double factor) {
    int width = (int)(factor * this.getWidth()); // get and scale the width
    int height = (int)(factor * this.getHeight());
    this.setSize(width, height);
  }
  
  /* saves the original title of the window */
  private String savedTitle = null;
  
  /* place the window size on the title bar */
  public void sizeOnTitle(boolean turnOn) {
    if (turnOn)
      super.setTitle(savedTitle + " " + this.getWidth() + "x" + this.getHeight());
    else
      super.setTitle(savedTitle);
  }
  
  /* we are changing the behavior of the setTitle method so that, before
   * setting the title, it stores the title into a field */
  @Override
  public void setTitle(String title) {
    this.savedTitle = title;
    super.setTitle(title);
  }
}

