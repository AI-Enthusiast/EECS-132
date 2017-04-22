/**
 * Models a line in 2D
 *
 * @author Cormac Dacker
 * @since 10/26/2016.
 */
public class Line2D extends Line {

    /*Recalls the primary point of the line*/
    private Point2D p1;

    /*Recalls the secondary point of the line*/
    private Point2D p2;

    /*Recalls the slope of the line*/
    private double slope;

    /*Recalls where the line intercepts the y axis*/
    private double yInt;

    /**
     * Creates a Line2D
     *
     * @param point1 Point2D used as the first point of the Line
     * @param point2 Point2D used as the second point of the Line
     */
    public Line2D(Point2D point1, Point2D point2) {
        super(point1, point2);
        /*Checks to be sure points are not the same so that no line is created*/
        if (point1.equals(point2)) {
            System.out.println("Error: Points are the same. No line can be made.");
        } else {
            this.p1 = point1;
            this.p2 = point2;
        }
    }

    /**
     * The equation of the Line
     *
     * @return the equation of a line in the form of a string
     */
    @Override
    public String toString() {
    /*if slope is vertical*/
        if (this.getSlope() == 0.0) {
      /*return x1 unless it is equal to 0, then return x2*/
            return (p1.getX() != 0.0) ? "x = " + p1.getX() : "x = " + p2.getX();
        }
    /*if slope is not vertical then return the point slope formula with inputted values*/
        else {
            if (this.getYInt() != 0.0) {
                return "y = " + this.getSlope() + "x" + " + " + this.getYInt();
            } else {
                return "y = " + this.getSlope() + "x";
            }
        }

    }
  
  /*equals method unnecessary because it inherits it from Line*/
  
  /*isParallel method unnecessary because it inherits it from Line*/
  
  /*intersection method unnecessary because it inherits is from Line*/

    /**
     * The slope of the Line
     *
     * @return double that represents the slope of the Line
     */
    public double getSlope() {
        return ((p1.getY() - p2.getY()) / (p1.getX() - p2.getX()));
    }

    /**
     * The y intercept of the Line
     *
     * @return double that represents the y intercept of the Line
     */
    public double getYInt() {
        return (p1.getY() + (this.getSlope() * -p1.getX()));
    }
}
