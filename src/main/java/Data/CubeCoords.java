package Data;

public class CubeCoords {

    public final int x;
    public final int z;
    public final int y;

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

}
