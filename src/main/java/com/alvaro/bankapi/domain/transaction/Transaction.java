package com.alvaro.bankapi.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    private LocalDate date;
    private LocalTime time;
    private BigDecimal amount;
    private String description;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
