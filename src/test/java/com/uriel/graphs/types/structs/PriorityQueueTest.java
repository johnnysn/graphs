package com.uriel.graphs.types.structs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityQueueTest {

    private final PriorityQueue subject;

    public PriorityQueueTest() {
        subject = new PriorityQueue();
    }

    @Test
    void mustOfferCorrectly() {
        // arrange
        var elem1 = new QueueElement(3);
        var elem2 = new QueueElement(2);
        var elem3 = new QueueElement(1);
        // act
        subject.offer(elem1);
        subject.offer(elem2);
        subject.offer(elem3);
        // assert
        assertEquals(3, subject.size());
        var data = subject.getHeap().getData();
        System.out.println(data);
    }

    @Test
    void mustPollCorrectly() {
        // arrange
        var elem1 = new QueueElement(7);
        var elem2 = new QueueElement(3);
        var elem3 = new QueueElement(5);
        var elem4 = new QueueElement(2);
        subject.offer(elem1);
        subject.offer(elem2);
        subject.offer(elem3);
        subject.offer(elem4);
        System.out.println(subject.getHeap().getData());
        // act
        var first = subject.poll();
        System.out.println(subject.getHeap().getData());
        var second = subject.poll();
        System.out.println(subject.getHeap().getData());
        var third = subject.poll();
        System.out.println(subject.getHeap().getData());
        // assert
        assertEquals(1, subject.size());
        assertEquals(2, first.getKey());
        assertEquals(3, second.getKey());
        assertEquals(5, third.getKey());
        System.out.println(subject.getHeap().getData());
    }

    @Test
    void mustDescreasePriorityCorrectly() {
        // arrange
        var elem1 = new QueueElement(5); // Will be last index: 3
        var elem2 = new QueueElement(4);
        var elem3 = new QueueElement(3);
        var elem4 = new QueueElement(2);
        subject.offer(elem1);
        subject.offer(elem2);
        subject.offer(elem3);
        subject.offer(elem4);
        System.out.println(subject.getHeap().getData());
        // act
        subject.decreasePriority(elem1, -1);
        // assert
        System.out.println(subject.getHeap().getData());
        assertEquals(-1, subject.peek().getKey());
    }
}

@ToString
class QueueElement implements Heapable {
    @Getter
    @Setter
    private double key;

    private final String original;

    public QueueElement(double key) {
        this.key = key;
        this.original = "" + key;
    }
}