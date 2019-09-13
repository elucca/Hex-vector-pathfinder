package Data;

import java.util.Objects;

/**
 * Represents a node on the pathfinding graph. A graph node is conceptually a pair consisting of a
 * hexagon's coordinates and a velocity vector.
 */
public class Node {

    private final AxialCoords coords;
    private final CubeCoords vector;
    private final Node previous;

    /**
     * Constructs a new graph node made up of the given hex coords and velocity vector. Represents
     * the state of a spaceship in both its location and velocity.
     * 
     * @param coords The node's hex coordinates.
     * @param vector The node's velocity vector.
     * @param previous The previous node the pathfinding algorithm reached this node
     * from. Used to store the complete path if the goal node is found.
     */
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
