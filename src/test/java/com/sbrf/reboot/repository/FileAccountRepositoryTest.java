package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.FileAccountRepository;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileAccountRepositoryTest {

    FileAccountRepository accountRepository;

    @Test
    @SneakyThrows
    void onlyPersonalAccounts() {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(222L);
            add(333L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

    @Test
    void failGetAllAccountsByClientId() {
        long clientId = 1L;

        String filePath = "somePath";

        accountRepository = new FileAccountRepository(filePath);

        assertThrows(FileNotFoundException.class, () -> accountRepository.getAllAccountsByClientId(clientId));
    }

    @Test
    @SneakyThrows
    void clientIdExist() {
        long contractNumber = 999L;

        String filePath = "src/main/resources/Accounts.txt";

        accountRepository = new FileAccountRepository(filePath);

        assertEquals(3L, accountRepository.getClientIdByContractNumber(contractNumber));
    }

    @Test
    @SneakyThrows
    void failGetClientIdByContractNumber() {
        long contractNumber = 123L;

        String filePath = "src/main/resources/Accounts.txt";

        accountRepository = new FileAccountRepository(filePath);

        assertEquals(-1L, accountRepository.getClientIdByContractNumber(contractNumber));
    }

    @Test
    @SneakyThrows
    void changeContractNumber() {
        long clientId = 1L;
        long oldContractNumber = 333L;
        long newContractNumber = 100L;

        String filePath = "src/main/resources/Accounts.txt";

        accountRepository = new FileAccountRepository(filePath);
        accountRepository.setNewContractNumberByClientId(clientId, oldContractNumber, newContractNumber);

        assertEquals(1L, accountRepository.getClientIdByContractNumber(newContractNumber));

        accountRepository.setNewContractNumberByClientId(clientId, newContractNumber, oldContractNumber);
    }

}