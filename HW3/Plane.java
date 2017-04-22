/**
 * Models a line in 3D
 *
 * @author Cormac Dacker
 * @since 11/4/2016.
 */
public class Plane {

    private Point point1;

    private Vector normalV;

    /**
     * Creates a Plane
     *
     * @param point1  the point of the plane
     * @param normalV the normal vector to the plane
     */
    public Plane(Point point1, Vector normalV) {
        this.normalV = normalV;
        this.point1 = point1;
    }

    /**
     * Creates a Plane
     *
     * @param p1 point 1 of the plane
     * @param p2 point 2 of the plane
     * @param p3 point 3 of the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = new Vector(p1);
        Vector v2 = new Vector(p2);
        Vector v3 = new Vector(v1, Point.distance(p1, p2));
        Vector v4 = new Vector(v2, Point.distance(p2, p3));
        this.normalV = (Vector.crossProduct(v3, v4));
        this.point1 = p2;
    }

    /**
     * Normal Vector of the Plane
     *
     * @return normalV the normal vector of the Plane
     */
    public Vector getNormal() {
        return (normalV);
    }

    /**
     * The equation of the Plane
     *
     * @return equation that represents the Plane
     */
    @Override
    public String toString() {
        return (normalV.getPoint().getX() + "x + " +
                normalV.getPoint().getY() + "y + " +
                normalV.getPoint().getZ() + "z + " +
                (-((point1.getX() * normalV.getPoint().getX()) +
                        (point1.getY() * normalV.getPoint().getY()) +
                        (point1.getZ() * normalV.getPoint().getZ()))));
    }

    /**
     * Determines if the input is equal to the given Plane
     *
     * @param o object that is potentially equal to the Plane
     * @return boolean of whether it is or is not equal to the Plane
     */
    @Override
    public boolean equals(Object o) {
        Plane oPlane = (Plane) o;
        if (o instanceof Plane) {
            return ((this.contains(oPlane.getPoint())) && (this.getNormal().equals(oPlane)));
        } else {
            return false;
        }
    }

    /**
     * Determines if the input is on the Plane
     *
     * @param point1 inputted point that is potentially on the Plane
     * @return boolean of whether it is or not on the Plane
     */
    public boolean contains(Point point1) {
        Point subP = new Point((point1.getX() - this.getPoint().getX()), //a new Point of ...
                (point1.getY() - this.getPoint().getY()), //... point1 - this.getPoint();
                (point1.getZ() - this.getPoint().getZ()));
        Vector crossV = new Vector(subP);
        return (Vector.dotProduct(crossV, this.getNormal()) == 0.0);
    }

    /**
     * Determines if the inputted Planes are equal
     *
     * @param plane1 inputted plane that is potentially equal to plane2
     * @param plane2 inputted plane that is potentially equal to plane1
     * @return boolean of whether planes are equal
     */
    public static boolean isParallel(Plane plane1, Plane plane2) {
        return (Vector.isParallel(plane1.getNormal(), plane2.getNormal()));
    }

    /**
     * Determines if the inputted Planes are orthogonal
     *
     * @param plane1 inputted plane that is potentially orthogonal to plane2
     * @param plane2 inputted plane that is potentially orthogonal to plane1
     * @return boolean of whether or not planes are orthogonal
     */
    public static boolean isOrthogonal(Plane plane1, Plane plane2) {
        return (Vector.isOrthogonal(plane1.getNormal(), plane2.getNormal()));
    }

    /**
     * The point of the Plane
     *
     * @return point1 the point that is used in the constructor to create the Plane
     */
    public Point getPoint() {
        return point1;
    }
}