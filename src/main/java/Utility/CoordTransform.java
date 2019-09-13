package Utility;

import Data.AxialCoords;
import Data.CubeCoords;

public class CoordTransform {

    public static AxialCoords cubeToAxial(CubeCoords cubeCoords) {
        int q = cubeCoords.x;
        int r = cubeCoords.z;
        return new AxialCoords(q, r);
    }

    public static CubeCoords axialToCube(AxialCoords axialCoords) {
        int x = axialCoords.q;
        int z = axialCoords.r;
        int y = -x - z;
        return new CubeCoords(x, z, y);
    }
}
