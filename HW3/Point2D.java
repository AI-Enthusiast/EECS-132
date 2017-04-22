/**
 * Models a point in 2D
 *
 * @author Cormac Dacker
 * @since 10/26/2016
 */
public class Point2D extends Point {
    /*Recalls the location of x on an axis*/
    private double x;

    /*Recalls the location of y on an axis*/
    private double y;

    /*Recalls the location of z on an axis, automatically set as 0 by the constructor*/
    private double z;

    /**
     * Creates a Point2D
     *
     * @param x represents x on the x Axis
     * @param y represents y on the y Axis
     */
    public Point2D(double x, double y) {
        super(x, y, 0.0);
        this.x = x;
        this.y = y;
        this.z = 0.0;
    }
  
  /*getX & getY methods unnecessary because it inherits them from Point*/

    /**
     * The equation of the Point
     *
     * @return the equation of the point in the form of a string
     */
    @Override
    public String toString() {
        return ("(" + getX() + ", " + getY() + ")");
    }
  
  /*equals method unnecessary because it inherits it from Point*/
  
  /*distance method unnecessary because it inherits it from Point*/
}
