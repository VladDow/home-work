package com.sbrf.reboot.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void getAddition() {
        assertEquals(9, new Calculator().getAddition(4, 5));
    }

    @Test
    void getSubtraction() {
        assertEquals(-1, new Calculator().getSubtraction(4, 5));
    }

    @Test
    void getMultiplication() {
        assertEquals(20, new Calculator().getMultiplication(4, 5));
    }

    @Test
    void getDivision() {
        assertEquals(3, new Calculator().getDivision(9, 3));
    }

    @Test
    void getRemainder() {
        assertEquals(3, new Calculator().getRemainder(3, 4));
    }

    @Test
    void getModule() {
        assertEquals(5, new Calculator().getModule(-5));
    }

    @Test
    void getExponentiation() {
        assertEquals(0.00032, new Calculator().getExponentiation(5, -5));
    }

    @Test
    void classHasSevenMethods() {
        assertEquals(7, Calculator.class.getMethods().length - Object.class.getMethods().length);
    }
}