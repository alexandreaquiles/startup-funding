package com.checkr.interviews;

public enum CsvLayout {
    PERMALINK(0, "permalink"),
    COMPANY_NAME(1, "company_name"),
    NUMBER_OF_EMPLOYEES(2, "number_of_employees"),
    CATEGORY(3, "category"),
    CITY(4, "city"),
    STATE(5, "state"),
    FUNDED_DATE(6, "funded_state"),
    RAISED_AMOUNT(7, "raised_amount"),
    RAISED_CURRENCY(8, "raised_currency"),
    ROUND(9, "round");

    private int index;
    private String key;

    CsvLayout(int index, String key) {
        this.index = index;
        this.key = key;
    }

    public int index() {
        return index;
    }

    public String key() {
        return key;
    }
}
