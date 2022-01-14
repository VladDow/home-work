package com.sbrf.reboot.repository;

import java.util.Set;

/**
 * Интерфейс, описывающий базовые операции для работы с репозиторием счетов
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public interface AccountRepository {

    /**
     * Метод должен возвращать все счета по уникальному идентификатору клиента
     * @param clientId уникальный идентификатор клиента
     * @return возвращает счета по идентификатору клиента
     */
    Set<Long> getAllAccountsByClientId(long clientId);

    /**
     * Метод должен возвращать уникальный идентификатор клиента по его счету
     * @param contractNumber номер счета
     * @return возвращает уникальный идентификатор по счету
     */
    long getClientIdByContractNumber(long contractNumber);
}
