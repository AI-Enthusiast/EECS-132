import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Cormac Dacker
 * @since 11/19/16
 */
public class TestTsuro {

    /**
     * Tests the makeBoard method
     */
    @Test
    public void testMakeBoard(){
        Tsuro mb1 = new Tsuro(0,0); //test 0
        assertEquals(0, mb1.getRows());
        assertEquals(0, mb1.getCols());
        Tsuro mb2 = new Tsuro(1,1); //test 1
        assertEquals(1, mb2.getRows());
        assertEquals(1, mb2.getCols());
        Tsuro mb3 = new Tsuro(7,7); //test many
        assertEquals(7, mb3.getRows());
        assertEquals(7, mb3.getCols());
    }

    /**
     * Tests the makeHandOne and makeHandTwo methods
     */
    @Test
    public void testMakeHand(){
        Tsuro mh1 = new Tsuro(6,6,0); //test 0
        assertEquals(0, mh1.getHandsize());
        Tsuro mh2 = new Tsuro(6, 6, 1); //test 1
        assertEquals(1, mh2.getHandsize());
        Tsuro mh3 = new Tsuro(6, 6, 3); //test many
        assertEquals(3, mh3.getHandsize());
    }

    /**
     * Test the getRows method
     */
    @Test
    public void testGetRows(){
        Tsuro gr1 = new Tsuro(0,0); //test 0
        assertEquals(0, gr1.getRows());
        Tsuro gr2 = new Tsuro(1,1); //test 1
        assertEquals(1, gr2.getRows());
        Tsuro gr3 = new Tsuro(7,7); //test many
        assertEquals(7, gr3.getRows());
    }

    /**
     * Test the getCols method
     */
    @Test
    public void testGetCols(){
        Tsuro gc1 = new Tsuro(0,0); //test 0
        assertEquals(0, gc1.getCols());
        Tsuro gc2 = new Tsuro(1,1); //test 1
        assertEquals(1, gc2.getCols());
        Tsuro gc3 = new Tsuro(7,7); //test many
        assertEquals(7, gc3.getCols());
    }

    /**
     * Test the getHandsize method
     */
    @Test
    public void testGetHandsize(){
        Tsuro mh1 = new Tsuro(6, 6, 0); //test 0
        assertEquals(0, mh1.getHandsize());
        Tsuro mh2 = new Tsuro(6, 6, 1); //test 1
        assertEquals(1, mh2.getHandsize());
        Tsuro mh3 = new Tsuro(6, 6, 4); //test many
        assertEquals(4, mh3.getHandsize());
    }

    /**
     * Test the getter and setter for buttonPressed methods
     */
    @Test
    public void testButtonPressed(){

    }

    /**
     * Test the getBoardPannels methods
     */
    @Test
    public void testGetBoardPanels(){

    }

    /**
     * Test the getter and setter for whosTurn Method
     */
    @Test
    public void testWhosTurn(){
        Tsuro wt = new Tsuro(); //test 0
        assertFalse(wt.getWhosTurn());
        wt.setWhosTurn(true); //test
        assertTrue(wt.getWhosTurn());
    }

    /**
     * Test the inCorrectHand method
     */
    @Test
    public void testInCorrectHand(){

    }

    /**
     * Test the getBBLocation method
     */
    @Test
    public void testGetBBLocation(){

    }

    /**
     * Test the isLegalMove method
     */
    @Test
    public void testIsLegalMove(){

    }

    /**
     * Test the addTwo method
     */
    @Test
    public void testAddTwo(){
        Tsuro at = new Tsuro();
        /*Tests the possible inputs for the array used in rotateArray*/
        assertEquals(2, at.addTwo(0));
        assertEquals(3, at.addTwo(1));
        assertEquals(5, at.addTwo(2));
        assertEquals(4, at.addTwo(3));
        assertEquals(6, at.addTwo(4));
        assertEquals(7, at.addTwo(5));
        assertEquals(1, at.addTwo(6));
        assertEquals(0, at.addTwo(7));
        /*Tests for impossible array inputs to be thorough*/
        assertEquals(0, at.addTwo(12));
        assertEquals(0, at.addTwo(-1));
        assertEquals(0, at.addTwo(-100));
        assertEquals(0, at.addTwo(33));
    }

    /**
     * Test the rotateArray method
     */
    @Test
    public void testRotateArray(){

    }

    /**
     * Test the rotate method
     */
    @Test
    public void testRotate(){

    }

    /**
     * Test the actionPerformed method
     */
    @Test
    public void testActionPerformed(){

    }
}
