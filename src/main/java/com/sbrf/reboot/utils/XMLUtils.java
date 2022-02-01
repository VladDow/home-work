package com.sbrf.reboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

/**
 * Утилита для сериализации классов в XML, передаваемых между банкоматом и хостом.
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public final class XMLUtils {

    // Убираем возможность создания экземпляров данного класса
    private XMLUtils() {}

    private static final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Метод сериализации объектов в XML формат.
     * @param data объект для сериализации
     * @return строку с объектом в формате XML
     */
    public static <T> String toXML(T data) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(data);
    }

    /**
     * Метод десериализации объекта класса Request из XML формата.
     * @param xmlData строка с объектом для десериализации
     * @return объект класса Request
     */
    public static Request XMLtoRequest(String xmlData) throws JsonProcessingException {
        return xmlMapper.readValue(xmlData, Request.class);
    }

    /**
     * Метод десериализации объекта класса Response из XML формата.
     * @param xmlData строка с объектом для десериализации
     * @return объект класса Response
     */
    public static Response XMLtoResponse(String xmlData) throws JsonProcessingException {
        return xmlMapper.readValue(xmlData, Response.class);
    }

}
