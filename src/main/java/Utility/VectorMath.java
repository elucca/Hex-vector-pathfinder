package Utility;

import Data.CubeCoords;

public class VectorMath {

    public static double Magnitude(CubeCoords vector) {
        return Math.sqrt(vector.x + vector.z + vector.y);
    }
    
    public static CubeCoords Sum(CubeCoords a, CubeCoords b) {
        return new CubeCoords(a.x + b.x, a.z + b.z, a.y + b.y);
    }
}
