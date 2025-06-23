package com.furan.living.commodity.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 收支类型枚举
 */
public enum IncomeExpenseType {
    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String value;

    IncomeExpenseType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
