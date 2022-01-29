package com.sbrf.reboot.concurrentmodify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveElementWithoutErrorsTest {

    List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>(Arrays.asList(1, 2, 2, 3));
    }

    @Test
    public void successConcurrentModificationException() {

        assertThrows(ConcurrentModificationException.class, () -> {
            
            for (Integer integer : list) {
                list.remove(1);
            }

        });

    }

    @Test
    public void successRemoveElementWithoutErrorsOne() {

        list.removeIf(integer -> integer == 2);
        assertEquals(2, list.size());

    }

    @Test
    public void successRemoveElementWithoutErrorsTwo() {

        while (list.remove((Integer) 2));
        assertEquals(2, list.size());

    }

    @Test
    public void successRemoveElementWithoutErrorsThree() {

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }

        assertEquals(2, list.size());

    }

    @Test
    public void successRemoveElementWithoutErrorsFour() {

        list = list.stream().filter(integer -> !integer.equals(2)).collect(Collectors.toList());
        assertEquals(2, list.size());

    }

}
