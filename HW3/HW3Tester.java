import org.junit.*;

import static org.junit.Assert.*;

/**
 * Testing Doc for HW3
 *
 * @author Cormac Dacker
 * @since 10/27/2016.
 */
public class HW3Tester {

    /*
     * Tests Point2D
     */
    @Test
    public void testPoint2D() {
    /*Test the getX method of Point2D*/
        Point2D x1 = new Point2D(0.0, 0.0);
        assertEquals("Failed to get the proper x", 0.0, x1.getX(), 0);  //test 0
        Point2D x2 = new Point2D(1.0, 0.0);
        assertEquals("Failed to get the proper x", 1.0, x2.getX(), 0); //test 1
        Point2D x3 = new Point2D(100.0, 0.0);
        assertEquals("Failed to get the proper x", 100.0, x3.getX(), 0); //test many
    
    /*Test the getY method of Point2D*/
        Point2D y1 = new Point2D(0.0, 0.0);
        assertEquals("Failed to get the proper y", 0.0, y1.getY(), 0);  //test 0
        Point2D y2 = new Point2D(0.0, 1.0);
        assertEquals("Failed to get the proper y", 1.0, y2.getY(), 0); //test 1
        Point2D y3 = new Point2D(0.0, 100.0);
        assertEquals("Failed to get the proper y", 100.0, y3.getY(), 0); //test many
    
    /*Test the equals method of Point2D*/
        Point2D e1 = new Point2D(0.0, 0.0);
        Point2D a1 = new Point2D(0.0, 0.0);
        assertTrue(e1.equals(a1));  //test 0
        Point2D e2 = new Point2D(0.0, 1.0);
        Point2D a2 = new Point2D(0.0, 0.0);
        assertFalse(e2.equals(a2));  //test 1
        Point2D e3 = new Point2D(-30.0, 100.0);
        Point2D a3 = new Point2D(100.0, -400.0);
        assertFalse(e3.equals(a3)); //test many
    
    /*Tests the toString method of Point2D*/
        Point2D s1 = new Point2D(0.0, 0.0);
        assertEquals("Incorrect String output", "(0.0, 0.0)", s1.toString()); //test 0
        Point2D s2 = new Point2D(1.0, 1.0);
        assertEquals("Incorrect String output", "(1.0, 1.0)", s2.toString()); //test 1
        Point2D s3 = new Point2D(100.0, 100.0);
        assertEquals("Incorrect String output", "(100.0, 100.0)", s3.toString()); //test many
    
    /*Test the distance method of Point2D*/
        Point2D d1 = new Point2D(0.0, 0.0);
        Point2D p1 = new Point2D(0.0, 0.0);
        assertEquals("Incorrect distance returned", 0.0, Point2D.distance(d1, p1), 0); //test 0
        Point2D d2 = new Point2D(1.0, 2.0);
        Point2D p2 = new Point2D(2.0, 2.0);
        assertEquals("Incorrect distance returned", 1.0, Point2D.distance(d2, p2), 0); //test 1
        Point2D d3 = new Point2D(-1.0, -2.0);
        Point2D p3 = new Point2D(-2.0, -2.0);
        assertEquals("Incorrect distance returned", 1.0, Point2D.distance(d3, p3), 0); //test -1
        Point2D d4 = new Point2D(18.0, 15.0);
        Point2D p4 = new Point2D(14.0, 12.0);
        assertEquals("Incorrect distance returned", 5.0, Point2D.distance(d4, p4), 0); //test many
        Point2D d5 = new Point2D(-18.0, -15.0);
        Point2D p5 = new Point2D(-14.0, -12.0);
        assertEquals("Incorrect distance returned", 5.0, Point2D.distance(d5, p5), 0); //test -many
    }

