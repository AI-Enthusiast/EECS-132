/** A class that represents a hexagon, and an example of interfaces */
public class Hexagon extends Polygon implements RegularPolygon {
  
  /** the length of a side */
  private double sideLength;
  
  /** Creates a hexagon
    * @param the desired length for each side
    */
  public Hexagon(double sideLength) {
    super(6);
    this.sideLength = sideLength;
  }
  
  /**
   * Returns the area of the hexagon
   * @return the area of the shape
   */
  public double area() {
    return areaOfRP();
  }
  
  /**
   * Returns the length of a side of the hexagon.
   * @return the length of a side
   */
  public double getSideLength() {
    return sideLength;
  }
}