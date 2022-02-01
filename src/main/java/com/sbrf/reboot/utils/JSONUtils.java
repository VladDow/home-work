package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

/**
 * Утилита для сериализации классов в JSON, передаваемых между банкоматом и хостом.
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public final class JSONUtils {

    // Убираем возможность создания экземпляров данного класса
    private JSONUtils() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Метод сериализации объектов в JSON формат.
     * @param data объект для сериализации
     * @return строку с объектом в формате JSON
     */
    public static <T> String toJSON(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }

    /**
     * Метод десериализации объекта класса Request из JSON формата.
     * @param jsonData строка с объектом для десериализации
     * @return объект класса Request
     */
    public static Request JSONtoRequest(String jsonData) throws JsonProcessingException {
        return objectMapper.readValue(jsonData, Request.class);
    }

    /**
     * Метод десериализации объекта класса Response из JSON формата.
     * @param jsonData строка с объектом для десериализации
     * @return объект класса Response
     */
    public static Response JSONtoResponse(String jsonData) throws JsonProcessingException {
        return objectMapper.readValue(jsonData, Response.class);
    }

}
