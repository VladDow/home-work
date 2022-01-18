package com.sbrf.reboot.lesson5;

import com.sbrf.reboot.lesson5.fruit.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

class BoxTest {

    Apple[] apple;
    Orange orange;

    @BeforeEach
    void setUp() {
        apple = new Apple[] {
            Mockito.mock(Apple.class),
            Mockito.mock(Apple.class)
        };
        orange = Mockito.mock(Orange.class);
    }

    @Test
    void addFruitInBoxTrue() {
        Box<Apple> boxTest = new Box<>();
        Assertions.assertTrue(boxTest.add(apple[0]));
        // При boxTest.add(orange) будет ошибка компиляции
    }

    @Test
    void addFruitInBoxFalse() {
        Box<Apple> boxTest = new Box<>();
        boxTest.add(apple[0]);
        Assertions.assertFalse(boxTest.add(apple[0]));
    }

    @Test
    void getWeightBox() {
        Box<Apple> boxTest = new Box<>(apple);

        Mockito.when(apple[0].getWeight()).thenReturn(100.0);
        Mockito.when(apple[1].getWeight()).thenReturn(120.5);

        Assertions.assertEquals(220.5, boxTest.getWeight());
    }

    @Test
    void compareBoxTrue() {
        Box<Apple> boxTestApple = new Box<>(apple);
        Box<Orange> boxTestOrange = new Box<>(orange);

        Mockito.when(apple[0].getWeight()).thenReturn(100.0);
        Mockito.when(apple[1].getWeight()).thenReturn(120.5);

        Mockito.when(orange.getWeight()).thenReturn(220.5);

        Assertions.assertTrue(boxTestApple.compareWeight(boxTestOrange));
    }

    @Test
    void compareBoxFalse() {
        Box<Apple> boxTestApple = new Box<>(apple);
        Box<Orange> boxTestOrange = new Box<>(orange);

        Mockito.when(apple[0].getWeight()).thenReturn(100.0);
        Mockito.when(apple[1].getWeight()).thenReturn(120.5);

        Mockito.when(orange.getWeight()).thenReturn(160.0);

        Assertions.assertFalse(boxTestApple.compareWeight(boxTestOrange));
    }

}
