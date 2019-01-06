package com.keli.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Dequeue<Item> implements Iterable<Item> {

    private int size;

    private Node first;
    private Node last;

    public Dequeue() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        validateItem(item);
        Node newFirst = new Node(item);

        if (isEmpty()) {
            addToEmptyQueue(newFirst);
        } else {
            newFirst.next = first;
            first.prev = newFirst;
            first = newFirst;
        }

        size++;
    }

    public void addLast(Item item) {
        validateItem(item);
        Node newLast = new Node(item);

        if (isEmpty()) {
            addToEmptyQueue(newLast);
        } else {
            newLast.prev = last;
            last.next = newLast;
            last = newLast;
        }

        size++;
    }

    private void validateItem(Item item) {
        if (Objects.isNull(item))
            throw new IllegalArgumentException("Cannot add null object");
    }

    private void addToEmptyQueue(Node node) {
        first = node;
        last = first;
    }

    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException("EMPTY Dequeue");
        Item item = first.item;

        first = first.next;
        size--;

        if (isEmpty()) {
            first = null;
            last = null;
        }

        return item;
    }

    public Item removeLast() {
        if (last == null) throw new NoSuchElementException("EMPTY Dequeue");
        Item item = last.item;

        last = last.prev;
        size--;

        if (isEmpty()) {
            first = null;
            last = null;
        }

        return item;
    }

    public Item getLast() {
        if (last == null) throw new NoSuchElementException("EMPTY Dequeue");
        return last.item;
    }

    public Item getFirst() {
        if (first == null) throw new NoSuchElementException("EMPTY Dequeue");
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    public class Node {

        private Node prev;
        private Node next;
        private Item item;

        Node(Item item) {
            this.item = item;
        }
    }

    private class DequeueIterator implements Iterator<Item> {

        private Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more item");

            Item item = node.item;
            node = node.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("REMOVE NOT SUPPORTED");
        }
    }
}
