package Data;

/**
 * Represents the coordinates of a hex in two-dimensaionl axial coordinates. The q-axis represents
 * columns, and the r-axis rows. The r-axis is tilted to the left in order to fit the hexes. This
 * type of coordinate is easier to understand and work with, and so is most commonly used in the
 * program. For most calculations, however, cube coordinates are more convenient.
 * 
 * Do not represent vectors with axial coordinates. Use cube coordinates instead.
 * 
 * Axial coordinates can be easily converted to cube coordinates and vice versa, as needed.
 */
public class AxialCoords {

    public final int q;
    public final int r;

    /**
     * Constructs a new instance of axial coordinates representing a hex.
     * 
     * @param q The q-coordinate, which stands for rows and corresponds to the cube system's
     * x-coordinate.
     * @param r The r-coordinate, which stands for columns and correspnods to the cube system's
     * z-coordinate.
     */
    public AxialCoords(int q, int r) {
        this.q = q;
        this.r = r;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.q;
        hash = 59 * hash + this.r;
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
        final AxialCoords other = (AxialCoords) obj;
        if (this.q != other.q) {
            return false;
        }
        if (this.r != other.r) {
            return false;
        }
        return true;
    }

}
