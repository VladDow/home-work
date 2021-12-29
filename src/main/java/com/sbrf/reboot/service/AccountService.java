package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;

import lombok.AllArgsConstructor;

/**
 * Класс, реализующий базовые операции для клиентов
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
@AllArgsConstructor
public class AccountService {

    // Репозиторий счетов
    private AccountRepository accountRepository;

    /**
     * Проверка наличия заданного номера счета у клиента
     * @param clientId уникальный идентификатор клиента
     * @param contractNumber номер счета
     * @return возвращает истину в случае наличия заданного счета
     */
    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    /**
     * Проверка наличия хоть одного счета у клиента
     * @param clientId уникальный идентификатор клиента
     * @return возвращает истину в случае существования хотя бы одного счета
     */
    public boolean isClientHasLeastOneContract(long clientId) {
        return !accountRepository.getAllAccountsByClientId(clientId).isEmpty();
    }

}
