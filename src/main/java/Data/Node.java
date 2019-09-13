package Data;

import java.util.Objects;

public class Node {

    private final AxialCoords coords;
    private final CubeCoords vector;
    private final Node previous;

    public Node(AxialCoords coords, CubeCoords vector, Node previous) {
        this.coords = coords;
        this.vector = vector;
        this.previous = previous;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.coords);
        hash = 71 * hash + Objects.hashCode(this.vector);
        hash = 71 * hash + Objects.hashCode(this.previous);
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
        final Node other = (Node) obj;
        if (!Objects.equals(this.coords, other.coords)) {
            return false;
        }
        if (!Objects.equals(this.vector, other.vector)) {
            return false;
        }
        if (!Objects.equals(this.previous, other.previous)) {
            return false;
        }
        return true;
    }

    public AxialCoords getCoords() {
        return coords;
    }

    public CubeCoords getVector() {
        return vector;
    }

    public Node getPrevious() {
        return previous;
    }

}
