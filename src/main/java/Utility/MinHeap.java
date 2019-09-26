package Utility;

import Data.Node;
import java.util.Comparator;

/**
 * An implementation of a minimum heap for O(log(n)) retrieval of the graph node with the minimum
 * value, as determined by the specified comparator.
 *
 * Todo: Resize the heap if maxSize would be exceeded
 */
public class MinHeap {

    private Node[] heap;
    private int heapSize;

    private Comparator comparator;

    /**
     * Construct a new minimum heap which can at maximum hold a number of nodes equal to maxSize.
     *
     * @param maxSize The size of the array used to store the heap.
     * @param comparator The comparator used to determine which nodes have the smaller value.
     */
    public MinHeap(int maxSize, Comparator comparator) {
        this.heap = new Node[maxSize + 1];
        this.heapSize = 0;
        this.comparator = comparator;
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
                // swap i and smallest
                heapify(smallestI);
            } else if (leftI == heapSize && comparator.compare(heap[leftI], heap[i]) == -1) {
                // swap
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
        int parentIndex = i / 2;
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
        int leftIndex = 2 * i;
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
        int rightIndex = 2 * i + 1;
        if (rightIndex >= heap.length || rightIndex < 0) {
            return -1;
        }

        return rightIndex;
    }

}
