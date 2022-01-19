package com.sbrf.reboot.atm.cassettes;

import java.util.List;

/**
 * Класс, определяющий кассеты в банкомате
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public class Cassette<T extends Banknote> {

    // Список купюр одного номинала в кассете
    private final List<T> cassette;

    public Cassette(List<T> cassette) {
        this.cassette = cassette;
    }

    public int getCountBanknotes() {
        return cassette.size();
    }

}
