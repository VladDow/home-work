package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EqualsHashCodeTest {

    class Car {

        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        Car(String model, String color, Calendar releaseDate, int maxSpeed) {
            this.model = model;
            this.color = color;
            this.releaseDate = releaseDate;
            this.maxSpeed = maxSpeed;
        }

        @Override
        public boolean equals(Object o) {

            // Рефлексивность: объект должен равняться самому себе
            if (o == this) {
                return true;
            }

            // Все остальные свойства, вроде симметричности, транзитивности и т.д. вытекают из условий ниже
            if (o instanceof Car) {

                Car anotherCar = (Car) o;

                if (anotherCar.model != null) {
                    if (!anotherCar.model.equals(this.model)) {
                        return false;
                    }
                } else if (this.model != null) {
                    return false;
                }

                if (anotherCar.color != null) {
                    if (!anotherCar.color.equals(this.color)) {
                        return false;
                    }
                } else if (this.color != null) {
                    return false;
                }

                if (anotherCar.releaseDate != null) {
                    if (!anotherCar.releaseDate.equals(this.releaseDate)) {
                        return false;
                    }
                } else if (this.releaseDate != null) {
                    return false;
                }

                return anotherCar.maxSpeed == this.maxSpeed;

            } else {
                return false;
            }

        }

        @Override
        public int hashCode() {
            final int BASE = 59, NOT_EXIST = 43;
            int result = BASE + this.maxSpeed;
            result = ((result * BASE + (this.model == null ? NOT_EXIST : this.model.hashCode()))
                      * BASE + (this.color == null ? NOT_EXIST : this.color.hashCode()))
                      * BASE + (this.releaseDate == null ? NOT_EXIST : this.releaseDate.hashCode());
            return result;
        }

    }

    Car car1;

    @BeforeEach
    public void setUp() {
        this.car1 = new Car("Mercedes", "black", new GregorianCalendar(2020, Calendar.JANUARY, 25), 10);
    }

    @Test
    public void assertTrueEquals() {
        Car car2 = new Car("Mercedes", "black", new GregorianCalendar(2020, Calendar.JANUARY, 25), 10);
        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car2 = new Car("Audi", "white", new GregorianCalendar(2017, Calendar.JANUARY, 25), 10);
        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode() {
        Car car2 = new Car("Mercedes", "black", new GregorianCalendar(2020, Calendar.JANUARY, 25), 10);
        Assertions.assertEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    public void failEqualsHashCode() {
        Car car2 = new Car("Audi", "white", new GregorianCalendar(2017, Calendar.JANUARY, 25), 10);
        Assertions.assertNotEquals(car1.hashCode(), car2.hashCode());
    }

    /**
     *  Рефлексивность: объект должен равняться самому себе
     */

    @Test
    public void equalsShouldReflexivity() {
        Assertions.assertTrue(car1.equals(car1));
    }

    /**
     *  Симметричность: x.equals(y) должен возвращать тот же результат, что и y.equals(x)
     */

    @Test
    public void equalsShouldSymmetric() {
        Car car2 = new Car("Mercedes", "black", new GregorianCalendar(2020, Calendar.JANUARY, 25), 10);
        Assertions.assertEquals(car1.equals(car2), car2.equals(car1));
    }

    /**
     *  Транзитивность: если x.equals(y) и y.equals(z), то также x.equals(z)
     */

    @Test
    public void equalsShouldTransitive() {
        Car car2 = new Car("Mercedes", "black", new GregorianCalendar(2020, Calendar.JANUARY, 25), 10);
        Car car3 = new Car("Mercedes", "black", new GregorianCalendar(2020, Calendar.JANUARY, 25), 10);
        Assertions.assertTrue(car1.equals(car2));
        Assertions.assertEquals(car2.equals(car3), car1.equals(car3));
    }

    /**
     *  Внутренняя согласованность: значение hashCode() может изменяться только в том случае, если изменяется свойство, находящееся в equals()
     */

    @Test
    public void hashCodeShouldInternalConsistencyTrue() {
        Assertions.assertEquals(car1.hashCode(), car1.hashCode());
    }

    @Test
    public void hashCodeShouldInternalConsistencyFalse() {
        int oldHashCode = car1.hashCode();
        car1.model = "Audi";
        Assertions.assertNotEquals(oldHashCode, car1.hashCode());
    }

}
