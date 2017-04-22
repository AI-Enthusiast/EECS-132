/**
 * Models a vector
 *
 * @author Cormac Dacker
 * @since 11/2/16
 */
public class Vector {

    /*Recalls the point of the vector*/
    private Point p1;

    /**
     * Creates a Vector
     *
     * @param x represents x on the x Axis
     * @param y represents y on the y Axis
     * @param z represents z on the z Axis
     */
    public Vector(double x, double y, double z) {
        Point p1 = new Point(x, y, z);
        this.p1 = p1;
    }

    /**
     * Creates a Vector
     *
     * @param p1 the point that represents the Vector
     */
    public Vector(Point p1) {
        this.p1 = p1;
    }

    /**
     * Creates a Vector
     *
     * @param direct the direction the vector is going
     * @param length the length of the new Vector
     */
    public Vector(Vector direct, double length) {
        Point p1 = new Point(((direct.getPoint().getX() / direct.magnitude()) * (length)),
                ((direct.getPoint().getY() / direct.magnitude()) * (length)),
                ((direct.getPoint().getZ() / direct.magnitude()) * (length)));
        this.p1 = p1;
    }

    /**
     * Equation of the Vector
     *
     * @return an equation in the form of a string
     */
    @Override
    public String toString() {
        return "<" + p1.getX() + ", " + p1.getY() + ", " + p1.getZ() + ">";
    }

    /**
     * Determines if the input is equal to the given Vector
     *
     * @param o object that is potentially equal to the Vector
     * @return boolean of whether or not o is equals the Vector
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector) {
            Vector ov = (Vector) o;
            return ((ov.p1.getX() == this.p1.getX())
                    && (ov.p1.getY() == this.p1.getY())
                    && (ov.p1.getY() == this.p1.getY()));
        } else {
            return false;
        }
    }

    /**
     * The magnitude of the Vector
     *
     * @return the length (magnitude) of the Vector
     */
    public double magnitude() {
        Point o = new Point(0.0, 0.0, 0.0);
        return Point.distance(this.p1, o);
    }

    /**
     * The unit vector of the Vector
     *
     * @return vector going the same direction as the Vector but with a magnitude of 1
     */
    public Vector unitVector() {
        Vector unitV = new Vector(this, 1.0);
        return unitV;
    }

    /**
     * The sum of the vectors
     *
     * @param v1 vector that will be added to v2
     * @param v2 vector that will be added to v2
     * @return a new vector that is the addition of the two vectors
     */
    public static Vector sum(Vector v1, Vector v2) {
        Vector sumV = new Vector((v1.p1.getX() + v2.p1.getX()),
                (v1.p1.getY() + v2.p1.getY()), (v1.p1.getZ() + v2.p1.getZ()));
        return sumV;
    }

    /**
     * Vector that is scaled
     *
     * @param v1     vector that will be scaled
     * @param scaler double that scales the vector
     * @return a new vector that is the same direction as v1 but scaled by the scaler
     */
    public static Vector scale(Vector v1, double scaler) {
        Vector scaledV = new Vector(((v1.getPoint().getX()) * (scaler)),
                ((v1.getPoint().getY()) * scaler), ((v1.getPoint().getZ()) * scaler));
        return scaledV;
    }

    /**
     * Returns the dot product of of two vectors
     */
    public static double dotProduct(Vector v1, Vector v2) {
        return ((v1.getPoint().getX() * v2.getPoint().getX())
                + (v1.getPoint().getY() * v2.getPoint().getY())
                + (v1.getPoint().getZ() * v2.getPoint().getZ()));
    }

    /**
     * Cross product of two vectors
     *
     * @param v1 vector that will be crossed with v2
     * @param v2 vector that will be crossed with v2
     * @return a new vector that is the result of the cross product
     */
    public static Vector crossProduct(Vector v1, Vector v2) {
        Vector crossedV = new Vector(
                ((v1.getPoint().getY() * v2.getPoint().getZ()) - (v1.getPoint().getZ() * v2.getPoint().getY())),
                ((v1.getPoint().getZ() * v2.getPoint().getX()) - (v1.getPoint().getX() * v2.getPoint().getZ())),
                ((v1.getPoint().getX() * v2.getPoint().getY()) - (v1.getPoint().getY() * v2.getPoint().getX())));
        return crossedV;
    }

    /**
     * The angle between two vectors
     *
     * @param v1 vector that will be used to assist in finding the angle
     * @param v2 vector that will be used to assist in finding the angle
     * @return the angle between the two inputted vectors
     */
    public static double angle(Vector v1, Vector v2) {
        return Math.acos(Vector.dotProduct(v1, v2)) / (v1.magnitude() * v2.magnitude());
    }

    /**
     * Determines if the vectors are orthogonal
     *
     * @param v1 inputted vector that is potentially orthogonal to v2
     * @param v2 inputted vector that is potentially orthogonal to v2
     * @return boolean of whether or not the vectors are orthogonal
     */
    public static boolean isOrthogonal(Vector v1, Vector v2) {
        return (Vector.dotProduct(v1, v2) == 0.0);
    }

    /**
     * Determines if the vectors are parallel
     *
     * @param v1 inputted vector that is potentially parallel to v2
     * @param v2 inputted vector that is potentially parallel to v2
     * @return boolean of whether or not the vectors are parallel
     */
    public static boolean isParallel(Vector v1, Vector v2) {
        Vector vZero = new Vector(0.0, 0.0, 0.0);
        return (vZero.equals(Vector.crossProduct(v1, v2)));
    }

    /**
     * The point of the Vector
     *
     * @return p1 the point
     */
    public Point getPoint() {
        return p1;
    }

}