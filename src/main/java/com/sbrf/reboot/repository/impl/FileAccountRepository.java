package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс, описывающий базовые операции для работы с репозиторием счетов в виде файла
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
@AllArgsConstructor
public class FileAccountRepository implements AccountRepository {

    // Счета клиентов
    final private String filePath;

    /**
     * Получение всех счетов клиента по его уникальному идентификатору
     * @param clientId уникальный идентификатор клиента
     * @return возвращает счета по идентификатору клиента
     */
    @Override
    @SneakyThrows
    public Set<Long> getAllAccountsByClientId(long clientId) {

        final String CLIENT_ID = "clientId";

        Set<Long> actualAccounts = new HashSet<>();
        String[] line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            while(reader.ready()) {
                line = reader.readLine().split(":");
                if (line[0].contains(CLIENT_ID) && Long.parseLong(line[1].replaceAll("[ ,]", "")) == clientId) {
                    actualAccounts.add( Long.parseLong(reader.readLine().split(":")[1].trim()) );
                }
            }
        }

        return actualAccounts;
    }

    /**
     * Получение уникального идентификатора клиента по его счету
     * @param contractNumber номер счета
     * @return возвращает уникальный идентификатор по счету и -1 в случае его отсутствия
     */
    @Override
    @SneakyThrows
    public long getClientIdByContractNumber(long contractNumber) {

        final String CLIENT_ID = "clientId";

        String[] line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            while(reader.ready()) {
                line = reader.readLine().split(":");
                if (line[0].contains(CLIENT_ID)) {
                    long result = Long.parseLong(line[1].replaceAll("[ ,]", ""));
                    if (Long.parseLong(reader.readLine().split(":")[1].replaceAll("[ ,]", "")) == contractNumber) {
                        return result;
                    }
                }
            }
        }

        return -1L;
    }

    /**
     * Обновление номера счета клиента по его уникальному идентификатору
     * @param clientId уникальный идентификатор клиента
     * @param oldContractNumber старый номер счета
     * @param newContractNumber новый номер счета
     */
    @SneakyThrows
    public void setNewContractNumberByClientId(long clientId, long oldContractNumber, long newContractNumber) {

        final String CLIENT_ID = "clientId";

        String read;
        String[] line;

        try (RandomAccessFile file = new RandomAccessFile(filePath, "rws")) {
            while( (read = file.readLine()) != null ) {
                line = read.split(":");

                if (line[0].contains(CLIENT_ID) && Long.parseLong(line[1].replaceAll("[ ,]", "")) == clientId) {
                    long nowFilePointer = file.getFilePointer();
                    read = file.readLine();
                    line = read.split(":");
                    if (Long.parseLong(line[1].replaceAll("[ ,]", "")) == oldContractNumber) {
                        file.seek(nowFilePointer);
                        file.writeBytes(read.replace(String.valueOf(oldContractNumber), String.valueOf(newContractNumber)));
                    }
                }

            }
        }

    }

}
