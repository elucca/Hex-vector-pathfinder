
package Data;

import java.util.Comparator;

/**
 * Comparator for comparing the value of two nodes. Used for the priority queue Dijkstra's algorithm
 * uses for finding the neighboring node with the lowest total cost so far.
 */
public class DijkstraComparator implements Comparator<Node> {
    
    @Override
    public int compare(Node node1, Node node2) {
        if (node1 == null) {
            return -1;
        }
        
        if (node2 == null) {
            return 1;
        }
        
        int node1Cost = node1.getCostSoFar();
        int node2Cost = node2.getCostSoFar();
        
        if (node1Cost == node2Cost) {
            return 0;
        }
        
        if (node1Cost < node2Cost) {
            return -1;
        }
        
        return 1;
    }
    
}
