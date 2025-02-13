package com.alvaro.bankapi.infrastructure.adapters.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionRequestDto {


    private BigDecimal account;
    @NotNull
    @PastOrPresent
    private LocalDate dateFrom;
    @NotNull
    @PastOrPresent
    private LocalDate dateTo;
    @NotNull
    @Positive
    private BigDecimal page;


    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public BigDecimal getPage() {
        return page;
    }

    public void setPage(BigDecimal page) {
        this.page = page;
    }
}
