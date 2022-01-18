package com.sbrf.reboot.lesson5;

import com.sbrf.reboot.lesson5.fruit.Fruit;

import java.util.HashSet;
import java.util.Arrays;

/**
 * Класс, определяющий коробку с фруктами
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public class Box<F extends Fruit> {

    // Множество фруктов в коробке
    private final HashSet<F> box;

    public Box(F... fruits) {
        box = new HashSet<>(Arrays.asList(fruits));
    }

    /**
     * Получить вес коробки
     * @return сумму веса всех фруктов в коробке
     */
    public double getWeight() {
        return box.stream().mapToDouble(Fruit::getWeight).sum();
    }

    /**
     * Добавить новый фрукт в коробку
     * @param fruit фрукт для добавления
     * @return результат добавления фрукта
     */
    public boolean add(F fruit) {
        if(fruit == null) {
            return false;
        }
        return box.add(fruit);
    }

    /**
     * Сравнить две коробки по весу
     * @param secondBox другая коробка для сравнения
     * @return результат сравнения
     */
    public boolean compareWeight(Box<?> secondBox) {
        return Math.abs(this.getWeight() - secondBox.getWeight()) < 0.0001;
    }

}