    /*
     * Tests Point
     */
    @Test
    public void testPoint() {
    /*Test the getX method of Point*/
        Point x1 = new Point(0.0, 0.0, 0.0);
        assertEquals("Failed to get the proper x", 0.0, x1.getX(), 0);  //test 0
        Point x2 = new Point(1.0, 0.0, 0.0);
        assertEquals("Failed to get the proper x", 1.0, x2.getX(), 0); //test 1
        Point x3 = new Point(100.0, 0.0, 0.0);
        assertEquals("Failed to get the proper x", 100.0, x3.getX(), 0); //test many
    
    /*Test the getY method of Point*/
        Point y1 = new Point(0.0, 0.0, 0.0);
        assertEquals("Failed to get the proper y", 0.0, y1.getY(), 0);  //test 0
        Point y2 = new Point(0.0, 1.0, 0.0);
        assertEquals("Failed to get the proper y", 1.0, y2.getY(), 0); //test 1
        Point y3 = new Point(0.0, 100.0, 0.0);
        assertEquals("Failed to get the proper y", 100.0, y3.getY(), 0); //test many
    
    /*Test the getZ method of Point*/
        Point z1 = new Point(0.0, 0.0, 0.0);
        assertEquals("Failed to get the proper z", 0.0, z1.getZ(), 0);  //test 0
        Point z2 = new Point(0.0, 0.0, 1.0);
        assertEquals("Failed to get the proper z", 1.0, z2.getZ(), 0); //test 1
        Point z3 = new Point(0.0, 0.0, 100.0);
        assertEquals("Failed to get the proper z", 100.0, z3.getZ(), 0); //test many
    
    /*Test the equals method of Point*/
        Point e1 = new Point(0.0, 0.0, 0.0);
        Point a1 = new Point(0.0, 0.0, 0.0);
        assertTrue(e1.equals(a1));  //test 0
        Point e2 = new Point(1.0, 5.0, 1.0);
        Point a2 = new Point(1.0, 1.0, 1.0);
        assertFalse(e2.equals(a2));  //test 1
        Point e3 = new Point(100.0, -330.0, 12);
        Point a3 = new Point(-100.0, 150.0, -700);
        assertFalse(e3.equals(a3)); //test many
    
    /*Tests the toString method of Point*/
        Point s1 = new Point(0.0, 0.0, 0.0);
        assertEquals("Incorrect String output", "(0.0, 0.0, 0.0)", s1.toString()); //test 0
        Point s2 = new Point(1.0, 1.0, 1.0);
        assertEquals("Incorrect String output", "(1.0, 1.0, 1.0)", s2.toString()); //test 1
        Point s3 = new Point(100.0, 100.0, 100.0);
        assertEquals("Incorrect String output", "(100.0, 100.0, 100.0)", s3.toString()); //test many
    
    /*Test the distance method of Point*/
        Point d1 = new Point(0.0, 0.0, 0.0);
        Point p1 = new Point(0.0, 0.0, 0.0);
        assertEquals("Incorrect distance returned", 0.0, Point.distance(d1, p1), 0); //test 0
        Point d2 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(2.0, 2.0, 3.0);
        assertEquals("Incorrect distance returned", 1.0, Point.distance(d2, p2), 0); //test 1
        Point d3 = new Point(-1.0, -2.0, -3.0);
        Point p3 = new Point(-2.0, -2.0, -3.0);
        assertEquals("Incorrect distance returned", 1.0, Point.distance(d3, p3), 0); //test -1
        Point d4 = new Point(18.0, 2.0, 15.0);
        Point p4 = new Point(14.0, 2.0, 12.0);
        assertEquals("Incorrect distance returned", 5.0, Point.distance(d4, p4), 0); //test many
        Point d5 = new Point(-18.0, -2.0, -15.0);
        Point p5 = new Point(-14.0, -2.0, -12.0);
        assertEquals("Incorrect distance returned", 5.0, Point.distance(d5, p5), 0); //test -many
    }

    /*
     * Tests Line2D
     */
    @Test
    public void testLine2D() {
        Point2D p1 = new Point2D(0.0, 0.0);
        Point2D p2 = new Point2D(2.0, 0.0);
        Point2D p3 = new Point2D(-3.0, 6.0);
        Point2D p4 = new Point2D(0.0, -2.0);
        Point2D p5 = new Point2D(100.0, 100.0);
        Point2D p6 = new Point2D(2.0, 2.0);
        Point2D p7 = new Point2D(4.0, -1.0);
        Point2D p8 = new Point2D(8.0, 3.0);
        Point2D p9 = new Point2D(1.0, 0.0);
        Point2D p10 = new Point2D(0.0, 1.0)

    /*Test the toString method of Line2d*/
        Line2D s1 = new Line2D(p1, p2);
        assertEquals("Incorrect String output", "x = 2.0", s1.toString()); //test 0
        Line2D s2 = new Line2D(p2, p1);
        assertEquals("Incorrect String output", "x = 2.0", s2.toString()); //test 0 (double check)
        Line2D s3 = new Line2D(p2, p3);
        assertEquals("Incorrect String output", "y = -1.2x + 2.4", s3.toString()); //test 1
        Line2D s4 = new Line2D(p4, p5);
        assertEquals("Incorrect String output", "y = 1.02x + -2.0", s4.toString()); //test many
        Line2D s5 = new Line2D(p5, p4);
        assertEquals("Incorrect String output", "y = 1.02x + -2.0", s5.toString()); //test many (double check)
    
    /*Test the equals method of Line2D*/
        Line2D e1 = new Line2D(p4, p5);
        Line2D e2 = new Line2D(p5, p4);
        assertTrue(e1.equals(e2)); //test 0
        Line2D e3 = new Line2D(p4, p2);
        assertFalse(e1.equals(e3)); //test 1
        Line2D e4 = new Line2D(p9, p8);
        assertFalse(e1.equals(e4)); //test many
    
    /*Test the isParallel method of Line2D*/
        Line2D pL1 = new Line2D(p1, p6);
        Line2D pL2 = new Line2D(p7, p8);
        assertTrue(Line2D.isParallel(pL2, pL1)); //test 0
    
    /*Test the intersection method Line2D*/
    
    
    /*Test the getSlope method of Line2D*/
        Line2D m1 = new Line2D(p9, p1);
        assertEquals("Incorrect slope", 0.0, m1.getSlope(), 0.005); //test 0
        Line2D m2 = new Line2D(p6, p1);
        assertEquals("Incorrect slope", 1.0, m2.getSlope(), 0.005); //test 1
        Line2D m3 = new Line2D(p7, p1);
        assertEquals("Incorrect slope", -0.25, m3.getSlope(), 0.005); //test many
    
    /*Test the getYInt method of Line2D*/
        Line2D y1 = new Line2D(p6, p1);
        assertEquals("Incorrect Y intercept", 0.0, y1.getYInt(), 0.005); //test 0
        Line2D y2 = new Line2D(p10, p5);
        assertEquals("Incorrect Y intercept", 1.0, y2.getYInt(), 0.005) //test1
        Line2D y3 = new Line2D(p4, p8);
        assertEquals("Incorrect Y intercept", -2.0, y3.getYInt(), 0.005) //test many
    }

