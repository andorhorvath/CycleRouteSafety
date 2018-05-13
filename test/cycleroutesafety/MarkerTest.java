/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cycleroutesafety;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andor Horvath
 */
public class MarkerTest {
    
    public MarkerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of toString method, of class Marker.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Marker instance = new Marker();
        String expResult = "[" + instance.getMarkerID() + "; " + instance.getMarkerDescription() + "; " + instance.getMarkerType() + "]";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMarkerDescription method, of class Marker.
     */
    @Test
    public void testSetMarkerDescription() {
        System.out.println("setMarkerDescription");
        String description = "";
        Marker instance = new Marker();
        instance.setMarkerDescription(description);
        
        String result = instance.getMarkerDescription();
        
        assertEquals (result, description);
    }

    /**
     * Test of seMarkerType method, of class Marker.
     */
    @Test
    public void testSeMarkerType() {
        System.out.println("seMarkerType");
        String markerType = "";
        Marker instance = new Marker();
        instance.seMarkerType(markerType);
        
        String result = instance.getMarkerType();
        
        assertEquals (result, markerType);
    }

    /**
     * Test of getMarkerID method, of class Marker.
     */
    @Test
    public void testGetMarkerID() {
        System.out.println("getMarkerID");
        Marker instance = new Marker();
        int expResult = 0;
        int result = instance.getMarkerID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMarkerDescription method, of class Marker.
     */
    @Test
    public void testGetMarkerDescription() {
        System.out.println("getMarkerDescription");
        Marker instance = new Marker();
        String expResult = null;
        String result = instance.getMarkerDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMarkerType method, of class Marker.
     */
    @Test
    public void testGetMarkerType() {
        System.out.println("getMarkerType");
        Marker instance = new Marker();
        String expResult = null;
        String result = instance.getMarkerType();
        assertEquals(expResult, result);
    }
    
}
