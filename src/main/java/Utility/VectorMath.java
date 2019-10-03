package Utility;

import Data.AxialCoords;
import Data.CubeCoords;

/**
 * Class for vector math, with vectors represented as cube coordinates.
 * Todo: Add overloads for each combination of cube and axial coords so these coord transforms
 * are all done here and other parts of the program don't have to care.
 */
public class VectorMath {

    /**
     * Calculates the magnitude (length) of the given vector in the 3D cube coordinate space.
     *
     * @param vector The vector to calculate the magnitude of expressed in cube coordinates
     * @return The magnitude of the vector in cube coordinate space.
     */
    public static double magnitude(CubeCoords vector) {
        return Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.z, 2) + Math.pow(vector.y, 2));
    }

    /**
     * Calculates the magnitude (length) of the given vector in the 3D cube coordinate space.
     *
     * @param vector The vector to calculate the magnitude of expressed in axial coordinates.
     * @return The magnitude of the vector in cube coordinate space.
     */
    public static double magnitude(AxialCoords vector) {
        return magnitude(CoordTransform.axialToCube(vector));
    }

    /**
     * Calculates the sum of two vectors.
     *
     * @param a The first vector, expressed as cube coordinates.
     * @param b The second vector, expressed as cube coordinates.
     * @return The sum of vectors a and b expressed as a vector in the cube coordinate space.
     */
    public static CubeCoords sum(CubeCoords a, CubeCoords b) {
        return new CubeCoords(a.x + b.x, a.z + b.z, a.y + b.y);
    }

    /**
     * Calculates the sum of two vectors.
     *
     * @param a The first vector, expressed as axial coordinates.
     * @param b The second vector, expressed as axial coordinates.
     * @return The sum of vectors a and b expressed as a vector in the cube coordinate space.
     */
    public static CubeCoords sum(AxialCoords a, AxialCoords b) {
        return sum(CoordTransform.axialToCube(a), CoordTransform.axialToCube(b));
    }
}
