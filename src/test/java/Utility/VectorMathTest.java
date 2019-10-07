/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Data.CubeCoords;
import org.junit.Test;
import static org.junit.Assert.*;

public class VectorMathTest {

    @Test
    public void sumTest() {
        CubeCoords v1 = new CubeCoords(2, -5, 3);
        CubeCoords v2 = new CubeCoords(9, 2, 8);
        CubeCoords v3 = new CubeCoords(0, 0, 0);
        assertEquals(new CubeCoords(11, -3, 11), VectorMath.sum(v1, v2));
        assertEquals(v2, VectorMath.sum(v2, v3));
    }

    @Test
    public void magnitudeTest() {
        CubeCoords v1 = new CubeCoords(-2, 3, 8);
        CubeCoords v2 = new CubeCoords(-1, 0, 0);
        CubeCoords v3 = new CubeCoords(0, 0, 0);

        assertEquals(8.7749643, VectorMath.magnitude(v1), 0.0001);
        assertEquals(1, VectorMath.magnitude(v2), 0.0001);
        assertEquals(0, VectorMath.magnitude(v3), 0.0001);
    }

    @Test
    public void hexLengthTest() {
        CubeCoords v1 = new CubeCoords(0, 0, 0);
        CubeCoords v2 = new CubeCoords(-3, 2, 1);

        assertEquals(0, VectorMath.hexLength(v1));
        assertEquals(3, VectorMath.hexLength(v2));
    }
}
