package Utility;

import Data.AxialCoords;
import Data.CubeCoords;

/**
 * This class provides static methods for converting between different coordinate systems.
 */
public class CoordTransform {

    /**
     * Converts hex coordinates from the cube system to the axial system.
     *
     * @param cubeCoords The cube coordinate system hex coordinates to be converted to axial
     * coordinates.
     * @return The same hex coordinates represented under the axial coordinate system.
     */
    public static AxialCoords cubeToAxial(CubeCoords cubeCoords) {
        int q = cubeCoords.x;
        int r = cubeCoords.z;
        return new AxialCoords(q, r);
    }

    /**
     * Converts hex coordinates from the axial system to the cube system.
     *
     * @param axialCoords The axial coordinate system hex coordinates to be converted to cube
     * coordinates.
     * @return The same hex coordinates represented under the cube coordinate system.
     */
    public static CubeCoords axialToCube(AxialCoords axialCoords) {
        int x = axialCoords.q;
        int z = axialCoords.r;
        int y = -x - z;
        return new CubeCoords(x, y, z);
    }
}
