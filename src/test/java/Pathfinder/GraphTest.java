package Pathfinder;

import Pathfinder.Graph;
import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import Utility.CoordTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GraphTest {

    Graph graph;

    @Before
    public void setUp() {
    }

    @Test
    public void rectangleMapHasAllHexes() {
        int mapSizeQ = 16;
        int mapSizeR = 10;
        Graph graph = new Graph(mapSizeQ, mapSizeR, 10, 1, 1);

        List<AxialCoords> hexesOnMap = new ArrayList<>();

        // Generating a rectangle-shaped map is somewhat involved. This algorithm has been verified
        // elsewhere, in the Lightsecond game project where it is used for map generation.
        int qOffset = 0;
        int offsetCounter = 0;
        for (int r = 0; r < mapSizeR; r++) {
            if (offsetCounter == 2) {
                qOffset++;
                offsetCounter = 0;
            }
            offsetCounter++;

            for (int q = 0; q < mapSizeQ; q++) {
                AxialCoords hexCoords = new AxialCoords(q - qOffset, r);
                hexesOnMap.add(hexCoords);
            }
        }
        
        for (AxialCoords axialCoords : hexesOnMap) {
            Node node = new Node(axialCoords, new AxialCoords(0, 0), null, 0);
            assertTrue(graph.nodeExists(node));
        }
    }

    @Test
    public void rectangleMapHasNoExtraneousHexes() {
        // Can't test exhaustively, but tests some border cases. Hopefully at least catches
        // off-by-one errors.
        Graph graph = new Graph(6, 6, 5, 1, 1);

        assertFalse(graph.nodeExists(new Node(new AxialCoords(-1, 0), new AxialCoords(0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(6, 0), new AxialCoords(0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(0, -1), new AxialCoords(0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(0, 7), new AxialCoords(0, 0), null, 0)));
    }

    /* These tests temporarily commented out until I figure out whether I want to keep the possiblity
    for rhombus-shaped maps
    
    @Test
    public void rhombusShapedMapHasAllHexes() {

        int mapSizeQ = 10;
        int mapSizeR = 16;
        Graph graph = new Graph(mapSizeQ, mapSizeR, 10, 1, 1);

        List<AxialCoords> hexesOnMap = new ArrayList<>();

        for (int r = 0; r < mapSizeR; r++) {
            for (int q = 0; q < mapSizeQ; q++) {
                hexesOnMap.add(new AxialCoords(q, r));
            }
        }

        for (AxialCoords hex : hexesOnMap) {
            assertTrue(graph.nodeExists(new Node(hex, new CubeCoords(0, 1, 1), null, 0)));
        }
    }

    @Test
    public void rhombusShapedMapHasNoExtraneousHexes() {
        // Can't test exhaustively, but tests some border cases.
        Graph graph = new Graph(10, 10, 5, 1, 1);
        
        assertFalse(graph.nodeExists(new Node(new AxialCoords(-1, 0), new CubeCoords(0, 0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(10, 2), new CubeCoords(0, 0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(1, -1), new CubeCoords(0, 0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(1, 10), new CubeCoords(0, 0, 0), null, 0)));
    }
     */
    /**
     * Test the case where all neighbors exist. (they are on map, and below max velocity)
     */
    @Test
    public void findNeighborsTestFullSet() {
        Graph graph = new Graph(10, 10, 5, 1, 1);
        // Node comfortably in the middle of the graph.
        Node node = new Node(new AxialCoords(3, 1), new AxialCoords(0, 2), null, 0);

        // This test case is painstakingly worked out on paper.
        AxialCoords[] correctHexCoords = new AxialCoords[]{
            new AxialCoords(3, 3),
            new AxialCoords(4, 3),
            new AxialCoords(4, 2),
            new AxialCoords(3, 2),
            new AxialCoords(2, 3),
            new AxialCoords(2, 4),
            new AxialCoords(3, 4),};

        CubeCoords[] correctVectors = new CubeCoords[]{
            new CubeCoords(0, -2, 2),
            new CubeCoords(1, -3, 2),
            new CubeCoords(1, -2, 1),
            new CubeCoords(0, -1, 1),
            new CubeCoords(-1, -1, 2),
            new CubeCoords(-1, -2, 3),
            new CubeCoords(0, -3, 3)
        };

        Node[] correctNeighbors = new Node[7];

        int i = 0;
        while (i < 7) {
            correctNeighbors[i] = new Node(correctHexCoords[i], CoordTransform.cubeToAxial(correctVectors[i]), null, 0);
            i++;
        }

        List<Node> foundNeighbors = graph.findNeighbors(node);
        List<Node> correctNeighborsList = new ArrayList<>(Arrays.asList(correctNeighbors));

        assertTrue(correctNeighborsList.containsAll(foundNeighbors) && foundNeighbors.containsAll(correctNeighborsList));
    }

    /**
     * Test the case where some neighbors exist, and others are outside the map.
     */
    @Test
    public void findNeighborsTestMapEdge() {

    }

    /**
     * Test the case where some neighbors exist, and others are above max velocity.
     */
    @Test
    public void findNeighborsTestMaxVel() {

    }

    /**
     * Test the case where no neighbors exist as all of them are off the map.
     */
    @Test
    public void findNeighborsTestOffMap() {

    }

    /**
     * Test the case where no neighbors exist as all are above max velocity.
     */
    @Test
    public void findNeighborsTestAboveMaxVel() {

    }

    // Do I need to test mixed cases where some nodes are above max velocity and others are off map?
}
