package com.alvaro.bankapi.infrastructure.adapters.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class TransactionDetailsRequestDto {

    private BigDecimal sequential;

    @NotBlank
    @Size(max = 2)
    private String creditOrDebit;
    private BigDecimal account;
    @NotBlank
    @Size(max = 4)
    private String code;
    @NotBlank
    @Size(min = 5)
    private String description;
    private BigDecimal amount;
    @NotBlank
    @Size(min = 3, max = 3) //COP, USD
    private String currency;

    public BigDecimal getSequential() {
        return sequential;
    }

    public void setSequential(BigDecimal sequential) {
        this.sequential = sequential;
    }

    public String getCreditOrDebit() {
        return creditOrDebit;
    }

    public void setCreditOrDebit(String creditOrDebit) {
        this.creditOrDebit = creditOrDebit;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
