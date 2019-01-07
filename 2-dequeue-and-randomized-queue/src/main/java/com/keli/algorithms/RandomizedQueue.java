package com.keli.algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Random random;

    private int size;
    private Item[] array;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        random = new Random();
        size = 0;
        array = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        validateItem(item);
        array[size++] = item;

        if (array.length == size) resize(array.length * 2);
    }

    private void validateItem(Item item) {
        if (Objects.isNull(item))
            throw new IllegalArgumentException("Item cannot be null");
    }

    public Item dequeue() {
        validateIsEmpty();

        int randIdx = getRandomIdx(size);
        Item item = array[randIdx];
        array[randIdx] = array[--size];

        if (size > 0 && size == array.length / 4)
            resize(array.length / 2);

        return item;
    }

    private void validateIsEmpty() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue from empty Queue");
    }

    private int getRandomIdx(int upperBound) {
        return random.nextInt(upperBound);
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            copy[i] = array[i];
        }

        this.array = copy;
    }

    public Item sample() {
        validateIsEmpty();
        int randIdx = getRandomIdx(size);
        return array[randIdx];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] toIterate;
        private int currentSize;

        @SuppressWarnings("unchecked")
        public RandomizedQueueIterator() {
            this.currentSize = size;
            this.toIterate = (Item[]) new Object[array.length];
            copyQueue();
        }

        private void copyQueue() {
            System.arraycopy(array, 0, toIterate, 0, array.length);
        }

        @Override
        public boolean hasNext() {
            return currentSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            int randIdx = getRandomIdx(currentSize--);
            Item item = toIterate[randIdx];
            toIterate[randIdx] = toIterate[currentSize];
            toIterate[currentSize] = null;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported operation");
        }
    }
}
