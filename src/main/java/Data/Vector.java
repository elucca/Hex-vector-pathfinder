package Data;

public class Vector {
    
    public final int x;
    public final int z;
    public final int y;

    public Vector(int x, int z, int y) {
        this.x = x;
        this.z = z;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.z;
        hash = 67 * hash + this.y;
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
        final Vector other = (Vector) obj;
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