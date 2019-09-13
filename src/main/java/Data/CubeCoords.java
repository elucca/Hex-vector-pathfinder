package Data;

/**
 * Represents the coordinates of a hex in cube coordinate form. Cube coordinates are three-
 * dimensional, but have a direct analogy to a hex grid and thus can be used to represent hexes
 * on the grid. While less intuitive than axial coords, they are more convenient for many math
 * operations. This class is also used to represent hex grid vectors, which are also necessarily
 * three-dimensional. See https://www.redblobgames.com/grids/hexagons/#coordinates for reference.
 * 
 * Cube coordinates can be easily converted to axial coordinates and vice versa, as needed.
 */
public class CubeCoords {

    public final int x;
    public final int z;
    public final int y;

    /**
     * Constructs a new instance of cube coordinates representing a hex or a vector.
     * @param x The x-coordinate, which corresponds to the axial system's q-coordinate.
     * @param z The z-coordinate, which corresponds to the axial system's r-coordinate.
     * @param y The y-coordinate, which is generated from the axial system's q and r coordinates.
     */
    public CubeCoords(int x, int z, int y) {
        this.x = x;
        this.z = z;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.z;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CubeCoords other = (CubeCoords) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.z != other.z) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + z + ", " + y + ")";
    }
    
    

}
