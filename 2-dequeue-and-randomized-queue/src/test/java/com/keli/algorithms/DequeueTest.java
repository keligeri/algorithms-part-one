package com.keli.algorithms;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

public class DequeueTest {

    private static final String STRING_ONE = "one";
    private static final String STRING_TWO = "two";
    private static final String STRING_THREE = "three";
    private static final String STRING_FOUR = "four";

    @Test
    public void shouldUseIteratorInFifoOrder() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addLast(STRING_TWO);
        underTest.addFirst(STRING_ONE);

        String result = StreamSupport
                .stream(underTest.spliterator(), false)
                .map(String::toString)
                .collect(Collectors.joining(","));

        String[] splitResult = result.split(",");
        assertThat(splitResult[0], containsString(STRING_ONE));
        assertThat(splitResult[1], containsString(STRING_TWO));
    }

    @Test
    public void shouldAddItemsInDifferentOrder() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addLast(STRING_THREE);
        underTest.addLast(STRING_FOUR);
        underTest.addFirst(STRING_TWO);
        underTest.addFirst(STRING_ONE);

        assertThat(underTest.removeLast(), equalTo(STRING_FOUR));
        assertThat(underTest.removeFirst(), equalTo(STRING_ONE));
    }

    @Test
    public void shouldRemoveFirstAfterAddFirst() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);

        String removed = underTest.removeFirst();

        assertThat(removed, equalTo(STRING_ONE));
    }

    @Test
    public void shouldRemoveFirstAfterAddLast() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addLast(STRING_ONE);

        String removed = underTest.removeFirst();

        assertThat(removed, equalTo(STRING_ONE));
    }

    @Test
    public void shouldRemoveLastAfterAddLast() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addLast(STRING_ONE);

        String removed = underTest.removeLast();

        assertThat(removed, equalTo(STRING_ONE));
    }

    @Test
    public void shouldRemoveLastAfterAddFirst() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);

        String removed = underTest.removeLast();

        assertThat(removed, equalTo(STRING_ONE));
    }

    @Test
    public void shouldRemoveLast() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);
        underTest.addLast(STRING_TWO);

        String removed = underTest.removeLast();

        assertThat(removed, equalTo(STRING_TWO));
    }

    @Test
    public void shouldRemoveFirst() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);
        underTest.addLast(STRING_TWO);

        String removed = underTest.removeFirst();

        assertThat(removed, equalTo(STRING_ONE));
    }

    @Test
    public void shouldAddLastTwice() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addLast(STRING_ONE);

        underTest.addLast(STRING_TWO);

        assertThat(underTest.size(), equalTo(2));
        assertThat(underTest.removeFirst(), equalTo(STRING_ONE));
        assertThat(underTest.removeFirst(), equalTo(STRING_TWO));
    }

    @Test
    public void shouldAddFirstTwice() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);

        underTest.addFirst(STRING_TWO);

        assertThat(underTest.size(), equalTo(2));
        assertThat(underTest.removeFirst(), equalTo(STRING_TWO));
        assertThat(underTest.getFirst(), equalTo(STRING_ONE));
    }

    @Test
    public void shouldAddFirstWhenEmpty() {
        Dequeue<String> underTest = new Dequeue<>();

        underTest.addFirst(STRING_ONE);

        assertThat(underTest.getFirst(), equalTo(STRING_ONE));
    }

    @Test
    public void shouldAddLastWhenEmpty() {
        Dequeue<String> underTest = new Dequeue<>();

        underTest.addLast(STRING_ONE);

        assertThat(underTest.getLast(), equalTo(STRING_ONE));
    }

    @Test
    public void shouldBeEmptyWhenRemoveLastTwice() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_TWO);
        underTest.addFirst(STRING_ONE);
        underTest.removeLast();
        underTest.removeLast();

        assertTrue(underTest.isEmpty());
    }

    @Test
    public void shouldBeEmptyWhenRemoveFirstTwice() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_TWO);
        underTest.addFirst(STRING_ONE);
        underTest.removeFirst();
        underTest.removeFirst();

        assertTrue(underTest.isEmpty());
    }

    @Test
    public void shouldBeEmptyWhenRemoveFirst() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);
        underTest.removeFirst();

        int size = underTest.size();

        assertThat(size, equalTo(0));
    }

    @Test
    public void shouldBeEmptyWhenRemoveLast() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);
        underTest.removeLast();

        int size = underTest.size();

        assertThat(size, equalTo(0));
    }

    @Test
    public void shouldGetSizeWhenAddLast() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addLast(STRING_ONE);

        int size = underTest.size();

        assertThat(size, equalTo(1));
    }

    @Test
    public void shouldGetSizeWhenAddFirst() {
        Dequeue<String> underTest = new Dequeue<>();
        underTest.addFirst(STRING_ONE);

        int size = underTest.size();

        assertThat(size, equalTo(1));
    }

    @Test
    public void shouldGetSizeWhenEmpty() {
        Dequeue<String> underTest = new Dequeue<>();

        int size = underTest.size();

        assertThat(size, equalTo(0));
    }
}
