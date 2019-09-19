package Main;

import Data.AxialCoords;
import Data.CubeCoords;
import Data.Node;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

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
            assertTrue(graph.nodeExists(new Node(hex, new CubeCoords(0, 0, 0), null, 0)));
        }
    }

    @Test
    public void rhombusShapedMapHasNoExtraneousHexes() {
        // Can't test exhaustively, but tests some border cases.
        int mapSizeQ = 6;
        int mapSizeR = 6;
        Graph graph = new Graph(mapSizeQ, mapSizeR, 10, 1, 1);
        
        assertFalse(graph.nodeExists(new Node(new AxialCoords(-1, 0), new CubeCoords(0, 0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(7, 2), new CubeCoords(0, 0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(1, -1), new CubeCoords(0, 0, 0), null, 0)));
        assertFalse(graph.nodeExists(new Node(new AxialCoords(1, 7), new CubeCoords(0, 0, 0), null, 0)));
    }
}
