package com.alvaro.bankapi.domain.account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private BigDecimal account;
    private BigDecimal customerCode;
    private String type;
    private String currency;
    private BigDecimal available;
    private String status;
    private String branch;
    private String official;
    private LocalDate openDate;
    private LocalDate closeDate;
    private LocalDate lastTransaction;

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(BigDecimal customerCode) {
        this.customerCode = customerCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public LocalDate getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(LocalDate lastTransaction) {
        this.lastTransaction = lastTransaction;
    }
}
