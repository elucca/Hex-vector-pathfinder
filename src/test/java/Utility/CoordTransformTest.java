/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Data.AxialCoords;
import Data.CubeCoords;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordTransformTest {

    @Test
    public void axialToCubeTest() {
        AxialCoords c1 = new AxialCoords(2, 4);
        AxialCoords c2 = new AxialCoords(-4, -2);
        AxialCoords c3 = new AxialCoords(0, 0);
        
        assertEquals(new CubeCoords(2, -6, 4), CoordTransform.axialToCube(c1));
        assertEquals(new CubeCoords(-4, 6, -2), CoordTransform.axialToCube(c2));
        assertEquals(new CubeCoords(0, 0, 0), CoordTransform.axialToCube(c3));
    }
    
}
