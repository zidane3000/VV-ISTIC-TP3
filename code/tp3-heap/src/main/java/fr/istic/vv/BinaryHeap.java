package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    private ArrayList<T> heap;
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        T result = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return result;
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap.get(0);
    }

    public void push(T element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    public int count() {
        return heap.size();
    }

    private void heapifyUp(int index) {
        T element = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parent = heap.get(parentIndex);
            if (comparator.compare(element, parent) >= 0) {
                break;
            }
            heap.set(index, parent);
            index = parentIndex;
        }
        heap.set(index, element);
    }

    private void heapifyDown(int index) {
        T element = heap.get(index);
        int half = heap.size() / 2;
        while (index < half) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            int smallestChildIndex = leftChildIndex;
            T smallestChild = heap.get(leftChildIndex);
            if (rightChildIndex < heap.size() && comparator.compare(heap.get(rightChildIndex), smallestChild) < 0) {
                smallestChildIndex = rightChildIndex;
                smallestChild = heap.get(rightChildIndex);
            }
            if (comparator.compare(element, smallestChild) <= 0) {
                break;
            }
            heap.set(index, smallestChild);
            index = smallestChildIndex;
        }
        heap.set(index, element);
    }

}