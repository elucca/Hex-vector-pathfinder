/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Data.Node;
import Pathfinder.Comparators.DijkstraComparator;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jkesala
 */
public class MinHeapTest {

    MinHeap heap;

    @Before
    public void setUp() {
        this.heap = new MinHeap(100, new DijkstraComparator());
    }

    @Test
    public void heapifySingleNodeLeftTest() {
        Node n1 = new Node(null, null, null, 6);
        Node n2 = new Node(null, null, null, 4);
        Node n3 = new Node(null, null, null, 5);

        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);

        heap.delMin();
        heap.delMin();
        heap.delMin();
    }

    @Test
    public void isLeafTest() {
        Node n1 = new Node(null, null, null, 6);
        Node n2 = new Node(null, null, null, 4);
        Node n3 = new Node(null, null, null, 5);

        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);

        assertTrue(heap.isLeaf(2));
        assertTrue(heap.isLeaf(3));
        assertFalse(heap.isLeaf(1));

        assertFalse(heap.isLeaf(0));

        heap.delMin();
        assertFalse(heap.isLeaf(3));
    }
}
