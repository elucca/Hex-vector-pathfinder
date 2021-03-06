/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pathfinder;

import Pathfinder.Comparators.DijkstraComparator;
import Pathfinder.Pathfinder;
import Pathfinder.Graph;
import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Collections;

/**
 *
 * @author jkesala
 */
public class PathfinderTest {

    /**
     * Tests that the path is valid in that each state transition it makes is possible. (not
     * necessarily correct in the sense of being the shortest path) This test depends on Graph
     * finding the correct neighbors, which is tested in its own test class.
     * 
     * Todo: This is a bit outdated know as it only tests Dijkstra and there are now other 
     * algorithms.
     */
    @Test
    public void foundPathIsValid() {
        Graph graph = new Graph(10, 10, 5, 1, 1);
        Pathfinder pathfinder = new Pathfinder(graph);

        Node start = new Node(new AxialCoords(0, 0), new AxialCoords(0, 0), null, 0);
        Node goal = new Node(new AxialCoords(5, 0), new AxialCoords(0, 0), null, 0);

        Node foundGoal = pathfinder.pathfindingAlgorithm(graph, start, goal, new DijkstraComparator());

        Node node = foundGoal;
        while (node.getPrevious() != null) {
            List<Node> neighbors = graph.findNeighbors(node.getPrevious());
            assertTrue(neighbors.contains(node));
            node = node.getPrevious();
        }

    }
}
