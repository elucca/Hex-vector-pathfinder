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
    private static final int head = 1;

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

        heap[0] = new Node(null, null, null, Integer.MIN_VALUE);

        this.comparator = comparator;
    }

    /**
     * Return the node with the minimum value, without removing it from the heap.
     *
     * @return The node with the minimum value currently in the heap.
     */
    public Node min() {
        return heap[head];
    }

    /**
     * Return the node with the minimum value and remove it from the heap.
     *
     * @return The node with the minimum value currently in the heap.
     */
    public Node delMin() {
        Node min = heap[head];
        heapSize--;
        heap[head] = heap[heapSize];
        heapify(head);

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
        int i = heapSize;
        
        while (i > 1 && parent(i) != -1 && comparator.compare(node, heap[parent(i)]) == -1) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = node;
    }

    /**
     * Returns true if the heap is empty and false if it is not.
     *
     * @return Whether the heap is empty.
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Heapify the node at the given index, i.e. put a node in its correct position and rearrange
     * the heap array so that it is in fact a heap.
     *
     * @param i
     */
    private void heapify(int i) {
        // If node is not a leaf and is larger than either of its children
        if (!isLeaf(i)) {
            if (left(i) != -1 && comparator.compare(heap[i], heap[left(i)]) == 1
                    || left(i) != -1 && comparator.compare(heap[i], heap[right(i)]) == 1) {
                // If left child is smaller than right child
                if (comparator.compare(heap[left(i)], heap[right(i)]) == -1) {
                    swap(i, left(i));
                    heapify(left(i));
                } else {
                    swap(i, right(i));
                    heapify(right(i));
                }
            }
        }
    }

    /**
     * Returns the index of the parent node of the node with the given index.
     *
     * @param i
     * @return The index of the parent of the node with the given index.
     */
    private int parent(int i) {
        int parentIndex = i / 2;
        if (parentIndex >= heap.length || parentIndex < 0) {
            return -1;
        }

        return parentIndex;
    }

    /**
     * Returns the index of the left child node of the node with the given index.
     *
     * @param i
     * @return The index of the left child of the node with the given index. child doesn't exist.
     */
    private int left(int i) {
        int leftIndex = 2 * i;
        if (leftIndex >= heap.length || leftIndex < 0) {
            return -1;
        }

        return leftIndex;
    }

    /**
     * Returns the index of the right child node of the node with the given index.
     *
     * @param i
     * @return The index of the right child of the node with the given index.
     */
    private int right(int i) {
        int rightIndex = 2 * i + 1;
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

    /**
     * Returns true if the node at the given index is a leaf, false if it is not, including if the
     * node doesn't exist.
     *
     * @param i The index of the node to be evaluated.
     * @return True if the index is a leaf.
     */
    private boolean isLeaf(int i) {
        if (i >= heapSize / 2 && i <= heapSize) {
            return true;
        }

        return false;
    }

}
