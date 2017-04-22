/**
 * Models a point in a 3D plane
 *
 * @author Cormac Dacker
 * @since 10/26/2016.
 */
public class Point {

    /*Recalls the location of x on an axis*/
    private double x;

    /*Recalls the location of y on an axis*/
    private double y;

    /*Recalls the location of z on an axis*/
    private double z;

    /**
     * Creates a Point
     *
     * @param x represents x on the x Axis
     * @param y represents y on the y Axis
     * @param z represents z on the z Axis
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Retrieves the x of the Point
     *
     * @return x created in the constructor
     */
    public double getX() {
        return x;
    }

    /**
     * Retrieves the y of the Point
     *
     * @return y created in the constructor
     */
    public double getY() {
        return y;
    }

    /**
     * Retrieves the z of the Point
     *
     * @return z created in the constructor
     */
    public double getZ() {
        return z;
    }

    /**
     * Equation of the Point
     *
     * @return equation that represents the point in the for of a string
     */
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + getZ() + ")"; //returns something like "(x, y, z)"
    }

    /**
     * Determines if input is equal to the Point
     *
     * @param o object that is potentially equal to the Point
     * @return boolean of whether or not o is equal to the Point
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point p = (Point) o;
            return (this.getX() == p.getX() && this.getY() == p.getY() && this.getZ() == p.getZ());
        } else {
            return false;
        }
    }

    /**
     * Distance between two points
     *
     * @param p1 first point used to assist in determining the distance
     * @param p2 second point used to assist in determining the distance
     * @return double the distance between p1 and p2
     */
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2) + Math.pow(p1.getZ() - p2.getZ(), 2));
    }
}
