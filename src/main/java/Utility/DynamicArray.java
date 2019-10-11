package Utility;

import java.util.Arrays;

/**
 * A simple implementation of a dynamic array which can contain an arbitrary number of objects.
 */
public class DynamicArray {

    private Object[] array;
    private int size;

    public DynamicArray(int initialCapacity) {
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    public DynamicArray() {
        this.array = new Object[100];
        this.size = 0;
    }

    public void add(Object o) {
        if ((size + 1) > array.length) {
            enlargeArray();
        }
        this.array[size] = o;
        this.size++;
    }

    private void enlargeArray() {
        Object[] newArray = new Object[size * 2];

        int i = 0;
        while (i < this.array.length) {
            newArray[i] = array[i];
            i++;
        }

        this.array = newArray;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.deepHashCode(this.array);
        return hash;
    }

    /**
     * Return whether this dynamic array is equal with another one. They are considered equal if all
     * objects contained in the array evaluate to equal, while ignoring differences in array size
     * and possible trailing nulls
     *
     * @param obj The object to compare equality to.
     * @return Whether this dynamic array is equal with the object provided as parameter.
     */
    /*
    @Override
    public boolean equals(Object obj) {
        return false;
    }
    */

    public Object[] getArray() {
        return array;
    }

}
