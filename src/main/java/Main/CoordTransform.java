package Main;

import Data.AxialCoord;
import Data.CubeCoord;

public class CoordTransform {

    public static AxialCoord cubeToAxial(CubeCoord cubeCoord) {
        int q = cubeCoord.x;
        int r = cubeCoord.z;
        return new AxialCoord(q, r);
    }

    public static CubeCoord axialToCube(AxialCoord axialCoord) {
        int x = axialCoord.q;
        int z = axialCoord.r;
        int y = -x - z;
        return new CubeCoord(x, z, y);
    }

}
