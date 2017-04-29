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
}

