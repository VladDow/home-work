package com.sbrf.reboot.lesson5.fruit;

/**
 * Класс, определяющий фрукты
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public class Fruit {

    private final double WEIGHT;

    Fruit(double WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public double getWeight() {
        return WEIGHT;
    }

}
