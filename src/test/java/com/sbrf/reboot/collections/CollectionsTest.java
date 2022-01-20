package com.sbrf.reboot.collections;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionsTest {


    /*
     * Задача.
     * Имеется список лучших студентов вуза.
     *
     * 1. Иванов
     * 2. Петров
     * 3. Сидоров
     *
     * В новом семестре по результатам подсчетов оценок в рейтинг на 1 место добавился новый студент - Козлов.
     * Теперь в рейтинге участвуют 4 студента.
     * (Предполагаем что в рейтинг можно попасть только получив достаточное количество балов, что бы занять 1 место).
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете для текущего хранения и использования списка студентов?
     *
     * Проинициализируйте students, добавьте в нее 4 фамилии студентов что бы тест завершился успешно.
     */
    @Test
    public void addStudentToRating() {

        List<String> students = null;

        students = new LinkedList<>(Arrays.asList("Иванов", "Петров", "Сидоров"));
        students.add(0, "Козлов");

        assertEquals(4, students.size());
    }

    /*
     * Задача.
     * Вы коллекционируете уникальные монеты.
     * У вас имеется специальный бокс с секциями, куда вы складываете монеты в хаотичном порядке.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения монет в боксе.
     *
     * Проинициализируйте moneyBox, добавьте в нее 10 монет что бы тест завершился успешно.
     */
    @Test
    public void addMoneyToBox() {

        Set<Integer> moneyBox = null;

        moneyBox = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertEquals(10, moneyBox.size());
    }

    /*
     * Задача.
     * Имеется книжная полка.
     * Периодически вы берете книгу для чтения, затем кладете ее на свое место.
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для хранения книг.
     *
     * Проинициализируйте bookshelf, добавьте в нее 3 книги что бы тест завершился успешно.
     */
    @Test
    public void addBookToShelf() {
        class Book {
        }

        List<Book> bookshelf = null;

        bookshelf = new ArrayList<>(Arrays.asList(new Book(), new Book(), new Book()));

        assertEquals(3, bookshelf.size());
    }

    /*
     * Задача на 5+.
     * Имеется поликлиника. В поликлинике работает только один терапевт.
     * К терапевту выстроилась живая очередь. Необходимо обслужить сначала пациентов, которые пришли
     * по талону, и только потом всех остальных.
     *
     * Пациенты пришли в следующем порядке:
     *
     * 1. Екатерина - Без записи
     * 2. Максим - Запись на 9:30
     * 3. Анна - Запись на 9:00
     * 4. Руслан - Без записи
     *
     * Вопрос.
     * Какую коллекцию из реализаций интерфейса Collection вы предпочтете использовать для организации такой очереди.
     *
     * Проинициализируйте queue, имплементируйте необходимый интерфейс и добавьте 4 пациента что бы тест завершился успешно.
     */
    @Test
    public void queueOrganization() {

        class Patient implements Comparable<Patient> {

            final String name;
            final LocalTime receptionTime;

            Patient(String name) {
                this.name = name;
                this.receptionTime = LocalTime.MAX;
            }

            Patient(String name, String receptionTime) {
                this.name = name;
                this.receptionTime = LocalTime.parse(receptionTime);
            }

            @Override
            public int compareTo(Patient o) {
                return this.receptionTime.compareTo(o.receptionTime);
            }

        }

        Queue<Patient> queue = null;

        queue = new PriorityQueue<>(Arrays.asList(new Patient("Екатерина"), new Patient("Максим", "09:30"),
                                                  new Patient("Анна","09:00"), new Patient("Руслан")));

        assertEquals("Анна", queue.peek().name);
    }

}
