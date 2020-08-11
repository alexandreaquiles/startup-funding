package com.checkr.interviews;

public enum CsvLayout {
    PERMALINK(0),
    COMPANY_NAME(1),
    NUMBER_OF_EMPLOYEES(2),
    CATEGORY(3),
    CITY(4),
    STATE(5),
    FUNDED_DATE(6),
    RAISED_AMOUNT(7),
    RAISED_CURRENCY(8),
    ROUND(9);

    private int index;

    CsvLayout(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
