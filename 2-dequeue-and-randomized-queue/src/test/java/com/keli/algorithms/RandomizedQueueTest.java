package com.keli.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RandomizedQueueTest {

    private static final String STRING_ONE = "one";
    private static final String STRING_TWO = "two";
    private static final String STRING_THREE = "three";
    private static final String STRING_FOUR = "four";

    @Test
    public void shouldUseIterator() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);
        underTest.enqueue(STRING_TWO);
        underTest.enqueue(STRING_THREE);
        underTest.enqueue(STRING_FOUR);

        List<String> results = StreamSupport
                .stream(underTest.spliterator(), false)
                .map(String::toString)
                .collect(toList());

        assertThat(results, hasItems(
                STRING_ONE, STRING_TWO, STRING_THREE, STRING_FOUR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotEnqueueNull() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();

        underTest.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotDequeueFromEmptyQueue() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();

        underTest.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotGetSampleFromEmptyQueue() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();

        underTest.sample();
    }

    @Test
    public void shouldGetRandomItem() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);

        List<String> dequeueString = getDequeueString(underTest, 1);

        assertThat(dequeueString, hasItems(STRING_ONE));
    }

    @Test
    public void shouldGetRandomItems() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);
        underTest.enqueue(STRING_TWO);
        underTest.enqueue(STRING_THREE);
        underTest.enqueue(STRING_FOUR);

        List<String> dequeueString = getDequeueString(underTest, 4);

        assertThat(dequeueString, hasItems(
                STRING_ONE, STRING_TWO, STRING_THREE, STRING_FOUR));
    }

    @Test
    public void shouldGetSampleItem() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);

        String dequeueString = underTest.sample();

        assertThat(dequeueString, equalTo(STRING_ONE));
    }

    @Test
    public void shouldGetSampleNotDelete() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);

        underTest.sample();

        assertThat(underTest.size(), equalTo(1));
    }

    @Test
    public void shouldGetSize2() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);
        underTest.enqueue(STRING_TWO);
        underTest.enqueue(STRING_THREE);
        underTest.enqueue(STRING_FOUR);

        int size = underTest.size();

        assertThat(size, equalTo(4));
    }

    @Test
    public void shouldGetSize() {
        RandomizedQueue<String> underTest = new RandomizedQueue<>();
        underTest.enqueue(STRING_ONE);
        underTest.enqueue(STRING_TWO);
        underTest.dequeue();
        underTest.dequeue();

        int size = underTest.size();

        assertThat(size, equalTo(0));
    }

    private List<String> getDequeueString(RandomizedQueue<String> underTest, int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(underTest.dequeue());
        }

        return result;
    }
}