package Utility;

import Data.Node;
import java.util.Comparator;

/**
 * An implementation of a minimum heap for O(log(n)) retrieval of the graph node with the minimum
 * value, as determined by the specified comparator.
 *
 * Todo: Handle the case where the heap cannot fit in the array. This will currently crash.
 */
public class MinHeap {

    private Node[] heap;
    private int heapSize;
    private int maxSize;

    private Comparator comparator;

    /**
     * Construct a new minimum heap which can at maximum hold a number of nodes equal to maxSize.
     *
     * @param maxSize The size of the array used to store the heap.
     * @param comparator The comparator used to determine which nodes have the smaller value.
     */
    public MinHeap(int maxSize, Comparator comparator) {
        this.heap = new Node[maxSize + 1];
        this.maxSize = maxSize;
        this.heapSize = 0;

        this.comparator = comparator;
    }

    /**
     * Return the node with the minimum value, without removing it from the heap.
     *
     * @return The node with the minimum value currently in the heap.
     */
    public Node min() {
        return heap[0];
    }

    /**
     * Return the node with the minimum value and remove it from the heap.
     *
     * @return The node with the minimum value currently in the heap.
     */
    public Node delMin() {
        Node min = heap[0];
        heapSize--;
        heapify(0);

        return min;
    }

    /**
     * Insert the given node into the heap.
     *
     * @param node The node to be inserted.
     */
    public void insert(Node node) {
        if (heapSize >= maxSize) {
            return;
        }

        heapSize++;
        heap[heapSize] = node;
        int i = heapSize;

        // current < parent(current)
        while (comparator.compare(i, parent(i)) == -1) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Heapify the node at the given index, i.e. put a node in its correct position and rearrange
     * the heap array so that it is in fact a heap.
     *
     * @param i
     */
    private void heapify(int i) {
        int leftI = left(i);
        int rightI = right(i);

        if (rightI <= heapSize) {
            int smallestI;

            // If left is smaller than right
            if (comparator.compare(heap[leftI], heap[rightI]) == -1) {
                smallestI = leftI;
            } else {
                smallestI = rightI;
            }

            // If heap[i] is smaller than smallest
            if (comparator.compare(heap[i], heap[smallestI]) == -1) {
                swap(i, smallestI);
                heapify(smallestI);
            } else if (leftI == heapSize && comparator.compare(heap[leftI], heap[i]) == -1) {
                swap(i, leftI);
            }
        }
    }

    /**
     * Returns the index of the parent node of the node with the given index if the parent exists.
     *
     * @param i
     * @return The index of the parent of the node with the given index, or - 1 if the parent
     * doesn't exist.
     */
    private int parent(int i) {
        int parentIndex = (i - 1) / 2;
        if (parentIndex >= heap.length || parentIndex < 0) {
            return -1;
        }

        return parentIndex;
    }

    /**
     * Returns the index of the left child node of the node with the given index if the left child
     * exists.
     *
     * @param i
     * @return The index of the left child of the node with the given index, or - 1 if the left
     * child doesn't exist.
     */
    private int left(int i) {
        int leftIndex = 2 * i + 1;
        if (leftIndex >= heap.length || leftIndex < 0) {
            return -1;
        }

        return leftIndex;
    }

    /**
     * Returns the index of the right child node of the node with the given index if the right child
     * exists.
     *
     * @param i
     * @return The index of the right child of the node with the given index, or - 1 if the right
     * child doesn't exist.
     */
    private int right(int i) {
        int rightIndex = 2 * i + 2;
        if (rightIndex >= heap.length || rightIndex < 0) {
            return -1;
        }

        return rightIndex;
    }

    /**
     * Swap the positions of the nodes at the given indices in the array used to store the heap.
     *
     * @param i The index of the first node.
     * @param i2 The index of the second node.
     */
    private void swap(int i, int i2) {
        Node temp = heap[i];
        heap[i] = heap[i2];
        heap[i2] = temp;
    }

}