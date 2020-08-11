package com.checkr.interviews;

public enum CsvLayout {
    PERMALINK_INDEX(0),
    COMPANY_NAME_INDEX(1),
    NUMBER_OF_EMPLOYEES_INDEX(2),
    CATEGORY_INDEX(3),
    CITY_INDEX(4),
    STATE_INDEX(5),
    FUNDED_DATE_INDEX(6),
    RAISED_AMOUNT_INDEX(7),
    RAISED_CURRENCY_INDEX(8),
    ROUND_INDEX(9);

    private int index;

    CsvLayout(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
