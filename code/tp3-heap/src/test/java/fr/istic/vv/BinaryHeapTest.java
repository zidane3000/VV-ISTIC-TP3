package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

class BinaryHeapTest {
@Test
    void testPushAndCount() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.push(10);
        heap.push(20);
        heap.push(5);
        assertEquals(3, heap.count());
    }

    @Test
    void testPeek() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
        heap.push(10);
        heap.push(20);
        heap.push(5);
        assertEquals(5, heap.peek());
        assertEquals(3, heap.count()); // Ensure count remains the same
    }

    @Test
    void testPop() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
        heap.push(10);
        heap.push(20);
        heap.push(5);
        assertEquals(5, heap.pop());
        assertEquals(2, heap.count()); // Ensure count is decremented
        assertEquals(10, heap.pop());
        assertEquals(1, heap.count());
        assertEquals(20, heap.pop());
        assertEquals(0, heap.count());
    }

    @Test
    void testPopEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::pop);
    }

    @Test
    void testPeekEmptyHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(Comparator.naturalOrder());
        assertThrows(NoSuchElementException.class, heap::peek);
    }

}