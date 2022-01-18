package com.sbrf.reboot.atm.cassettes;

import java.util.ArrayList;

/**
 * Класс, определяющий кассеты в банкомате
 * @author Довгун Владислав Алексеевич
 * @version 0.1
 */
public class Cassette<T extends Banknote> {

    // Список купюр одного номинала в кассете
    private final ArrayList<T> cassette;

    public Cassette(ArrayList<T> cassette) {
        this.cassette = cassette;
    }

    public int getCountBanknotes() {
        return cassette.size();
    }

}
