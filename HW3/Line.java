/**
 * Models a line in 3D
 *
 * @author Cormac Dacker
 * @since 11/1/2016.
 */
public class Line {
  
  /*Recalls the first point*/
  private Point p1;
  
  /*Recalls the second point*/
  private Point p2;
  
  /**
   * Creates a Line
   *
   * @param p1 first point of the line
   * @param p2 second point of the line
   */
  public Line(Point p1, Point p2) {
    this.p1 = p1;
    this.p2 = p2;
  }
  
  /**
   * Creates a Line
   *
   * @param p1 first point of the Line
   * @param v1 Vector that's point is used as the second point
   */
  public Line(Point p1, Vector v1) {
    double x = v1.getPoint().getX();
    double y = v1.getPoint().getY();
    double z = v1.getPoint().getZ();
    Point p2 = new Point(x, y, z);
    this.p1 = p1;
    this.p2 = p2;
  }
  
  /**
   * Equation of the Line
   *
   * @return equation of the Line in the form of a string
   */
  @Override
  public String toString() {
    return ("x(t) = " + p1.getX() + " + " + (p1.getX() - p2.getX()) + "t\n" +
            "y(t) = " + p1.getY() + " + " + (p1.getY() - p2.getY()) + "t\n" +
            "z(t) = " + p1.getZ() + " + " + (p1.getZ() - p2.getZ()) + "t");
  }
  
  /**
   * Determines if the input is equal to the Line
   *
   * @param o object that is potentially equal to the Line
   * @return boolean of whether or not o equals the Line
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Line) {  
      Line lo = (Line) o;
      return ((Line.isParallel(this, lo)) &&
              (this.p1.equals(lo.getPoint1()) || this.p1.equals(lo.getPoint2())) &&
              (this.p2.equals(lo.getPoint1()) || this.p2.equals(lo.getPoint2())));
    } else {
      return false;
    }
  }
  
  /**
   * The normal vector of the Line
   *
   * @return the vector of the Line
   */
  public Vector getVector() {
    Vector v = new Vector(p1);
    return v;
  }
  
  /**
   * Determines if the inputted lines are parallel
   *
   * @param line1 inputted line that is potentially parallel to v2
   * @param line2 inputted line that is potentially parallel to v1
   * @return boolean of whether or not the two lines are parallel
   */
  public static boolean isParallel(Line line1, Line line2) {
    if (line1.getPoint1().getZ() == 0.0 && line2.getPoint1().getZ() == 0.0 &&
        line1.getPoint2().getZ() == 0.0 && line2.getPoint2().getZ() == 0.0) {
      Line2D line3 = (Line2D) line1;
      Line2D line4 = (Line2D) line2;
      return (line3.getSlope() == line4.getSlope());
    } else {
      /*variables used in the return statement when compairing the ratios of the lines*/
      double x1Ratio = (line1.getPoint1().getX() - line1.getPoint2().getX());
      double x2Ratio = (line2.getPoint1().getX() - line2.getPoint2().getX());
      double y1Ratio = (line1.getPoint1().getY() - line1.getPoint2().getY());
      double y2Ratio = (line2.getPoint1().getY() - line2.getPoint2().getY());
      double z1Ratio = (line1.getPoint1().getZ() - line1.getPoint2().getZ());
      double z2Ratio = (line2.getPoint1().getZ() - line2.getPoint2().getZ());
      return ((y1Ratio / x1Ratio) == (y2Ratio / x2Ratio) && (z1Ratio / x1Ratio) == (z2Ratio / x2Ratio)
                && (y1Ratio / z1Ratio) == (y2Ratio / z2Ratio));
    }
  }
  
//    /**
//     * Determines where the two inputted lines intersect
//     *
//     * @param line1 inputted line that potentially intersects with line2
//     * @param line2 inputted line that potentially intersects with line1
//     * @return the point of intersection of the two lines
//     */
//    public Point intersection(Line line1, Line line2) {
//        if (Line.isParallel(line1, line2)) {
//            return null;
//        } else {
//
//        }
//    }
  
  /**
   * Gets the first point of the Line
   *
   * @return p1 the first point
   */
  public Point getPoint1() {
    return p1;
  }
  
  /**
   * Gets the second point of the Line
   *
   * @return p2 the second point
   */
  public Point getPoint2() {
    return p2;
  }
}