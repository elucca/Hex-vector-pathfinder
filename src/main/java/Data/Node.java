package Data;

import java.util.Objects;

/**
 * Represents a node on the pathfinding graph. A graph node is conceptually a
 * pair consisting of a hexagon's coordinates and a velocity vector.
 */
public class Node {

    private final AxialCoords coords;
    private final AxialCoords vector;
    private final int costSoFar;
    private final Node previous;

    /**
     * Constructs a new graph node made up of the given hex coords and velocity
     * vector. Represents the state of a spaceship in both its location and
     * velocity.
     *
     * @param coords The node's hex coordinates.
     * @param vector The node's velocity vector.
     * @param previous The previous node the pathfinding algorithm reached this
     * node from. Used to store the complete path if the goal node is found.
     */
    public Node(AxialCoords coords, AxialCoords vector, Node previous, int costSoFar) {
        this.coords = coords;
        this.vector = vector;
        this.previous = previous;
        this.costSoFar = costSoFar;
    }

    @Override
    public String toString() {
        return Integer.toString(costSoFar);
        //return "Node{" + "coords=" + coords + ", vector=" + vector + ", costSoFar=" + costSoFar + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.coords);
        hash = 71 * hash + Objects.hashCode(this.vector);
        return hash;
    }

    /**
     * Returns whether the given node is equal to this node. Two nodes are considered equal if their
     * hex coordinate and vector are equal, i.e. whether they are, in the implicit graph, the same
     * node. 
     * 
     * @param obj The other node to be compared for equality.
     * @return Whether the nodes are equal.
     */
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
        return true;
    }

    public AxialCoords getCoords() {
        return coords;
    }

    public AxialCoords getVector() {
        return vector;
    }

    public Node getPrevious() {
        return previous;
    }

    public int getCostSoFar() {
        return costSoFar;
    }

}
