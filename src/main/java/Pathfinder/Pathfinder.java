package Pathfinder;

import Data.Node;
import Pathfinder.Comparators.*;
import Utility.MinHeap;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * This class contains pathfinding algorithms for finding the shortest path on the supplied graph,
 * according to the graph's cost function.
 */
public class Pathfinder {

    private Graph graph;

    /**
     * Constructs a new pathfinder instance which finds path on the supplied Graph. The Graph
     * instance defines the structure of the graph, including edge weights/state change costs.
     *
     * @param graph The graph on which pathfinding is to be done.
     */
    public Pathfinder(Graph graph) {
        this.graph = graph;
    }

    /**
     * Return the shortest path from the start node to the goal node. The path is found using the
     * specified algorithm. After the algorithm finishes, each node contains a reference to the
     * previous node on the found path, so the path can be extracted from the returned goal node.
     *
     * Todo: Refactor the algorithms to use a single method with only the data structure varying
     * because that's the only actual difference.
     *
     * @param start The starting node.
     * @param goal The goal node.
     * @param algorithm The algorithm to be used for pathfinding, among those currently implemented.
     * @return The goal node, including where it was reached from, if it is reachable, null
     * otherwise.
     */
    public Node findPath(Node start, Node goal, Algorithm algorithm) {
        switch (algorithm) {
            case DIJKSTRA:
                return pathfindingAlgorithm(graph, start, goal, new DijkstraComparator());
            case ASTAR_VECTOR:
                return pathfindingAlgorithm(graph, start, goal, new VectorComparator(goal, graph.getDeltaVMul()));
            case ASTAR_TIME_VECTOR:
                return pathfindingAlgorithm(graph, start, goal, new TimeVectorComparator(goal, graph.getDeltaVMul(), graph.getTimeMul()));
            default:
                return null;
        }
    }

    /**
     * A generic pathfinding algorithm that functions as Dijkstra's algorithm or A* with various
     * heuristics depending on which comparator is supplied to it.
     *
     * @param graph The graph on which the algorithm runs.
     * @param start The starting node.
     * @param goal The goal node.
     * @param comparator The comparator used to compare the values of nodes for the priority queue.
     * @return The goal node, including where it was reached from, if it is reachable, null
     * otherwise.
     */
    /*
    public Node pathfindingAlgorithm(Graph graph, Node start, Node goal, Comparator comparator) {
        Queue<Node> queue = new PriorityQueue<>(comparator);
        Set visited = new HashSet<>();

        queue.add(start);

        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();

            if (node != null && !visited.contains(node)) {
                visited.add(node);

                if (node.equals(goal)) {
                    return node;
                }

                enqueueNeighbors(queue, node);
            }
        }

        return null;
    }
     */
    public Node pathfindingAlgorithm(Graph graph, Node start, Node goal, Comparator comparator) {
        MinHeap queue = new MinHeap(10000000, comparator);
        Set visited = new HashSet<>();

        queue.insert(start);

        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.delMin();

            if (node != null && !visited.contains(node)) {
                visited.add(node);

                if (node.equals(goal)) {
                    return node;
                }

                heapNeighbors(queue, node);
            }
        }

        return null;
    }

    /**
     * Adds the neighbors of the given node to the collection, which is used by a pathfinding
     * algorithm to pick nodes to be processed. (For example, a stack for a priority queue.) The
     * neighbors that are added to the collection are determined by the Graph instance of this
     * pathfinder.
     *
     * @param collection The collection used by a pathfinding algorithm to select nodes to process.
     * @param node The node whose neighbors are to be added to the collection.
     */
    private void enqueueNeighbors(Collection<Node> collection, Node node) {
        collection.addAll(graph.findNeighbors(node));
    }

    private void heapNeighbors(MinHeap heap, Node node) {
        List<Node> neighbors = graph.findNeighbors(node);
        for (Node neighbor : neighbors) {
            heap.insert(neighbor);
        }
    }

}
