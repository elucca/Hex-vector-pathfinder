package Data;

public class AxialCoord {

    public final int q;
    public final int r;

    public AxialCoord(int q, int r) {
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
        final AxialCoord other = (AxialCoord) obj;
        if (this.q != other.q) {
            return false;
        }
        if (this.r != other.r) {
            return false;
        }
        return true;
    }

}
