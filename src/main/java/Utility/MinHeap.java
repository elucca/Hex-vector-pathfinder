package Utility;

import Data.Node;
import java.util.Arrays;
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

    private final Comparator comparator;

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
        //System.out.println("Heap size: " + heapSize);
        //System.out.println("Node at heapSize: " + heap[heapSize]);
        
        Node min = heap[head];
        heap[head] = heap[heapSize];
        heapSize--;

        //System.out.println("Pre-heapify");
        //System.out.println(Arrays.toString(heap));
        heapify(head);

        //System.out.println("Heapify result: ");
        //System.out.println(Arrays.toString(heap));
        
        //print();
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

        while (comparator.compare(heap[i], heap[parent(i)]) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
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
     * Prints a representation of the heap. Specifically, prints the cost so far of each node as
     * this is the relevant part of them for the heap.
     */
    public void print() {
        for (int i = 1; i <= heapSize / 2; i++) {
            StringBuilder string = new StringBuilder();
            if (heap[i] != null) {
                string.append(" Parent: " + heap[i].getCostSoFar());
            }

            if (heap[left(i)] != null) {
                string.append(" Left: " + heap[left(i)].getCostSoFar());
            }

            if (heap[right(i)] != null) {
                string.append(" Right: " + heap[right(i)].getCostSoFar());
            }
            System.out.println(string);

            System.out.println();
        }
    }

    /**
     * Heapify the node at the given index, i.e. put a node in its correct position and rearrange
     * the heap array so that it fulfills the heap conditions.
     *
     * @param i The index of the node to heapify.
     */
    void heapify(int i) {
        // If node is not a leaf and is greater than either of its children
        if (!isEmpty() && !isLeaf(i)) {
            if (comparator.compare(heap[i], heap[left(i)]) > 0
                    || comparator.compare(heap[i], heap[right(i)]) > 0) {
                // If left child is smaller than right child
                if (comparator.compare(heap[left(i)], heap[right(i)]) < 0) {
                    swap(i, left(i));
                    heapify(left(i));
                } else {
                    swap(i, right(i));
                    heapify(right(i));
                }
            }
        }
    }

    /*
    private void minHeapify(int pos) {

        // If the node is a non-leaf node and greater 
        // than any of its child 
        if (!isLeaf(pos)) {
            if (Heap[pos] > Heap[leftChild(pos)]
                    || Heap[pos] > Heap[rightChild(pos)]) {

                // Swap with the left child and heapify 
                // the left child 
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                } // Swap with the right child and heapify 
                // the right child 
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }
     */
    /**
     * Returns the index of the parent node of the node with the given index.
     *
     * @param i
     * @return The index of the parent of the node with the given index.
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Returns the index of the left child node of the node with the given index.
     *
     * @param i
     * @return The index of the left child of the node with the given index. child doesn't exist.
     */
    private int left(int i) {
        return (2 * i);
    }

    /**
     * Returns the index of the right child node of the node with the given index.
     *
     * @param i
     * @return The index of the right child of the node with the given index.
     */
    private int right(int i) {
        return (2 * i) + 1;
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
    boolean isLeaf(int i) {
        if (i > (heapSize / 2) && i <= heapSize) {
            return true;
        }

        return false;
    }

    public Node[] getHeap() {
        return heap;
    }

    public void setHeap(Node[] heap) {
        this.heap = heap;
    }

}
