/** An interface to define polygons that have all sides with the same length */
public interface RegularPolygon {
  
  /** Returns the number of sides of the polygon
    * @return the number of sides
    */
  int getNumSides();
  
  /** Returns the length of a side
    * @return the length of a side
    */
  double getSideLength();
  
  /**
   * Returns the area of a regular polygon.
   * Before Java 8, we would make this a static method inside the polygon
   * class so that we can avoid writing the same code in every class that
   * extends RegularPolygon.  Now, we can write the code once hear and the
   * Java compiler will textcopy it into every class that implements 
   * RegularPolygon.
   * @return the area of the polygon
   */
  default double areaOfRP() {
    return 0.25 * getNumSides() * getSideLength() * getSideLength() / Math.tan(Math.PI/getNumSides());
  }
}