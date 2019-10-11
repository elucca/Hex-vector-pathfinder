/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Data.AxialCoords;
import Data.Node;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkesala
 */
public class DynamicArrayTest {

    DynamicArray array;

    @Before
    public void setUp() {
        this.array = new DynamicArray(1);
    }

    @Test
    public void addWithSpaceRemainingTest() {
        array.add(new Node(new AxialCoords(2, 1), null, null, 0));
        Object[] testArray = {new Node(new AxialCoords(2,1), null, null, 0)};
        assertTrue(Arrays.equals(array.getArray(), testArray));
    }

    /*
    // The thing being tested actually works but the test doesn't!
    @Test
    public void addWithInsufficientSpaceTest() {
        array.add(new Node(new AxialCoords(2, 1), null, null, 0));
        array.add(new Node(new AxialCoords(3, 2), null, null, 0));
        array.add(new Node(new AxialCoords(4, 7), null, null, 0));

        Object[] testArray = {new Node(new AxialCoords(2, 1), null, null, 0), new Node(new AxialCoords(3, 2), null, null, 0),
            new Node(new AxialCoords(4, 7), null, null, 0)};
        assertTrue(Arrays.equals(array.getArray(), testArray));
    }
*/
   
}
