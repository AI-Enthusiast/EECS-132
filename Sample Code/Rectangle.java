/** A class the represents a rectangle */
public class Rectangle extends Quadrilateral {
  /** the width of the rectangle */
  private double width;
  
  /** the length of the rectangle */
  private double length;
  
  /**
   * Creates a rectangle with the given dimensions
   * @param width the desired width
   * @param height the desired height
   */
  public Rectangle(double width, double length) {
    this.width = width;
    this.length = length;
  }
  
  /**
   * Change the width of the rectangle
   * @param width the new width
   */
  public void setWidth(double width) {
    this.width = width;
  }
  
  /**
   * Return the width of the rectangle
   * @return the rectangle's width
   */
  public double getWidth() {
    return width;
  }
  
  /**
   * Change the length of the rectangle
   * @param length the new length
   */
  public void setLength(double length) {
    this.length = length;
  }
  
  /**
   * Return the length of the rectangle
   * @return the rectangle's length
   */
  public double getLength() {
    return length;
  }
  
  /**
   * Returns the area of the rectangle
   * @return the rectangle's area
   */
  public double area() {
    return getWidth() * getLength();
  }
  
  /** Doubles the width of the rectangle */
  public void doubleWidth() {
    setWidth(getWidth() * 2.0);
  }
  
  /**
   * Returns true if the input value is a Rectangle with the same width and length
   * @param o an object to compare against this rectangle
   * @return true if the input object is a Rectangle with the same dimensions
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Rectangle) {
      Rectangle r = (Rectangle)o;
      return r.getLength() == this.getLength() &&
        r.getWidth() == this.getWidth();
    }
    else
      return false;
  }
}