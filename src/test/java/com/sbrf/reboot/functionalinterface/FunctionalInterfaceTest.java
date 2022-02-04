package com.sbrf.reboot.functionalinterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.sbrf.reboot.utils.JSONUtils;

public class FunctionalInterfaceTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SomeObject {
        private String objectName;
    }

    @FunctionalInterface
    interface ObjectToJsonFunction<T> {
        String applyAsJson(T t) throws JsonProcessingException;
    }

    static class ListConverter<T> {
        public List<String> toJsonsList(@NonNull List<T> someObjects, ObjectToJsonFunction<T> objectToJsonFunction) {

            if (someObjects.isEmpty())
                throw new IllegalArgumentException("The list is empty");

            return someObjects.stream().map(object -> {
                try {
                    return objectToJsonFunction.applyAsJson(object);
                } catch (JsonProcessingException e) {
                    throw new IllegalArgumentException("Unable to convert all objects");
                }
            }).collect(Collectors.toList());

        }
    }

    @Test
    public void successCallFunctionalInterface() {
        ListConverter<SomeObject> ListConverter = new ListConverter<>();

        ObjectToJsonFunction<SomeObject> objectToJsonFunction = JSONUtils::toJSON;

        List<String> strings = ListConverter.toJsonsList(
                Arrays.asList(
                        new SomeObject("Object-1"),
                        new SomeObject("Object-2")
                ),
                objectToJsonFunction
        );

        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-1\"}"));
        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-2\"}"));
    }

    /*
     * Задача на 5+.
     * Имеется последовательность простых чисел в потоке.
     *
     * Вопрос.
     * Какой стандартный функциональный интерфейс необходимо реализовать, чтобы получить последовательность факториалов этих чисел.
     */
    @Test
    public void factorialFromStream() {

        Function<Integer, Integer> factorial = integer -> IntStream.rangeClosed(1, integer).reduce((left, right) -> left * right).getAsInt();

        List<Integer> result = Stream.of(1, 2, 3, 4, 5).map(factorial).collect(Collectors.toList());

        Assertions.assertEquals(result, Arrays.asList(1, 2, 6, 24, 120));

    }

}
