package Utility;

import Data.CubeCoords;

/**
 * Class for vector math, with vectors represented as cube coordinates.
 */
public class VectorMath {

    /**
     * Calculates the magnitude (length) of the given vector, represented as cube coordinates.
     * @param vector
     * @return 
     */
    public static double magnitude(CubeCoords vector) {
        return Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.z, 2) + Math.pow(vector.y, 2));
    }
    
    /**
     * Calculates the sum of two vectors.
     * @param a
     * @param b
     * @return The sum of vectors a and b.
     */
    public static CubeCoords sum(CubeCoords a, CubeCoords b) {
        return new CubeCoords(a.x + b.x, a.z + b.z, a.y + b.y);
    }
}
