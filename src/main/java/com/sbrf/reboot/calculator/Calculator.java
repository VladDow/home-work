package com.sbrf.reboot.calculator;

/**
 * Класс, реализующий часть базовых математических операций
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public class Calculator {

    /**
     * Сложение двух целых чисел
     * @param termOne первое слагаемое
     * @param termTwo второе слагаемое
     * @return возвращает сумму двух целых чисел
     */
    public int getAddition(int termOne, int termTwo) {
        return termOne + termTwo;
    }

    /**
     * Вычитание двух целых чисел
     * @param minuend уменьшаемое
     * @param subtrahend вычитаемое
     * @return возвращает разность двух целых чисел
     */
    public int getSubtraction(int minuend, int subtrahend) {
        return minuend - subtrahend;
    }

    /**
     * Умножение двух целых чисел
     * @param multiplied множимое
     * @param multiplier множитель
     * @return возвращает произведение двух целых чисел
     */
    public int getMultiplication(int multiplied, int multiplier) {
        return multiplied * multiplier;
    }

    /**
     * Целочисленное деление двух целых чисел
     * @param dividend делимое
     * @param divider делитель
     * @return возвращает частное двух целых чисел
     */
    public int getDivision(int dividend, int divider) {
        return dividend / divider;
    }

    /**
     * Остаток от целочисленное деление двух целых чисел
     * @param dividend делимое
     * @param divider делитель
     * @return возвращает остаток частного двух целых чисел
     */
    public int getRemainder(int dividend, int divider) {
        return dividend % divider;
    }

    /**
     * Модуль целого числа
     * @param number любое целое число
     * @return возвращает абсолютную величину целого числа
     */
    public int getModule(int number) {
        return number < 0 ? -1 * number : number;
    }

    /**
     * Возведение целого числа в целую степень
     * @param base основание степени
     * @param exponent показатель степени
     * @return возвращает результат возведения целого числа в степень
     */
    public double getExponentiation(int base, int exponent) {
        double result = 1;
        int exponentTemporary = new Calculator().getModule(exponent);
        for (int i = 0; i < exponentTemporary; ++i) {
            result *= base;
        }
        return exponent >= 0 ? result : 1 / result;
    }

}