    /*
     * Tests Line
     */
    @Test
    public void testLine() {
        Vector v1 = new Vector(1, 2, 3);
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 1, 1);
        Point p4 = new Point(1, 2, 3);
        Point p5 = new Point(-1, -1, -1);
        Point p6 = new Point(-3, -2, -3);
        Point p7 = new Point(1, 2, 4);
        Point p8 = new Point();
        Point p9 = new Point();
        Point p10 = new Point();

    /*Test the toString method of Line*/
        Line s1 = new Line(p1, v1); //used to test the (point, vector) constructor
        assertEquals("Incorrect String output", "x(t) = 0.0 + -1.0t\n" + //test 0
                "y(t) = 0.0 + -2.0t\n" +
                "z(t) = 0.0 + -3.0t", s1.toString());
        Line s2 = new Line(p2, p4);
        assertEquals("Incorrect String output", "x(t) = 1.0 + 0.0t\n" + //test 1
                "y(t) = 1.0 + -1.0t\n" +
                "z(t) = 1.0 + -2.0t", s2.toString());
        Line s3 = new Line(p5, p6);
        assertEquals("Incorrect String output", "x(t) = -1.0 + 2.0t\n" + //test many
                "y(t) = -1.0 + 1.0t\n" +
                "z(t) = -1.0 + 2.0t", s3.toString());

    /*Test the equals method of Line*/
        Line e1 = new Line(p1, p2);
        assertFalse("Incorrectly asserted true", e1.equals(v1)); //test 0
        Line e2 = new Line(p1, p7);
        assertFalse("Incorrectly asserted true", e1.equals(e2)); //test 1
        Line e3 = new Line(p1, p2);
        assertFalse("Incorrectly asserted true", e1.equals(e3)); //test many
    
    /*Test the getVector method of Line*/
        Line gvL1 = new Line(p2, p4);
        Vector gvV1 = new Vector(p2)
        assertEquals("Incorrect Vector returned", gvV1, gvL1.getVector()); //test 1

    
    /*Test the isParallel method of Line*/
    
    /*Test the intersection method of Line*/
    }

    /*
     * Tests Vector
     */
    @Test
    public void testVector() {
    
    /*Test the toString method of Vector*/
    
    /*Test the equals method of Vector*/
    
    /*Test the magnitude method of Vector*/
    
    /*Test the unitVector method of Vector*/
    
    /*Test the sum method of Vector*/
    
    /*Test the scale method of Vector*/
    
    /*Test the dotProduct method of Vector*/
    
    /*Test the crossProduct method of Vector*/
    
    /*Test the angle method of Vector*/
    
    /*Test the isOrthogonal method of Vector*/
    
    /*Test the isParallel method of Vector*/
    }

    /*
     * Tests Plane
     */
    public void testPlane() {
    
    /*Test the getNormal method of Plane*/
    
    /*Test the toString method of Plane*/
    
    /*Test the equals method of Plane*/
    
    /*Test the contains method of Plane*/
    
    /*Test the isParallel of Plane*/
    
    /*Test the isOrthogonal method of Plane*/
    }
}
